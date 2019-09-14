package com.example.receiver.infrastructure.mongo;

import com.example.receiver.models.RegraPromocaoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegraPromocaoRepository extends MongoRepository<RegraPromocaoEntity, String> { }
