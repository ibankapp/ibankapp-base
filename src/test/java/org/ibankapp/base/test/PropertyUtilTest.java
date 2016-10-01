package org.ibankapp.base.test;

import org.ibankapp.base.exception.PropertyUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PropertyUtilTest {

    @Test
    @Before
    public void loadPropertyFile() {
        PropertyUtil.load("test_message.properties");
    }

    @Test
    public void getValueByKey() {

        String message = PropertyUtil.getProperty("E-TEST-000001");
        Assert.assertEquals("无参数测试", message);
    }

    @Test
    public void getValueByNullKey() {
        String message = PropertyUtil.getProperty("E-TEST");
        Assert.assertNull(message);
    }

    @Test
    public void getValueByKeyWithOneParam() {
        String message = PropertyUtil.getProperty("E-TEST-000002", "参数A");
        Assert.assertEquals("一个参数参数A测试", message);
    }

    @Test
    public void getValueByKeyWithTwoParam() {
        String message = PropertyUtil.getProperty("E-TEST-000003", "参数A","参数B");
        Assert.assertEquals("两个参数参数A和参数B测试",message);
    }

    @Test
    public void getValueByKeyWithMultiParam(){
        String[] params = {"参数A","参数B","参数C"};
        String message = PropertyUtil.getProperty("E-TEST-000004",params);
        Assert.assertEquals("多个单数测试参数A,参数B以及参数C",message);
    }
}
