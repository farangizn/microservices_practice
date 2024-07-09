package org.example.notification.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.example.clients.notification.NotificationDTO;
import org.example.notification.entity.Notification;
import org.example.notification.mappers.NotificationMapper;
import org.example.notification.repo.NotificationRepository;
import org.example.notification.service.NotificationService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationListener {
    private final NotificationService notificationService;

    @RabbitListener(queues = {"myqueue"})
    public void consumer(NotificationDTO notificationDTO) {
        notificationService.save(notificationDTO);
    }
}
