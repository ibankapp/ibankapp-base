/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.test;

import javax.validation.ValidationException;
import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.validation.type.Idtp;
import org.ibankapp.base.validation.validator.BeanValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class IdentifierValidatorTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testIdCard() {
    TestModelWithIdentifier model = new TestModelWithIdentifier();
    Idtp idtp = Idtp.IDCARD;
    model.setIdtp(idtp);
    model.setIdno("130404197602293014");
    BeanValidator.validate(model);
  }

  @Test
  public void testIdCardInvalid() {

    thrown.expect(BaseException.class);
    thrown.expectMessage("证件号码不合法");

    TestModelWithIdentifier model = new TestModelWithIdentifier();
    model.setIdtp(Idtp.IDCARD);
    model.setIdno("130404197602293015");
    BeanValidator.validate(model);
  }

  @Test
  public void testOcc() {
    TestModelWithIdentifier model = new TestModelWithIdentifier();
    model.setIdtp(Idtp.OCC);
    model.setIdno("81852090X");
    BeanValidator.validate(model);
  }

  @Test
  public void testOccInvalid() {

    thrown.expect(BaseException.class);
    thrown.expectMessage("证件号码不合法");

    TestModelWithIdentifier model = new TestModelWithIdentifier();
    model.setIdtp(Idtp.OCC);
    model.setIdno("818520900");
    BeanValidator.validate(model);
  }

  @Test
  public void testUscic() {
    TestModelWithIdentifier model = new TestModelWithIdentifier();
    model.setIdtp(Idtp.USCIC);
    model.setIdno("911202246818640656");
    BeanValidator.validate(model);
  }

  @Test
  public void testUscicInvalid() {

    thrown.expect(BaseException.class);
    thrown.expectMessage("证件号码不合法");

    TestModelWithIdentifier model = new TestModelWithIdentifier();
    model.setIdtp(Idtp.USCIC);
    model.setIdno("911202246818640657");
    BeanValidator.validate(model);
  }

  @Test
  public void testPassport() {
    TestModelWithIdentifier model = new TestModelWithIdentifier();
    model.setIdtp(Idtp.PASSPORT);
    model.setIdno("911202246818640656");
    BeanValidator.validate(model);
  }

  @Test
  public void testBaseException() {

    thrown.expect(ValidationException.class);

    TestModelWithIdentifierError model = new TestModelWithIdentifierError();
    model.setIdtp(Idtp.USCIC);
    model.setIdno("911202246818640656");
    BeanValidator.validate(model);
  }
}
