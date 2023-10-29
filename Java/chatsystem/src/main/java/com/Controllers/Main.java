package com.Controllers;
import java.net.*;

import com.Models.HostList;
import com.Views.HostListGUI;

public class Main {

    public static void main(String[] args) throws UnknownHostException {
        HostList hostList = new HostList();
        
        String myIP = InetAddress.getLocalHost().getHostAddress();
        String myName = myIP; //A changer pour chaque client 

        HostListGUI gui = new HostListGUI(hostList);
        Thread receiverThread = new Thread(new UDPListener(myName, myIP, hostList, gui));
        UDPBroadcastSender sender = new UDPBroadcastSender(myName, myIP);

        receiverThread.start();        
        sender.sendBroadcast();
        
    }
}
