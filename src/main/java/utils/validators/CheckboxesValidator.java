package utils.validators;

public class CheckboxesValidator {
    public static boolean validate(boolean[] checkboxes, String value, StringBuilder message) {
        for (int i = 0; i < checkboxes.length; i++) {

            if (checkboxes[i]) {
                Integer val = i + 1;
                value = val.toString();
                break;
            }

            if (i == checkboxes.length - 1) {
                message.append("Value R isn't entered. ");
                return false;
            }
        }
        return true;
    }
}
