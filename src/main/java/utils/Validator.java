package utils;

import utils.exceptions.UnknownValueException;

public class Validator {
    public static String isValid(String value, String valueName) throws UnknownValueException {

        if (value == null || value.equals("")) {
            return "Value " + valueName + " wasn't entered<br><br>";
        }

        try {
            value = value.replace(",", ".");
            double val;
            Double.parseDouble(value);

            if (value.equals("r")) {
                value = handleRadiusValue(value);
            }

            if (value.contains(".") || value.contains(",")) {
                if (value.length() > 8) {
                    val = Double.parseDouble(value.substring(0, 5));
                } else {
                    val = Double.parseDouble(value);
                }
            } else {
                val = Double.parseDouble(value);
            }

            switch (valueName) {
                case "x":
                    if (!(val >= -3 && val <= 5 && val - (int) val == 0)) {
                        return "Value " + valueName + " out of range<br><br>";
                    }
                    break;

                case "y":
                    if (!(val > -5 && val < 3)) {
                        return "Value " + valueName + " out of range<br><br>";
                    }
                    break;

                case "r":
                    if (!(val > 2 && val < 5)) {
                        return "Value " + valueName + " out of range<br><br>";
                    }
                    break;

                default:
                    throw new UnknownValueException("The specified value name is not intended to be used.");

            }

        } catch (NumberFormatException e) {
            return "Value " + valueName + " is not a number.<br><br>";
        }
        return "";
    }

    public static String handleRadiusValue(String value) {
        if (value.length() > 10) {
            char[] charValue = value.toCharArray();
            if (charValue[0] == '1' && charValue[1] == '.') {
                StringBuilder zeroString = new StringBuilder("");

                for (int i = 2; i < charValue.length; i++) {
                    if (charValue[i] == '0') {
                        zeroString.append("0");
                    } else {
                        break;
                    }
                }

                if (zeroString.length() > 10) {
                    return value.replace(zeroString, "");
                }
            }
        }
        return value;
    }
}