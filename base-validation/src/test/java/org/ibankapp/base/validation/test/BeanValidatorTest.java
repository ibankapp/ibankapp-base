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

import static org.junit.Assert.assertNotNull;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.validation.validator.BeanValidator;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BeanValidatorTest {

  /**
   * 异常测试RULE.
   */
  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Test
  public void testValidateFail() {

    thrown.expect(BaseException.class);
    thrown.expectMessage("名称不能为空;");
    thrown.expectMessage("ID不能为NULL;");

    ValidationTestBean bean = new ValidationTestBean();
    BeanValidator.validate(bean);
  }

  @Test
  public void testValidatePass() {

    ValidationTestBean bean = new ValidationTestBean();
    bean.setId("123");
    bean.setName("name");
    BeanValidator.validate(bean);
  }

  @Test
  public void testNewValidator() {

    BeanValidator validator = new BeanValidator();
    assertNotNull(validator);
  }

  @Test
  public void parameterValidatorTest() {
    validationFunction("111");
  }

  private void validationFunction(@Max(value = 2, message = "最大长度为2") String para1) {

    BeanValidator.validate(para1);
  }

  private static class ValidationTestBean {

    private String id;
    private String name;

    @NotNull(message = "ID不能为NULL")
    @SuppressWarnings("unused")
    public String getId() {
      return id;
    }

    void setId(String id) {
      this.id = id;
    }

    @NotEmpty(message = "名称不能为空")
    @SuppressWarnings("unused")
    public String getName() {
      return name;
    }

    void setName(String name) {
      this.name = name;
    }
  }
}
