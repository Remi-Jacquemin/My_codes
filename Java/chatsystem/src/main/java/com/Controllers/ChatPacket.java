package com.Controllers;
import java.io.Serializable;

import com.Models.Host;

public class ChatPacket implements Serializable {
    public enum ActionType {
        DISCOVERY,       // Action de découverte des hôtes
        ANSWER,          // Action de réponse au broadcast
        USERNAME_CHANGE, // Action de changement d'username
        DISCONNECT       // Action de déconnexion
    }

    private ActionType action;
    private Host host;      // Le nom de l'hôte (pour la découverte)
    //private String newUsername;   // Le nouveau nom d'utilisateur (pour le changement d'username)
    //private String hostAddress;   // L'adresse IP de l'hôte (pour la déconnexion)

    // Constructeur pour l'action DISCOVERY
    public ChatPacket(ActionType action, Host host) {
        this.action = action;
        this.host = host;
    }

    public ActionType getAction() {
        return action;
    }

    public Host getHost() {
        return host;
    }

}
