package org.example.notification;

import org.example.amqp.RabbitMQMessageProducer;
import org.springframework.amqp.core.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(
        scanBasePackages = {
                "org.example.notification",
                "org.example.amqp"
        }
)
public class NotificationApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationApplication.class, args);
    }

    @Bean
    public TopicExchange internalTopicExchange() {          // exchange
        return new TopicExchange("myinternalexchange");
    }

    @Bean
    public Queue notificationQueue() {                     // queue
        return new Queue("myqueue");
    }

    @Bean
    public Binding notificationBinding() {                 // here, we're binding queue and exchange and giving a key
        return BindingBuilder                              // exchange will know which queue to send the message to via this key
                .bind(notificationQueue())
                .to(internalTopicExchange())
                .with("secret-key");
    }

    // just a command line runner to test if amqp is working (not a part of rabbitmq configuration)
//    @Bean
//    CommandLineRunner commandLineRunner(RabbitMQMessageProducer producer) {
//        return args -> {
//            producer.publish(new Data("Eshmat", 12), "myinternalexchange", "secret-key");
//        };
//    }
//
//    record Data(String name, Integer age) {
//
//    }

}
