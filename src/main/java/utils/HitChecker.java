package utils;

import utils.validators.Validator;

public class HitChecker {
    public static boolean isInArea(String firstVal, String secondVal, String thirdVal) {
        try {

            double x = Double.parseDouble(firstVal);
            double y = Double.parseDouble(secondVal);
            int r = Integer.parseInt(thirdVal);

            if ((x > -r / 2 && x < 0 && y > -r && y < 0)
                    || (x > 0 && y <= 0 && x * x + y * y < r / 2 * r / 2)
                    || (x > 0 && y >= 0 && y < r / 2 - x)) {
                return true;
            } else {
                return false;
            }
        } catch(NumberFormatException ex) {
            return false;
        }
    }
}
