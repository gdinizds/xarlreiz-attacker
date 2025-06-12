package com.onzecincocincodoet.xarlreizattacker.enums;

public enum Protocol {
    UDP, TCP;

    public static Protocol fromString(String protocol) {
        try {
            return valueOf(protocol.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid protocol. Use 'UDP' or 'TCP'");
        }
    }
}

