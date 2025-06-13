package com.onzecincocincodoet.xarlreizattacker.controller;

import com.onzecincocincodoet.xarlreizattacker.dto.AttackRequest;
import com.onzecincocincodoet.xarlreizattacker.dto.AttackStatus;
import com.onzecincocincodoet.xarlreizattacker.enums.Protocol;
import com.onzecincocincodoet.xarlreizattacker.exception.InvalidTargetException;
import com.onzecincocincodoet.xarlreizattacker.service.AttackOrchestrator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attack")
@RequiredArgsConstructor
@Tag(name = "Attack Controller", description = "Endpoints para controle de ataques de pacotes")
public class PacketFloodController {
    private final AttackOrchestrator attackOrchestrator;

    @Operation(
            summary = "Iniciar ataque",
            description = "Inicia um ataque usando o protocolo especificado (UDP ou TCP)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Ataque iniciado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Parâmetros inválidos ou ataque já em andamento")
    })
    @PostMapping(value = "/start" , consumes = "application/json")
    public ResponseEntity<String> startAttack(@Valid @RequestBody AttackRequest request) {
        try {
            Protocol protocol = Protocol.fromString(request.getProtocol());
            attackOrchestrator.startAttack(request.getTarget(), request.getPort(), protocol);
            return ResponseEntity.ok("Attack started against " + request.getTarget() +
                    " using protocol: " + protocol);
        } catch (IllegalArgumentException | IllegalStateException | InvalidTargetException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @Operation(
            summary = "Parar ataque",
            description = "Para o ataque em andamento"
    )
    @ApiResponse(responseCode = "200", description = "Ataque parado com sucesso")
    @PostMapping(value = "/stop")
    public ResponseEntity<String> stopAttack() {
        attackOrchestrator.stopAttack();
        return ResponseEntity.ok("Attack stopped");
    }

    @Operation(
            summary = "Status do ataque",
            description = "Retorna o status atual do ataque"
    )
    @ApiResponse(responseCode = "200", description = "Status obtido com sucesso")
    @GetMapping(value = "/status", produces = "application/json")
    public ResponseEntity<AttackStatus> getStatus() {
        return ResponseEntity.ok(attackOrchestrator.getStatus());
    }
}

