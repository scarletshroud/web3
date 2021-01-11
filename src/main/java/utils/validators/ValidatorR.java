package utils.validators;

public class ValidatorR extends Validator {

    @Override
    public boolean validate(String value, StringBuilder message) {

        if (isEmpty(value, "R", message)) {
            return false;
        }

        try {
            int val = Integer.parseInt(value);

            if (!(val >= 1 && val <= 5)) {
                message.append("Value R out of range.\n");
                return false;
            }

        } catch (NumberFormatException e) {
            message.append("Value  R  is incorrect.\n");
            return false;
        }

        return true;
    }
}
