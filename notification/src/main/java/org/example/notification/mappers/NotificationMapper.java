package org.example.notification.mappers;

import org.example.clients.notification.NotificationDTO;
import org.example.notification.entity.Notification;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface NotificationMapper {
    Notification toEntity(NotificationDTO notificationDTO);

    NotificationDTO toDto(Notification notification);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Notification partialUpdate(NotificationDTO notificationDTO, @MappingTarget Notification notification);
}