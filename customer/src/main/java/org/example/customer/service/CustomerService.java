package org.example.customer.service;

import lombok.RequiredArgsConstructor;
import org.example.amqp.RabbitMQMessageProducer;
import org.example.clients.fraud.FraudCheckResponse;
import org.example.clients.fraud.FraudClient;
import org.example.clients.notification.NotificationClient;
import org.example.clients.notification.NotificationDTO;
import org.example.customer.dto.CustomerDTO;
import org.example.customer.entity.Customer;
import org.example.customer.mappers.CustomerMapper;
import org.example.customer.repo.CustomerRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;
//    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;
    // Now we're not sending notifications using faign client, we're using amqp
//    private final NotificationClient notificationClient;

    private final RabbitMQMessageProducer producer;

    public HttpEntity<?> register(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customerRepository.save(customer);

        //-
        // We can establish communication between microservices using rest template however,
        // in this case, there are problems with dto. Which have to be created in all microservices that
        // need the dto.
//        FraudCheckResponse fraudCheckResponse = restTemplate.getForObject(
////                "http://localhost:8082/api/fraud-check/{customerId}", // now that we have eureka and put loadBalanced annotation to rest template bean, we dont need the port
//                "http://FRAUD/api/fraud-check/{customerId}",
//                FraudCheckResponse.class,
//                customer.getId());

        FraudCheckResponse fraudCheckResponse = fraudClient.checkFraudster(customer.getId());
        if (fraudCheckResponse.getIsFraudster()) {
            throw new RuntimeException("Fraudster!!!");
        }

        NotificationDTO notificationDTO = new NotificationDTO(
                customer.getId(),
                "is not a fraud and is registered"
        );

//        notificationClient.saveNotification(notificationDTO);

        // now notifications are being saved using amqp asynchronously
        producer.publish(notificationDTO, "myinternalexchange", "secret-key");

        return ResponseEntity.status(201).body(customer);
    }
}
