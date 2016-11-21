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

package org.ibankapp.base.validations.validators;

import org.ibankapp.base.exceptions.BaseException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

public class BeanValidator {

    public static <T> void validate(T object) {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        validate(validator, object);
    }

    public static <T> void validate(Validator validator, T object) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);

        Iterator it = constraintViolations.iterator();

        String message = "";

        while (it.hasNext()) {
            @SuppressWarnings("unchecked")
            ConstraintViolation<T> constraintViolation = (ConstraintViolation<T>) it.next();
            message += (constraintViolation.getMessage() + ";");
        }

        if (message.trim().length() != 0) {
            throw new BaseException("E-BASE-000005", message);
        }
    }
}
