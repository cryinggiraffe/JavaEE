package com.company.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePage {

    public void placeMeuu(JFrame jFrame, JPanel jPanel){

        JMenuBar jMenuBar = new JMenuBar();

        JMenu config_menu = new JMenu("获取配置");
        JMenuItem ip_mac = new JMenuItem("获取IP及MAC");
        JMenuItem exit = new JMenuItem("退出");
        config_menu.add(ip_mac);
        config_menu.add(exit);

        JMenu network = new JMenu("网络测试");
        JMenuItem connention = new JMenuItem("测试网络连通性");
        network.add(connention);

        jMenuBar.add(config_menu);
        jMenuBar.add(network);

        jFrame.setJMenuBar(jMenuBar);

        ConfigPage configPage = new ConfigPage();
        ip_mac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configPage.placeComponents(jPanel);
            }
        });

    }

    public void placeComponents(JPanel jPanel){

        JLabel userLabel = new JLabel("Hello World!");
        userLabel.setBounds(10,20,80,25);
        jPanel.add(userLabel);
    }
}
