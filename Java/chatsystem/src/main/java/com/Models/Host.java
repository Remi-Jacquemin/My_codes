package com.Models;

import java.io.Serializable;
import java.util.Objects;

public class Host implements Serializable{
    private String username;
    private String ipAdress;
    private int port;
    
    public Host(String username, String ipAdress, int port) {
        this.username = username;
        this.ipAdress = ipAdress;
        this.port = port;
    }

    public String toString(){
        return "Username : " + username +
                ", IP address : " + ipAdress + 
                ", port : " + port + "\n";
    }
    

    public String getUsername() {
        return username;
    }

    public String getIpAdress() {
        return ipAdress;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Host otherHost = (Host) obj;
        return port == otherHost.port
                && Objects.equals(username, otherHost.username)
                && Objects.equals(ipAdress, otherHost.ipAdress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, ipAdress, port);
    }
}

