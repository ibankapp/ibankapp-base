/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.constraint;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.ibankapp.base.validation.validator.OccValidator;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = OccValidator.class)
@Documented
public @interface Occ {

  /**
   * 获取 错误信息.
   *
   * @return 错误信息
   */
  String message() default "{constraints.occ}";

  /**
   * 分组.
   *
   * @return 分组
   */
  Class<?>[] groups() default {};

  /**
   * payload.
   *
   * @return payload
   */
  Class<? extends Payload>[] payload() default {};
}
