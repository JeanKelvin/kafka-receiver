package com.example.consumer.commands;

import com.example.consumer.dtos.SkuLojistaDTO;
import com.example.consumer.builders.SkuLojistaEntityBuilder;
import com.example.consumer.models.SkuLojistaEntity;
import com.example.consumer.infrastructure.mongo.SkuLojistaRepository;
import com.example.consumer.mappers.BaseEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.example.consumer.mappers.BaseJsonMapper.fromJson;
import static java.util.Objects.nonNull;


@Component
@RequiredArgsConstructor
public class SkuLojistaCommand extends ReceiverCommandCommandConsumerKafka<SkuLojistaEntity, String> {

    private final SkuLojistaRepository repository;

    @Override
    public MongoRepository<SkuLojistaEntity, String> getRepository() {
        return this.repository;
    }

    @Override
    public void execute(String message, Map<String, Object> args) {
        final SkuLojistaDTO skuLojistaDTO = fromJson(message, SkuLojistaDTO.class);
        final SkuLojistaEntity entity = BaseEntityMapper.toEntity(skuLojistaDTO, SkuLojistaEntity.class);

        //TODO VALIDAR REGISTRO

        final SkuLojistaEntity query = new SkuLojistaEntityBuilder()
                .comSku(entity.getIdSku())
                .comLojista(entity.getIdLojista())
                .constroi();

        inserirOuAtualizarRegistro(message, query, entity, nonNull(args) ?
                String.valueOf(args.get(ArgsCommandEnum.TOPIC.toString())) : null);
    }
}
