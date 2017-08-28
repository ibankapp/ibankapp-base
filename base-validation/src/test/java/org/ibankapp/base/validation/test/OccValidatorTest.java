/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.test;

import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.validation.validator.BeanValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OccValidatorTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testOcc() {
    TestModelWithOcc model = new TestModelWithOcc();
    model.setOcc("81852090-X");
    BeanValidator.validate(model);
    model.setOcc("81852090X");
    BeanValidator.validate(model);
  }

  @Test
  public void testOccInvalid() {
    thrown.expect(BaseException.class);
    thrown.expectMessage("组织机构代码不合法");

    TestModelWithOcc model = new TestModelWithOcc();
    model.setOcc("81852090AX");
    BeanValidator.validate(model);
  }

  @Test
  public void testOccInvalid1() {
    thrown.expect(BaseException.class);
    thrown.expectMessage("组织机构代码不合法");

    TestModelWithOcc model = new TestModelWithOcc();
    model.setOcc("81852091X");
    BeanValidator.validate(model);
  }

  @Test
  public void testOccInvalid2() {
    thrown.expect(BaseException.class);
    thrown.expectMessage("组织机构代码不合法");

    TestModelWithOcc model = new TestModelWithOcc();
    model.setOcc("81852091-X");
    BeanValidator.validate(model);
  }

  @Test
  public void testOccInvalid3() {
    thrown.expect(BaseException.class);
    thrown.expectMessage("组织机构代码不合法");

    TestModelWithOcc model = new TestModelWithOcc();
    model.setOcc("818520912");
    BeanValidator.validate(model);
  }

}
