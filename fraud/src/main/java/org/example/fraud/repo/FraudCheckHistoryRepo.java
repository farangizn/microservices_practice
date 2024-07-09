package org.example.fraud.repo;


import org.example.fraud.entity.FraudCheckHistory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FraudCheckHistoryRepo extends MongoRepository<FraudCheckHistory, String> {
}
