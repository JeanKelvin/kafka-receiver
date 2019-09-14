package com.example.consumer.builders;

import com.example.consumer.models.SkuLojistaEntity;
import lombok.Data;

@Data
public class SkuLojistaEntityBuilder {

    private Long idSku;
    private Long idLojista;

    public SkuLojistaEntity constroi() {
        return new SkuLojistaEntity(idSku, idLojista);
    }

    public SkuLojistaEntityBuilder comSku(Long idSku) {
        this.idSku = idSku;
        return this;
    }

    public SkuLojistaEntityBuilder comLojista(Long idLojista) {
        this.idLojista = idLojista;
        return this;
    }
}
