package org.example.fraud.service;

import lombok.RequiredArgsConstructor;
import org.example.fraud.entity.FraudCheckHistory;
import org.example.fraud.repo.FraudCheckHistoryRepo;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FraudCheckService {

    private final FraudCheckHistoryRepo fraudCheckHistoryRepo;

    public Boolean isFraudster(Integer customerId) {
        fraudCheckHistoryRepo.save(new FraudCheckHistory(
                customerId,
                false
        ));
        return false;
    }
}
