package regapp.util;

import javax.validation.Validation;
import javax.validation.Validator;

public class ValidationUtilImpl implements ValidationUtil {
    private Validator validator;

    public ValidationUtilImpl() {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public <E> boolean isValid(E entity){
        return this.validator.validate(entity).size() == 0;
    }
}
