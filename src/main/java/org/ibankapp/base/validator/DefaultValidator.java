package org.ibankapp.base.validator;


import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;

public class DefaultValidator implements IValidator {

    private Validator validator;

    public DefaultValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }


    @Override
    public List<String> validate(Object bean) {
        validator.validate(bean);
        return null;
    }
}
