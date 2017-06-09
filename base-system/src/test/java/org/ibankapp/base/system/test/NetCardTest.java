/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */
package org.ibankapp.base.system.test;

import org.ibankapp.base.system.NetCard;
import org.junit.Assert;
import org.junit.Test;

import java.net.SocketException;
import java.util.Set;

/**
 * 测试网卡工具类
 */
public class NetCardTest {
    public NetCardTest(){
    }
    @Test
    public void testGetMacAddresses()
    {
        NetCard nc = new NetCard();
        Set<String> sets = null;
        try {
            sets = NetCard.getMacAddresses();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        for(String mac:sets)
        {
            Assert.assertTrue(mac.split(":").length==6);
        }

    }
}
