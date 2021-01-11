package utils.validators;

public class ValidatorX extends Validator{

    @Override
    public boolean validate(String value, StringBuilder message) {

        if (isEmpty(value, "X", message)) {
            return false;
        }

        try {
            double val = handleValue(value);

            if (!(val >= -3 && val <= 5)) {
                message.append("Value X out of range.\n");
                return false;
            }
        } catch (NumberFormatException e) {
            message.append("Value  X  is incorrect.\n");
            return false;
        }

        return true;
    }
}
