package com.example.receiver.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@Document(collection = "skuLojista")
public class SkuLojistaEntity extends BaseEntity {

    private Long idSku;
    private Long idLojista;
    private String idSkuLojista;
    private Long idSkuMarketplace;
    private Long idProdutoMarketplace;
    private Integer tempoSku;

    public SkuLojistaEntity(Long idSku, Long idLojista) {
        this.idSku = idSku;
        this.idLojista = idLojista;
    }
}
