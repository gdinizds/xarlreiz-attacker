package com.onzecincocincodoet.xarlreizattacker.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
@Schema(description = "Status do ataque")
public class AttackStatus {
    @Schema(description = "Indica se o ataque está em execução")
    boolean running;

    @Schema(description = "Número de pacotes UDP enviados")
    long udpPacketsSent;

    @Schema(description = "Número de pacotes TCP enviados")
    long tcpPacketsSent;
}


