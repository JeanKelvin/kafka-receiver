package com.example.consumer.infrastructure.mongo;

import com.example.consumer.models.RegraPromocaoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegraPromocaoRepository extends MongoRepository<RegraPromocaoEntity, String> { }
