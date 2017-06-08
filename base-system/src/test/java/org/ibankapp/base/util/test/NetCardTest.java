package org.ibankapp.base.util.test;

import org.ibankapp.base.system.NetCard;
import org.junit.Assert;
import org.junit.Test;

import java.net.SocketException;
import java.util.ArrayList;
import java.util.Map;

/**
 * 测试网卡工具类
 */
public class NetCardTest {
    public NetCardTest() throws SocketException {
    }
    @Test
    public void testGetMultiCards()
    {
        NetCard nc = new NetCard();
        ArrayList<Map<String, Object>> addressList = null;
        try {
            addressList = NetCard.getMultiCards();
        } catch (SocketException e) {
            e.printStackTrace();
        }

        for(Map<String, Object> al:addressList)
        {
            Assert.assertTrue(al.get("mac").toString().split("-").length==6);
        }

    }
}
