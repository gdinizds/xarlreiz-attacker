package com.onzecincocincodoet.xarlreizattacker.util;

import lombok.experimental.UtilityClass;

import java.net.InetAddress;
import java.net.UnknownHostException;

@UtilityClass
public class NetworkUtils {

    public static InetAddress resolveAddress(String target) throws UnknownHostException {
        try {
            return InetAddress.getByName(target);
        } catch (UnknownHostException e) {
            throw new UnknownHostException("Unable to resolve address: " + target);
        }
    }

    public static boolean isValidTarget(String target) {
        try {
            resolveAddress(target);
            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
}

