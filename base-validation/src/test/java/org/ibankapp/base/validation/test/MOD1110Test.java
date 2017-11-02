package org.ibankapp.base.validation.test;

import org.ibankapp.base.validation.check.Digit;
import org.ibankapp.base.validation.check.iso7064.MOD1110;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MOD1110Test {

  private Digit digit;

  @Before
  public void setUp() {
    this.digit = new MOD1110();
  }

  @Test
  public void testCompute() {
    Assert.assertEquals(1, digit.compute("19071000004762017072109703195"));
  }

  @Test
  public void testAnotherCompute() {
    Assert.assertEquals(0, digit.compute("19071000004762017072409727811"));
  }

  @Test
  public void testComputeFailed() {
    Assert.assertNotEquals(8, digit.compute("19071000004762017072109703131"));
  }

  @Test
  public void testAnotherComputeFailed() {
    Assert.assertNotEquals(4, digit.compute("190710000047620170721097027873"));
  }

  @Test
  public void testVerifyTrue() {
    Assert.assertTrue(digit.verify("190710000047620170721097031316"));
  }

  @Test
  public void testAnotherVerifyTrue() {
    Assert.assertTrue(digit.verify("190710000047620170721097027873"));
  }

  @Test
  public void testVerifyFalse() {
    Assert.assertFalse(digit.verify("190710000047620170721097031315"));
  }

  @Test
  public void testEncode() {
    Assert.assertEquals("190710000047620170721097031316",digit.encode("19071000004762017072109703131"));
  }
}
