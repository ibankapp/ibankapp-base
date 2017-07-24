package org.ibankapp.base.security.test;

import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.security.BaseSecurityException;
import org.junit.Test;

/**
 * Created by llj on 17/6/13.
 */
public class BaseSecurityExceptionTest {
    @Test
    public void testBaseSecurityException()
    {
        try {
            throw new BaseSecurityException("E-BASE-SECURITY-000003","通讯");
        } catch (Exception e) {

    }

    }
}
