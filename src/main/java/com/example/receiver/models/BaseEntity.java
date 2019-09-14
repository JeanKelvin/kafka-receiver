package com.example.receiver.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
public class BaseEntity {

    @Id
    @Field("_id")
    private String id;

    private Date dataObjetoOrigem;

    @Field
    private Date dataInclusaoRegistro;

    @Field
    private Date dataEdicaoRegistro;

    public void defineDataInclusao() {
        this.dataInclusaoRegistro = new Date();
    }
    public void defineDataEdicao() {
        this.dataEdicaoRegistro = new Date();
    }
}
