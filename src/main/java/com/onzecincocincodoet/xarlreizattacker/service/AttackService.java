package com.onzecincocincodoet.xarlreizattacker.service;

public interface AttackService {
    void sendPackets(String ip, int port);
    long getPacketsSent();
}

