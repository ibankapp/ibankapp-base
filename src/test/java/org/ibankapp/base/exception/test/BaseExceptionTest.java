package org.ibankapp.base.exception.test;

import org.ibankapp.base.exception.BaseException;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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

}
