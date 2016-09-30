package org.ibankapp.base.test;

import org.ibankapp.base.exception.PropertyUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PropertyUtilTest {

    @Test
    @Before
    public void testLoadPropertyFile(){
        PropertyUtil.load("base_message.properties");
    }

    @Test
    public void testGetValueByKey(){

        String message=PropertyUtil.getProperty("E-BASE-000001");
        Assert.assertEquals("系统错误",message);
    }
}
