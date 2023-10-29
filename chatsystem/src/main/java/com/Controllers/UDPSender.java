package com.Controllers;
import com.Models.Host;
import java.net.*;
import java.io.*;

public class UDPSender {

    private String myName;
    private String myIP;
    private String destIP;
    private int destPort;
    
    public UDPSender(String myName, String myIP, String destIP, int destPort) {
        this.myName = myName;
        this.myIP = myIP;   
        this.destIP = destIP;     
        this.destPort = destPort;
    }

    public void send() {
        try {
            DatagramSocket socket = new DatagramSocket();
            int receivePort = 8888; //A CHANGER POUR CHAQUE CONNEXION
            Host localHost = new Host(myName, myIP, receivePort);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(localHost);
            byte[] sendData = byteArrayOutputStream.toByteArray();

            InetAddress destAddress = InetAddress.getByName(destIP);
            DatagramPacket packet = new DatagramPacket(sendData, sendData.length, destAddress, destPort);

            socket.send(packet);
            System.out.println("Message UDP envoyé :\n" + localHost.toString());
            socket.close();
        } 
        
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
