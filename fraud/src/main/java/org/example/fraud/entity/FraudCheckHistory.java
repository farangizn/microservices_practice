package org.example.fraud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class FraudCheckHistory {

    private String id;
    private Integer customerId;
    private Boolean isFraudster;
    @CreatedDate
    private LocalDateTime checkDate;

    public FraudCheckHistory(Integer customerId, Boolean isFraudster) {
        this.customerId = customerId;
        this.isFraudster = isFraudster;
    }
}
