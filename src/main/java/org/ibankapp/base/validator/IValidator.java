package org.ibankapp.base.validator;

import java.util.List;

public interface IValidator {

    List<String> validate(Object bean);
}
