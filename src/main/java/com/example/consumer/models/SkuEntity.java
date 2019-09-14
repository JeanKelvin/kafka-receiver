package com.example.consumer.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "sku")
public class SkuEntity extends BaseEntity {

    private Long idSku;
    private Double pesoKg;
    private Integer idTipoTransporte;
    private DimensaoEntity dimensao;
    private EstruturaEntity estrutura;


    public SkuEntity(Long idSku, Double pesoKg, Integer idTipoTransporte, DimensaoEntity dimensao, EstruturaEntity estrutura, Date dataObjetoOrigem) {
        this.idSku = idSku;
        this.pesoKg = pesoKg;
        this.idTipoTransporte = idTipoTransporte;
        this.dimensao = dimensao;
        this.estrutura = estrutura;
        super.setDataObjetoOrigem(dataObjetoOrigem);
    }
}
