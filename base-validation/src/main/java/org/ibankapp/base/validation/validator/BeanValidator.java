/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.validator;


import org.ibankapp.base.validation.exception.BaseValidationException;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

public class BeanValidator {

    public static <T> void validate(T object) {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        validate(validator, object);
    }

    private static <T> void validate(Validator validator, T object) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);

        Iterator it = constraintViolations.iterator();

        String message = "";

        while (it.hasNext()) {
            @SuppressWarnings("unchecked")
            ConstraintViolation<T> constraintViolation = (ConstraintViolation<T>) it.next();
            message += (constraintViolation.getMessage() + ";");
        }

        if (message.trim().length() != 0) {
            throw new BaseValidationException("E-BASE-VALIDATION-000001", message);
        }
    }
}
