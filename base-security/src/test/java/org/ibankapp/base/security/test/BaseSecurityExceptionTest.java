package org.ibankapp.base.security.test;

import org.ibankapp.base.security.BaseSecurityException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BaseSecurityExceptionTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testBaseSecurityException() {
    thrown.expect(BaseSecurityException.class);

    throw new BaseSecurityException("E-BASE-SECURITY-000003", "通讯");
  }
}
