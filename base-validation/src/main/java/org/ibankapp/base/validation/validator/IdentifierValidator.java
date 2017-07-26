/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.validation.IdentifierValidation;
import org.ibankapp.base.validation.constraint.Identifier;
import org.ibankapp.base.validation.type.Idtp;

/**
 * 证件号码校验注解实现类
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class IdentifierValidator implements ConstraintValidator<Identifier, Object> {

  private String typeFieldName;
  private String codeFieldName;

  @Override
  public void initialize(Identifier constraintAnnotation) {
    this.typeFieldName = constraintAnnotation.typefield();
    this.codeFieldName = constraintAnnotation.codefield();
  }

  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    try {
      final Idtp idtp = (Idtp) PropertyUtils.getProperty(value, typeFieldName);
      final String idno = BeanUtils.getProperty(value, codeFieldName);

      if (idtp.equals(Idtp.IDCARD)) {
        return IdentifierValidation.isIdCardNo(idno);
      }

      if (idtp.equals(Idtp.OCC)) {
        return IdentifierValidation.isOcc(idno);
      }

      if (idtp.equals(Idtp.USCIC)) {
        return IdentifierValidation.isUscic(idno);
      }
    } catch (Exception e) {
      throw new BaseException("E-BASE-000001", e.getMessage()).initCause(e);
    }

    return true;
  }
}
