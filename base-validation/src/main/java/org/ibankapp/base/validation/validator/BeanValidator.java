/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.validator;

import java.util.Iterator;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.ibankapp.base.exception.BaseValidationException;

/**
 * Bean校验类
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class BeanValidator {

  /**
   * 校验传入的bean是否合法.
   *
   * @param object 传入的bean
   * @param <T> bean的类型
   */
  public static <T> void validate(T object) {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    validate(validator, object);
  }

  /**
   * 校验传入的bean是否合法.
   *
   * @param validator 校验器
   * @param object 传入的bean
   * @param <T> bean的类型
   */
  private static <T> void validate(Validator validator, T object) {
    Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);

    Iterator it = constraintViolations.iterator();

    StringBuilder message = new StringBuilder();

    while (it.hasNext()) {
      @SuppressWarnings("unchecked")
      ConstraintViolation<T> constraintViolation = (ConstraintViolation<T>) it.next();
      message.append(constraintViolation.getMessage()).append(";");
    }

    if (message.toString().trim().length() != 0) {
      throw new BaseValidationException("E-BASE-VALIDATION-000001", message.toString());
    }
  }
}
