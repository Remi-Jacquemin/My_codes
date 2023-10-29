package com.Models;
import java.util.*;


public class HostList {
    
    private static int length = 0;
    private ArrayList<Host> hostList;

    public HostList() {
        hostList = new ArrayList<Host>();
    } 
    
    public ArrayList<Host> gethostList() {
        return hostList;
    }

    public boolean is_in_list(Host host){
        return (hostList.contains(host));
    }
    
    public void add_to_list(Host host) {
        if (!is_in_list(host)){
            hostList.add(host);
            length ++;
        } else {
            throw new NoSuchElementException("Host already in list, can't add");
        }
    }

    public void del_from_list(Host host) {
        if (is_in_list(host)){
            hostList.remove(host);
            length --;
        } else {
            throw new NoSuchElementException("Host not in list, can't delete");
        }
    }
    
    public int get_length(){
        return length;
    }

    public String toString(){
        String result = String.format("Liste des hôtes connectés [%d] : \n", get_length());
        for (Host host : hostList){
            result += host.toString();
        }
        result += "\n";
        return result;
    }

    public Host getHost(int n){
        return hostList.get(n);
    }
}
