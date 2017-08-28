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
import org.ibankapp.base.validation.IdentifierValidation;
import org.ibankapp.base.validation.constraint.Occ;

public class OccValidator implements ConstraintValidator<Occ, String> {

  public void initialize(Occ constraint) {
  }

  public boolean isValid(String occ, ConstraintValidatorContext context) {
    return IdentifierValidation.isOcc(occ);
  }
}
