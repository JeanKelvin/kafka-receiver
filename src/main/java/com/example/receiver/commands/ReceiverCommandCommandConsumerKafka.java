package com.example.receiver.commands;

import com.example.receiver.models.BaseEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Example;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.lang.reflect.Field;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Slf4j
abstract class ReceiverCommandCommandConsumerKafka<T extends BaseEntity, ID> implements CommandConsumerKafka {

    public abstract MongoRepository<T, ID> getRepository();

    T inserirOuAtualizarRegistro(String message, T queryEntity, T newEntity, String topic) {
        try {

            T fromQuery = null;

            if (nonNull(queryEntity)) {
                fromQuery = getRepository().findOne(Example.of(queryEntity)).orElse(null);
            }

            if (!isNull(fromQuery)) {
                log.info("Mensagem recebida do Kafka no tópico " + topic + ": " + message, fromQuery);
                atualizaRegistro(fromQuery, newEntity);
            } else {
                log.info("Mensagem recebida do Kafka no tópico " + topic + ": " + message, newEntity);
            }

            verificaDataInclusaoRegistro(newEntity);

            log.info("Salvando registro", newEntity);
            return getRepository().save(newEntity);
        } catch (Exception ex) {
            //TODO Definir exeception
            throw new RuntimeException();
        }
    }

    private void atualizaRegistro(T entityQuery, T newEntity) throws IllegalAccessException {
        Field idEntityQuery = getIdField(entityQuery.getClass());
        Field idNewEntity = getIdField(newEntity.getClass());

        if (!isNull(idEntityQuery) && !isNull(idNewEntity)) {
            idNewEntity.set(newEntity, idEntityQuery.get(entityQuery));
            newEntity.setDataInclusaoRegistro(entityQuery.getDataInclusaoRegistro());
            newEntity.defineDataEdicao();
        } else {
            //TODO Definir exeception
            throw new RuntimeException();
        }
    }

    private Field getIdField(Class entityClass) {
        Field[] fields = entityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true);
                return field;
            }
        }

        if (nonNull(entityClass.getSuperclass()) && entityClass.getSuperclass().isAssignableFrom(BaseEntity.class)) {
            return getIdField(entityClass.getSuperclass());
        } else {
            return null;
        }
    }

    private void verificaDataInclusaoRegistro(T newEntity) {
        if (newEntity.getDataInclusaoRegistro() == null) {
            newEntity.defineDataInclusao();
        }
    }
}
