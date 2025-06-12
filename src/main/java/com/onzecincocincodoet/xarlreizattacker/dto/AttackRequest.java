package com.onzecincocincodoet.xarlreizattacker.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Dados para requisição de ataque")
public class AttackRequest {
    @NotBlank(message = "Target address cannot be empty")
    @Schema(description = "Endereço IP ou URL do alvo",
            example = "example.com ou 192.168.1.1",
            required = true)
    private String target;

    @Min(value = 1, message = "Port must be greater than 0")
    @Max(value = 65535, message = "Port must be less than 65536")
    @Schema(description = "Porta do alvo", example = "80",
            minimum = "1", maximum = "65535",
            required = true)
    private int port;

    @NotBlank(message = "Protocol cannot be empty")
    @Schema(description = "Protocolo a ser usado (UDP ou TCP)",
            example = "UDP",
            allowableValues = {"UDP", "TCP"},
            required = true)
    private String protocol;
}



