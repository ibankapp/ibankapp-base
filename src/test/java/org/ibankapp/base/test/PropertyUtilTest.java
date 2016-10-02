package org.ibankapp.base.test;

import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.utils.PropertyUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

import static org.hamcrest.CoreMatchers.isA;

public class PropertyUtilTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    @Before
    public void testLoadPropertyFile() {
        PropertyUtil.load("test_message.properties");
    }

    @Test
    public void testNewPropertyUtil(){
        PropertyUtil util = new PropertyUtil();
        Assert.assertNotNull(util);
    }

    @Test
    public void testLoadNotExistPropertyFile(){
        thrown.expect(BaseException.class);
        thrown.expectCause(isA(NullPointerException.class));

        PropertyUtil.load("noexist.properties");
    }

    @Test
    public void testGetValueByKey() {

        String message = PropertyUtil.getProperty("E-TEST-000001");
        Assert.assertEquals("无参数测试", message);
    }

    @Test
    public void testGetValueByNotExistKey() {
        String message = PropertyUtil.getProperty("E-TEST");
        Assert.assertNull(message);
    }

    @Test
    public void testGetValueByNullKey() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("key值必须输入");

        PropertyUtil.getProperty(null);

    }

    @Test
    public void testGetValueByKeyWithOneNullParam() {
        String message = PropertyUtil.getProperty("E-TEST-000002", (String) null);
        Assert.assertEquals("一个参数{0}测试", message);
    }

    @Test
    public void testGetValueByNullKeyWithOneParam() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("key值必须输入");

        PropertyUtil.getProperty(null, "参数A");

    }

    @Test
    public void testGetValueByKeyWithOneParam() {
        String message = PropertyUtil.getProperty("E-TEST-000002", "参数A");
        Assert.assertEquals("一个参数参数A测试", message);
    }

    @Test
    public void testGetValueByKeyWithTwoParam() {
        String message = PropertyUtil.getProperty("E-TEST-000003", "参数A", "参数B");
        Assert.assertEquals("两个参数参数A和参数B测试", message);
    }

    @Test
    public void testGetValueByKeyWithTwoParamFirstNull() {
        String message = PropertyUtil.getProperty("E-TEST-000003", null, "参数B");
        Assert.assertEquals("两个参数{0}和{1}测试", message);
    }

    @Test
    public void testGetValueByNotExistKeyWithTwoParam() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("属性keyE-TEST不存在");

        PropertyUtil.getProperty("E-TEST", "参数A", "参数B");
    }

    @Test
    public void testGetValueByKeyWithMultiParam() {
        String[] params = {"参数A", "参数B", "参数C"};
        String message = PropertyUtil.getProperty("E-TEST-000004", params);
        Assert.assertEquals("多个单数测试参数A,参数B以及参数C", message);
    }

    @Test
    public void testGetValueByNullKeyWithMultiParam() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("key值必须输入");

        String[] params = {"参数A", "参数B", "参数C"};
        PropertyUtil.getProperty(null, params);
    }

    @Test
    public void testGetValueByKeyWithMultiNullParam() {
        String message = PropertyUtil.getProperty("E-TEST-000004", (String[]) null);
        Assert.assertEquals("多个单数测试{0},{1}以及{2}", message);
    }
}
