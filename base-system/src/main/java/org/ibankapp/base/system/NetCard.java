/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */
package org.ibankapp.base.system;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.*;

/**
 * 与网卡相关的工具类
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:liulj@ibankapp.org">esailor</a>
 * @since 1.0.0.0
 */
public class NetCard {


    /**
     * 获取机器上所有网卡的InetAddress信息
     * @return 所有网卡的InetAddress信息列表
     * @throws SocketException 获取网卡列表信息异常时抛出
     */
    public static List<InetAddress> getCardsInfo() throws SocketException {

        Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
        List<InetAddress> addressList = new ArrayList<InetAddress>();
        while (interfaces.hasMoreElements()) {
            NetworkInterface ni = interfaces.nextElement();
            Enumeration<InetAddress> address = ni.getInetAddresses();

            while (address.hasMoreElements()) {
                InetAddress ia = address.nextElement();

                addressList.add(ia);
            }
        }
        return addressList;
    }


    /**
     * 获取Set类型的MAC地址集合
     * @return 网卡mac地址的set集合
     * @throws SocketException 调用getCardsInfo()和获取mac地址时异常时抛出
     */
    public static Set<String> getMacAddresses() throws SocketException {
        Set<String> set = new HashSet<String>();
        List<InetAddress> ias = getCardsInfo();
        for (InetAddress ia:ias)
        {
            byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();
            if(mac==null)
                continue;
            StringBuffer sb = new StringBuffer("");
            for(int i=0; i<mac.length; i++) {
                if(i!=0) {
                    sb.append(":");
                }
                int temp = mac[i]&0xff;
                String str = Integer.toHexString(temp);
                if(str.length()==1) {
                    sb.append("0"+str);
                }else {
                    sb.append(str);
                }
            }
//            System.out.println("本机MAC地址:"+sb.toString());
            set.add(sb.toString());
        }
        return set;
    }
}
