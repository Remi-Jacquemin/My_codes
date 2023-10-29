package com.Controllers;

public class UDPDiscovery extends UDPSender {
    public UDPDiscovery(String myName, String myIP, int inPort) {
        super(myName, myIP, inPort);
    }

    public void sendBroadcast() {
        sendMessage(ChatPacket.ActionType.DISCOVERY, "255.255.255.255");
    }
}