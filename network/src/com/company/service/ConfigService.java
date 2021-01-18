package com.company.service;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

public class ConfigService {

    public Map<String,String> getIpAndMac(){

        Map<String,String> ip_and_mac = new HashMap<>();

        try {
            InetAddress addr = InetAddress.getLocalHost();
            String IP = addr.getHostAddress().toString();
            ip_and_mac.put("ip",IP);
            ip_and_mac.put("mac",getLocalMac(addr));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ip_and_mac;
    }

    private static String getLocalMac(InetAddress ia) throws SocketException {
        //获取网卡，获取地址
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
        StringBuffer sb = new StringBuffer("");
        for (int i = 0; i < mac.length; i++) {
            if (i != 0) {
                sb.append("-");
            }
            //字节转换为整数
            int temp = mac[i] & 0xff;
            String str = Integer.toHexString(temp);
            //System.out.println("每8位:" + str);
            if (str.length() == 1) {
                sb.append("0" + str);
            } else {
                sb.append(str);
            }
        }
        //System.out.println("本机MAC地址:" + sb.toString().toUpperCase());
        return sb.toString().toUpperCase();
    }

}
