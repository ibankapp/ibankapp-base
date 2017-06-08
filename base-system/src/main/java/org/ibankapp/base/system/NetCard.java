package org.ibankapp.base.system;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 与网卡信息相关的工具类
 */
public class NetCard {
    /**
     * 获取操作系统多网卡列表信息
     * @return 多网卡信息列表，包括mac地址及其他网卡信息
     * @throws SocketException
     */

    public static ArrayList<Map<String, Object>> getMultiCards() throws SocketException {
        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        ArrayList<Map<String, Object>> addresssList = new ArrayList<Map<String, Object>>();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            Enumeration<InetAddress> addresss = ni.getInetAddresses();

            while (addresss.hasMoreElements()) {
                Map<String, Object> map = new HashMap<String, Object>();
                InetAddress ia = addresss.nextElement();
                byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
                if(mac==null)
                    continue;
                //System.out.println("mac数组长度："+mac.length);
                StringBuffer sb = new StringBuffer("");
                for(int i=0; i<mac.length; i++) {
                    if(i!=0) {
                        sb.append("-");
                    }
                    //字节转换为整数
                    int temp = mac[i]&0xff;
                    String str = Integer.toHexString(temp);
//                    System.out.println("每8位:"+str);
                    if(str.length()==1) {
                        sb.append("0"+str);
                    }else {
                        sb.append(str);
                    }
                }
                System.out.println("本机MAC地址:"+sb.toString().toUpperCase());
                map.put("mac",sb.toString().toUpperCase());
                map.put("info",ia);
                addresssList.add(map);
            }
        }
        return addresssList;
    }
}
