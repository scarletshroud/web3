package utils.validators;

import utils.exceptions.UnknownValueException;

public abstract class Validator {

    public abstract String validate(String value);

    protected boolean isEmpty(String value) {
        return (value == null || value.equals(""));
    }

    protected double handleValue(String value) {
        if (value.contains(".") || value.contains(",")) {
            value = value.replace(",", ".");
            if (value.length() > 8) {
                return Double.parseDouble(value.substring(0, 5));
            }
        }
        return Double.parseDouble(value);
    }

}