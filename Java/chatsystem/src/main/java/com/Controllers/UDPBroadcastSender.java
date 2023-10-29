package com.Controllers;
import com.Models.Host;
import java.net.*;
import java.io.*;

public class UDPBroadcastSender {

    private String myName;
    private String myIP;
    
    public UDPBroadcastSender(String myName, String myIP) {
        this.myName = myName;
        this.myIP = myIP;        
    }

    public void sendBroadcast() {
        try {
            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);
            int sendPort = 8888;
            int receivePort = 8888; //A CHANGER POUR CHAQUE CONNEXION
            Host localHost = new Host(myName, myIP, receivePort);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(localHost);
            byte[] sendData = byteArrayOutputStream.toByteArray();

            InetAddress broadcastAddress = InetAddress.getByName("255.255.255.255");
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, broadcastAddress, sendPort);

            socket.send(packet);
            System.out.println("Broadcast envoyé :\n" + localHost.toString());
            socket.close();
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
