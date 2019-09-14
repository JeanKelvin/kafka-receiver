package com.example.receiver.infrastructure.mongo;

import com.example.receiver.models.SkuEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkuRepository extends MongoRepository<SkuEntity, String> { }
