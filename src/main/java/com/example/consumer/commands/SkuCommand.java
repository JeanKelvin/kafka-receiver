package com.example.consumer.commands;

import com.example.consumer.dtos.MercadoriaDTO;
import com.example.consumer.builders.SkuEntityBuilder;
import com.example.consumer.models.DimensaoEntity;
import com.example.consumer.models.EstruturaEntity;
import com.example.consumer.models.SkuEntity;
import com.example.consumer.infrastructure.mongo.SkuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.example.consumer.mappers.BaseJsonMapper.fromJson;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class SkuCommand extends ReceiverCommandCommandConsumerKafka<SkuEntity, String> {

    private final SkuRepository repository;

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

        inserirOuAtualizarRegistro(message, query, entity, nonNull(args) ?
                String.valueOf(args.get(ArgsCommandEnum.TOPIC.toString())) : null);
    }
}
