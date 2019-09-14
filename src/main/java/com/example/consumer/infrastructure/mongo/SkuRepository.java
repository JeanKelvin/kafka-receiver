package com.example.consumer.infrastructure.mongo;

import com.example.consumer.models.SkuEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkuRepository extends MongoRepository<SkuEntity, String> { }
