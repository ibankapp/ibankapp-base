/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.validation.IdentifierValidation;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IdentifierValidationTest {

  /**
   * 异常测试RULE.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testIdentifierValidator() {

    new IdentifierValidation();

    assertTrue(IdentifierValidation.isIdCardNo("130404197602293014"));
    assertTrue(IdentifierValidation.isIdCardNo("37250119830110781X"));
    assertFalse(IdentifierValidation.isIdCardNo("130404195009143025"));
    assertFalse(IdentifierValidation.isIdCardNo("1304041947032830A4"));
    assertFalse(IdentifierValidation.isIdCardNo("130404201701013014"));
    assertFalse(IdentifierValidation.isIdCardNo("130404197502293014"));
    assertFalse(IdentifierValidation.isIdCardNo("170404198703240001"));
    assertFalse(IdentifierValidation.isIdCardNo("130404760229301"));
    assertFalse(IdentifierValidation.isIdCardNo("13040419760229301A"));
    assertFalse(IdentifierValidation.isIdCardNo("110105201801020129"));
    assertTrue(IdentifierValidation.isOcc("685104709"));
    assertTrue(IdentifierValidation.isOcc("MA06A7081"));
    assertTrue(IdentifierValidation.isOcc("103939950"));
    assertTrue(IdentifierValidation.isOcc("81852090X"));
    assertFalse(IdentifierValidation.isOcc("81852090A"));
    assertFalse(IdentifierValidation.isOcc("818520903"));
    assertFalse(IdentifierValidation.isOcc("68510470-9"));
    assertFalse(IdentifierValidation.isOcc("685104708"));
    assertFalse(IdentifierValidation.isOcc("6851047X8"));
    assertTrue(IdentifierValidation.isOcc("M00010RN6"));
    assertTrue(IdentifierValidation.isUscic("911202246818640656"));
    assertTrue(IdentifierValidation.isUscic("9112010223967930XT"));
    assertFalse(IdentifierValidation.isUscic("911202246818640657"));
    assertFalse(IdentifierValidation.isUscic("91120224681864065"));
    assertFalse(IdentifierValidation.isUscic("111202246818640657"));
    assertFalse(IdentifierValidation.isUscic("511202246818640657"));
    assertFalse(IdentifierValidation.isUscic("Y11202246818640657"));
    assertFalse(IdentifierValidation.isUscic("Y21202246818640657"));
    assertFalse(IdentifierValidation.isUscic("Y112C2246818640657"));
    assertFalse(IdentifierValidation.isUscic("Y11202246818640647"));
    assertFalse(IdentifierValidation.isUscic("211202246818640657"));
    assertFalse(IdentifierValidation.isUscic("91350212M0010RN6D"));
    assertTrue(IdentifierValidation.isUscic("91350200MA2Y0ERQ4U"));
    assertFalse(IdentifierValidation.isUscic("91350212M00010RN6D"));
  }

  @Test
  public void testGetIdCardNoCheckBitLengthError() {
    thrown.expect(BaseException.class);
    IdentifierValidation.getIdCardNoCheckBit("1304041976022930");
  }

  @Test
  public void testGetIdCardNoCheckBitCharNotNumber() {
    thrown.expect(BaseException.class);
    IdentifierValidation.getIdCardNoCheckBit("1304041976022930A");
  }

  @Test
  public void testGetOccCheckBitLengthError() {
    thrown.expect(BaseException.class);
    IdentifierValidation.getOccCheckBit("1304041976022930");
  }

  @Test
  public void testGetOccCheckBitIllegalChar() {
    thrown.expect(BaseException.class);
    IdentifierValidation.getOccCheckBit("12-34566");
  }

  @Test
  public void testGetUscicCheckBitIllegalChar() {
    thrown.expect(BaseException.class);
    IdentifierValidation.getUscicCheckBit("91-2010223967930X");
  }

  @Test
  public void testGetUscicCheckBitLengthError() {
    thrown.expect(BaseException.class);
    IdentifierValidation.getUscicCheckBit("1304041976022930");
  }

}
