package utils.validators;

public class ValidatorY extends Validator{

    @Override
    public String validate(String value) {

        if (isEmpty(value)) {
            return "Value Y wasn't entered<br><br>";
        }

        try {
            double val = handleValue(value);

            if (!(val > -5 && val < 3)) {
                return "Value Y out of range<br><br>";
            }
        } catch (NumberFormatException e) {
            return "Value  Y  is incorrect.<br><br>";
        }

        return "";

    }
}
