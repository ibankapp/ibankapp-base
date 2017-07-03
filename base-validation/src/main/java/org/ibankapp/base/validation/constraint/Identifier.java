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

/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.constraint;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import org.ibankapp.base.validation.validator.IdentifierValidator;

/**
 * 证件验证注解
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
@Target({TYPE, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = IdentifierValidator.class)
@Documented
public @interface Identifier {

  /**
   * 错误信息
   *
   * @return 错误信息
   */
  String message() default "{constraints.identifier}";

  /**
   * 分组
   *
   * @return 分组
   */
  Class<?>[] groups() default {};

  /**
   * playload
   *
   * @return playload
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * 证件类型字段
   *
   * @return 证件类型字段
   */
  String typefield();

  /**
   * 证件号码字段
   *
   * @return 证件号码字段
   */
  String codefield();
}
