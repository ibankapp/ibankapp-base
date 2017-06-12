/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.exception.test;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.ibankapp.base.exception.BaseException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

/**
 * BaseException测试类
 *
 * @author codelder@ibankapp.org
 * @since 1.0.0.0
 */
public class BaseExceptionTest {

    /**
     * 异常测试RULE
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    /**
     * 测试默认构造函数
     */
    @Test
    public void testBaseException() {
        thrown.expect(BaseException.class);
        throw new BaseException();
    }

    /**
     * 测试不带插值参数的构造函数
     */
    @Test
    public void testBaseExcptionNoParam() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("测试错误1");

        throw new BaseException("E-BASE-TEST01");
    }

    /**
     * 测试带一个插值参数的构造函数
     */
    @Test
    public void testBaseExceptionWithOneParam() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("测试错误:属性不存在");

        throw new BaseException("E-BASE-TEST02", "属性不存在");
    }

    /**
     * 测试获取messageId
     */
    @Test
    public void testGetMessageId() {
        BaseException e = new BaseException("E-BASE-TEST01");
        assertEquals("E-BASE-TEST01", e.getMessageId());
    }

    /**
     * 测试带两个插值参数的构造函数
     */
    @Test
    public void testBaseExceptionWithTwoParams() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("测试错误:参数不存在,原因为:没有输入");

        throw new BaseException("E-BASE-TEST03", "参数不存在", "没有输入");
    }

    /**
     * 测试带多个参数插值的构造函数
     */
    @Test
    public void testBaseExceptionWithMultiParams() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("测试错误:参数不存在,原因为:没有输入,修正方法为:重新输入");

        String[] params = {"参数不存在", "没有输入", "重新输入"};
        throw new BaseException("E-BASE-TEST04", params);
    }

    /**
     * 测试设置原始异常
     */
    @Test
    public void testBaseExceptionInitCause() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("测试错误:原始错误");
        thrown.expectCause(new TypeSafeMatcher<Throwable>() {

            @Override
            protected boolean matchesSafely(Throwable item) {
                return item.getClass().isAssignableFrom(RuntimeException.class)
                        && item.getMessage().contains("原始错误");
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("expects type ")
                        .appendValue(RuntimeException.class)
                        .appendText(" and a message ")
                        .appendValue("原始错误");
            }
        });

        try {
            throw new RuntimeException("原始错误");
        } catch (Exception e) {
            throw new BaseException("E-BASE-TEST02", e.getMessage()).initCause(e);
        }
    }

}
