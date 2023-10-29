package com.Controllers;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

import com.Models.Host;
import com.Models.HostList;
import com.Views.HostListGUI;

public class UDPListener implements Runnable{
    private HostList hostList ;
    private String myName;
    private String myIP;
    private HostListGUI gui;

    public UDPListener(String myName, String myIP, HostList hostList, HostListGUI gui) {
        this.myName = myName;
        this.myIP = myIP;  
        this.hostList = hostList;     
        this.gui = gui; 
    }

    public void run() {
        try {
            int destPort = 8888;
            try (DatagramSocket socket = new DatagramSocket(destPort)) {
                System.out.println("[LISTENING]");

                while (true) {
                    byte[] receiveData = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    socket.receive(receivePacket); // Bloquant
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(receivePacket.getData());
                    ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                    Host receivedHost = (Host) objectInputStream.readObject();

                    if (!hostList.is_in_list(receivedHost)){
                        hostList.add_to_list(receivedHost);
                        System.out.println(hostList);

                        gui.updateTable();
                        
                        Thread.sleep(1000);
                        UDPSender sender = new UDPSender(myName, myIP, receivedHost.getIpAdress(), receivedHost.getPort());
                        sender.send();
                    }                    
                }
            } catch (ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
