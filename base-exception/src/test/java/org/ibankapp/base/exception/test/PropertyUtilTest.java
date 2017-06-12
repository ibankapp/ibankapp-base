/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.exception.test;

import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.exception.PropertyUtil;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.isA;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * PropertyUtil测试类
 *
 * @author codelder@ibankapp.org
 * @version 1.0.0.0
 */
public class PropertyUtilTest {

    /**
     * 异常测试RULE
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /**
     * 测试根据key从属性文件中读取value
     */
    @Test
    public void testGetValueByKey() {
        String message = PropertyUtil.getProperty("E-TEST-000001");

        assertEquals("无参数测试", message);
    }

    /**
     * 测试多个插值为null的情况下根据key从属性文件中生成value
     */
    @Test
    public void testGetValueByKeyWithMultiNullParam() {
        String message = PropertyUtil.getProperty("E-TEST-000004", (String[]) null);

        assertEquals("多个参数测试{0},{1}以及{2}", message);
    }

    /**
     * 测试多个插值的情况下根据key从属性文件中生成value
     */
    @Test
    public void testGetValueByKeyWithMultiParam() {
        String[] params = {"参数A", "参数B", "参数C"};
        String message = PropertyUtil.getProperty("E-TEST-000004", params);

        assertEquals("多个参数测试参数A,参数B以及参数C", message);
    }

    /**
     * 测试一个插值且为null的情况下根据key从属性文件中生成value
     */
    @Test
    public void testGetValueByKeyWithOneNullParam() {
        String message = PropertyUtil.getProperty("E-TEST-000002", (String) null);

        assertEquals("一个参数{0}测试", message);
    }

    /**
     * 测试一个插值的情况下根据key从属性文件中生成value
     */
    @Test
    public void testGetValueByKeyWithOneParam() {
        String message = PropertyUtil.getProperty("E-TEST-000002", "参数A");

        assertEquals("一个参数参数A测试", message);
    }

    /**
     * 测试两个插值的情况下根据key从属性文件中生成value
     */
    @Test
    public void testGetValueByKeyWithTwoParam() {
        String message = PropertyUtil.getProperty("E-TEST-000003", "参数A", "参数B");

        assertEquals("两个参数参数A和参数B测试", message);
    }

    /**
     * 测试两个插值且第一个插值为null的情况下根据key从属性文件中生成value
     */
    @Test
    public void testGetValueByKeyWithTwoParamFirstNull() {
        String message = PropertyUtil.getProperty("E-TEST-000003", null, "参数B");

        assertEquals("两个参数null和参数B测试", message);
    }


    /**
     * 测试两个插值且第一个插值为null的情况下根据key从属性文件中生成value
     */
    @Test
    public void testGetValueByKeyWithTwoParamSecondNull() {
        String message = PropertyUtil.getProperty("E-TEST-000003", "参数A", null);

        assertEquals("两个参数参数A和null测试", message);
    }

    /**
     * 测试当key不存在在属性文件中时应抛出异常
     */
    @Test
    public void testGetValueByNotExistKey() {
        String message = PropertyUtil.getProperty("E-TEST");
        assertNull(message);
    }

    /**
     * 测试两个插值情况下当key不存在在属性文件中时应抛出异常
     */
    @Test
    public void testGetValueByNotExistKeyWithTwoParam() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("属性keyE-TEST不存在");

        PropertyUtil.getProperty("E-TEST", "参数A", "参数B");
    }

    /**
     * 测试当key值为null时应抛出异常
     */
    @Test
    public void testGetValueByNullKey() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("key值必须输入");
        PropertyUtil.getProperty(null);
    }

    /**
     * 测试多个插值的情况下当key值为null时应抛出异常
     */
    @Test
    public void testGetValueByNullKeyWithMultiParam() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("key值必须输入");

        String[] params = {"参数A", "参数B", "参数C"};

        PropertyUtil.getProperty(null, params);
    }

    /**
     * 测试一个插值的情况下当key值为null时应抛出异常
     */
    @Test
    public void testGetValueByNullKeyWithOneParam() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("key值必须输入");
        PropertyUtil.getProperty(null, "参数A");
    }

    /**
     * 测试加载一个不存在的属性文件时应抛出异常
     */
    @Test
    public void testLoadNotExistPropertyFile() {
        thrown.expect(BaseException.class);
        thrown.expectCause(isA(NullPointerException.class));
        PropertyUtil.load("noexist.properties");
    }

    /**
     * 测试加载属性文件
     */
    @Test
    @Before
    public void testLoadPropertyFile() {
        PropertyUtil.load("test_message.properties");
    }

    /**
     * 测试创建PropertyUtil对象
     */
    @Test
    public void testNewPropertyUtil() {
        PropertyUtil util = new PropertyUtil();

        assertNotNull(util);
    }
}
