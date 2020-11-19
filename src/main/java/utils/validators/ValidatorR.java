package utils.validators;

public class ValidatorR extends Validator {

    @Override
    public String validate(String value) {

        if (isEmpty(value)) {
            return "Value R wasn't entered<br><br>";
        }

        try {
            int val = Integer.parseInt(value);

            if (!(val > 2 && val < 5)) {
                return "Value R out of range<br><br>";
            }

        } catch (NumberFormatException e) {
            return "Value  R  is incorrect.<br><br>";
        }

        return "";
    }
}
