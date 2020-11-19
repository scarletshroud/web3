package utils.validators;

public class ValidatorX extends Validator{

    @Override
    public String validate(String value) {

        if (isEmpty(value)) {
            return "Value X wasn't entered<br><br>";
        }

        try {
            double val = handleValue(value);

            if (!(val >= -3 && val <= 5)) {
                return "Value X out of range<br><br>";
            }
        } catch (NumberFormatException e) {
            return "Value  X  is incorrect.<br><br>";
        }

        return "";
    }
}
