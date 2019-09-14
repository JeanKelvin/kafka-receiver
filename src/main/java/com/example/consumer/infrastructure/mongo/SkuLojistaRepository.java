package com.example.consumer.infrastructure.mongo;

import com.example.consumer.models.SkuLojistaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkuLojistaRepository extends MongoRepository<SkuLojistaEntity, String> { }
