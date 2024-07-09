package org.example.notification.service;

import lombok.RequiredArgsConstructor;
import org.example.clients.notification.NotificationDTO;
import org.example.notification.entity.Notification;
import org.example.notification.mappers.NotificationMapper;
import org.example.notification.repo.NotificationRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    public void save(NotificationDTO notificationDTO) {
        Notification notification = notificationMapper.toEntity(notificationDTO);
        notificationRepository.save(notification);
    }
}
