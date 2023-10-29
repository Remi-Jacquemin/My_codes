package com.Controllers;

public class UDPDisconnect extends UDPSender {
    public UDPDisconnect(String myName, String myIP, int inPort) {
        super(myName, myIP, inPort);
    }

    public void sendBroadcast() {
        sendMessage(ChatPacket.ActionType.DISCONNECT, "255.255.255.255");
    }
}