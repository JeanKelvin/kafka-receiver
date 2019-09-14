package com.example.receiver.commands;

import com.example.receiver.dtos.MercadoriaDTO;
import com.example.receiver.builders.SkuEntityBuilder;
import com.example.receiver.infrastructure.kafka.MapKafkaTopicsConfig;
import com.example.receiver.infrastructure.kafka.TopicProducerHandler;
import com.example.receiver.models.DimensaoEntity;
import com.example.receiver.models.EstruturaEntity;
import com.example.receiver.models.SkuEntity;
import com.example.receiver.infrastructure.mongo.SkuRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.example.receiver.mappers.BaseJsonMapper.fromJson;
import static java.util.Objects.nonNull;

@Slf4j
@Component
@RequiredArgsConstructor
public class SkuCommand extends ReceiverCommandCommandConsumerKafka<SkuEntity, String> {

    private final SkuRepository repository;
    private final TopicProducerHandler topicProducerHandler;
    private final MapKafkaTopicsConfig mapKafkaTopicsConfig;

    @Override
    public MongoRepository<SkuEntity, String> getRepository() {
        return this.repository;
    }

    @Override
    public void execute(String message, Map<String, Object> args) {
        final MercadoriaDTO mercadoria = fromJson(message, MercadoriaDTO.class);

        //TODO VALIDAR REGISTRO

        final SkuEntityBuilder builder = new SkuEntityBuilder();
        final SkuEntity entity = builder.comSku(mercadoria.getIdSku())
                .comPeso(mercadoria.getPesoKg())
                .comTipoTransporte(mercadoria.getIdTipoTransporte())
                .comDimensao(new DimensaoEntity(mercadoria.getAltura(), mercadoria.getLargura(), mercadoria.getComprimento()))
                .comEstrutura(new EstruturaEntity(mercadoria.getIdSetor(), mercadoria.getIdDepartamento(), mercadoria.getIdFamilia(), mercadoria.getIdSubFamilia()))
                .comDataObjetoOrigem(mercadoria.getDataObjetoOrigem())
                .constroi();

        final SkuEntityBuilder queryBuilder = new SkuEntityBuilder();
        final SkuEntity query = queryBuilder.comSku(entity.getIdSku()).constroi();

        SkuEntity skuEntity = inserirOuAtualizarRegistro(message, query, entity, nonNull(args) ?
                String.valueOf(args.get(ArgsCommandEnum.TOPIC.toString())) : null);

        //sendMessageKafka(skuEntity.getId());
    }

    /*private void sendMessageKafka(String message) {
        final String topic = this.mapKafkaTopicsConfig.getProducerMap().get(TopicProducerEnum.TOPICSKUID.name());
        this.topicProducerHandler.produce(topic, message);
        log.info("ID Sku enviado ao kafka", message);
    }*/
}
