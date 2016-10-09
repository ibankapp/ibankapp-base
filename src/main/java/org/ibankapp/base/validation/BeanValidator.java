package org.ibankapp.base.validation;

import org.ibankapp.base.exception.BaseException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Iterator;
import java.util.Set;

public class BeanValidator {
    public static <T> void validate(T object){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);

        Iterator it = constraintViolations.iterator();

        String message = "";

        while (it.hasNext()){
            @SuppressWarnings("unchecked")
            ConstraintViolation<T> constraintViolation = (ConstraintViolation<T>) it.next();
            message+=(constraintViolation.getMessage()+";");
        }

        if(message.trim().length()!=0){
            throw new BaseException("E-BASE-000005",message);
        }
    }
}
