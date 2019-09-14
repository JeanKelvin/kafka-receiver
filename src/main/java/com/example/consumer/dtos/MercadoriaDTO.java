package com.example.consumer.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class MercadoriaDTO extends BaseIIDRDTO{

    @SerializedName("CD_MCR")
    private Long idSku;

    @SerializedName("QT_MCR_ATR")
    private Double altura;

    @SerializedName("QT_MCR_LGU")
    private Double largura;

    @SerializedName("QT_MCR_CMT")
    private Double comprimento;

    @SerializedName("QT_MCR_PSO")
    private Double pesoKg;

    @SerializedName("CD_MDLMCR")
    private Integer idTipoTransporte;

    @SerializedName("CD_CLAMCR")
    private Integer idSetor;

    @SerializedName("CD_SETMCR")
    private Integer idDepartamento;

    @SerializedName("CD_ESPMCR")
    private Integer idFamilia;

    @SerializedName("CD_ESPSUB")
    private Integer idSubFamilia;
}