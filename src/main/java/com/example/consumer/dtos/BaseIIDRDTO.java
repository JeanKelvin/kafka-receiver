package com.example.consumer.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
class BaseIIDRDTO {

    @SerializedName("OBJETO")
    private String objeto;

    @SerializedName("AUD_APPLY_TIMESTAMP")
    private String dataObjetoOrigem;

    @SerializedName("AUD_ENTTYP")
    private String transacao;
}
