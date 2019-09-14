package com.example.consumer.builders;

import com.example.consumer.models.DimensaoEntity;
import com.example.consumer.models.EstruturaEntity;
import com.example.consumer.models.SkuEntity;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class SkuEntityBuilder {

    private Long idSku;
    private Double pesoKg;
    private Integer idTipoTransporte;
    private DimensaoEntity dimensaoEntity;
    private EstruturaEntity estruturaEntity;
    private Date dataObjetoOrigem;

    public SkuEntity constroi() {
        return new SkuEntity(idSku, pesoKg, idTipoTransporte, dimensaoEntity, estruturaEntity, dataObjetoOrigem);
    }

    public SkuEntityBuilder comSku(Long idSku) {
        this.idSku = idSku;
        return this;
    }

    public SkuEntityBuilder comPeso(Double pesoKg) {
        this.pesoKg = pesoKg;
        return this;
    }

    public SkuEntityBuilder comTipoTransporte(Integer idTipoTransporte) {
        this.idTipoTransporte = idTipoTransporte;
        return this;
    }

    public SkuEntityBuilder comDimensao(DimensaoEntity dimensaoEntity) {
        this.dimensaoEntity = dimensaoEntity;
        return this;
    }

    public SkuEntityBuilder comEstrutura(EstruturaEntity estruturaEntity) {
        this.estruturaEntity = estruturaEntity;
        return this;
    }

    public SkuEntityBuilder comDataObjetoOrigem(String dataObjetoOrigem) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss");
            this.dataObjetoOrigem = format.parse(dataObjetoOrigem);
            return this;
        } catch (ParseException e) {
            //TODO CRIAR EXCEPTION
            throw new RuntimeException();
        }
    }
}
