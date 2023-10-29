package com.Controllers;

public class UDPAnswer extends UDPSender {
    private String destIP;

    public UDPAnswer(String myName, String myIP, String destIP, int inPort) {
        super(myName, myIP, inPort);
        this.destIP = destIP;
    }

    public void send() {
        sendMessage(ChatPacket.ActionType.ANSWER, destIP);
    }
}