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
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;


public class BaseExceptionTest {

    /**
     * 异常测试RULE
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void testBaseException() {
        thrown.expect(BaseException.class);
        throw new BaseException();
    }

    @Test
    public void testBaseExceptionWithOneParam() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("属性keysomekey不存在");

        throw new BaseException("E-BASE-000003", "somekey");
    }

    @Test
    public void testGetMessageId() {
        BaseException e = new BaseException("E-BASE-000001");
        assertEquals("E-BASE-000001", e.getMessageId());
    }

    @Test
    public void testBaseExceptionWithTwoParams() {
        BaseException e =new BaseException("E-BASE-000001","参数A","参数B");
        assertEquals("E-BASE-000001", e.getMessageId());
        assertEquals("参数A",e.getMessage());
    }

    @Test
    public void testBaseExceptionWithMultiParams() {
        String[] params = {"参数A","参数B","参数C"};
        BaseException e =new BaseException("E-BASE-000001",params);
        assertEquals("E-BASE-000001", e.getMessageId());
    }

}
