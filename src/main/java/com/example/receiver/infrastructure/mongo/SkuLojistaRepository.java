package com.example.receiver.infrastructure.mongo;

import com.example.receiver.models.SkuLojistaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SkuLojistaRepository extends MongoRepository<SkuLojistaEntity, String> { }
