package net.azurewebsites.mydevlife;

import java.util.HashMap;
import java.util.Map;

public class JavaDivisionUseCase {

    private JavaDivisionUseCase() {
    }

    public static Map<String, Object> divideNumber(int num1, int num2) {

        Map<String, Object> result = new HashMap<>();

        try {
            result.put("SUCCESS", num1 / num2);
        } catch (ArithmeticException ex) {
            result.put("FAIL", "Can't divide a number per zero!");
        } catch (Exception ex) {
            result.put("FAIL", "Problem when try to divide the numbers! Reason: " + ex.getMessage());
        }

        return result;
    }

}
