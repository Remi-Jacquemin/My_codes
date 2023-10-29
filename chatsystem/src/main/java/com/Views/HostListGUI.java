package com.Views;

import javax.swing.*;
import com.Models.Host;
import com.Models.HostList;
import javax.swing.table.DefaultTableModel;


public class HostListGUI extends JFrame {
    private JTable table;
    private HostList hostList; 

    public HostListGUI(HostList hostList) {
        super("Liste des appareils connectés");
        this.hostList = hostList;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        String[] columnNames = {"Nom d'utilisateur", "Adresse IP", "Port"};
        Object[][] data = new Object[hostList.get_length()][3];

        for (int i = 0; i < hostList.get_length(); i++) {
            Host host = hostList.getHost(i);
            data[i][0] = host.getUsername();
            data[i][1] = host.getIpAdress();
            data[i][2] = host.getPort();
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);

        // Ajouter la table à un JScrollPane pour la faire défiler si nécessaire
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);

        pack();
        setLocationRelativeTo(null); // Centrer la fenêtre
        setVisible(true);
    }

    public void updateTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Efface toutes les lignes actuelles de la table

        for (int i = 0; i < hostList.get_length(); i++) {
            Host host = hostList.getHost(i);
            model.addRow(new Object[]{host.getUsername(), host.getIpAdress(), host.getPort()});
        }
    }
}
