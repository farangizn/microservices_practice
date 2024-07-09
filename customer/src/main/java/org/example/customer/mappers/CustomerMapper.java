package org.example.customer.mappers;

import org.example.customer.dto.CustomerDTO;
import org.example.customer.entity.Customer;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface CustomerMapper {
    Customer toEntity(CustomerDTO customerDTO);

    CustomerDTO toDto(Customer customer);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Customer partialUpdate(CustomerDTO customerDTO, @MappingTarget Customer customer);
}