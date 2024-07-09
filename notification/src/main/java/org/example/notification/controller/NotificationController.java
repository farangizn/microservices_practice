package org.example.notification.controller;

import lombok.RequiredArgsConstructor;
import org.example.clients.notification.NotificationDTO;
import org.example.notification.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public void saveNotification(@RequestBody NotificationDTO notificationDTO) {
        notificationService.save(notificationDTO);
    }
}
