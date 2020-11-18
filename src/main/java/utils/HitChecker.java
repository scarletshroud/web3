package utils;

public class HitChecker {
    public static boolean isInArea(String firstVal, String secondVal, String thirdVal) {
        try {

            thirdVal = Validator.handleRadiusValue(thirdVal);

            double x = Double.parseDouble(firstVal);
            double y = Double.parseDouble(secondVal);
            double r = Double.parseDouble(thirdVal);

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
