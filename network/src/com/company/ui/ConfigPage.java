package com.company.ui;

import com.company.service.ConfigService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ConfigPage {

    JButton jButton;

    public void placeComponents(JPanel jPanel){

        jPanel.removeAll();
        jPanel.repaint();
        jButton = new JButton("demo");
        JLabel IpAndMac = new JLabel("");
        IpAndMac.setSize(200, 0);
        jPanel.add(jButton);
        jPanel.add(IpAndMac);
        jPanel.revalidate();

        ConfigService configService = new ConfigService();



        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Map<String, String> ip_and_mac = new HashMap<>();
                ip_and_mac = configService.getIpAndMac();
                StringBuffer resultsb = new StringBuffer();
                resultsb.append("<html>");
                resultsb.append("ip：" + ip_and_mac.get("ip"));
                resultsb.append("<br>");
                resultsb.append("mac："+ip_and_mac.get("mac"));
                resultsb.append("</html>");

                IpAndMac.setText(resultsb.toString());
            }
        });

    }


}
