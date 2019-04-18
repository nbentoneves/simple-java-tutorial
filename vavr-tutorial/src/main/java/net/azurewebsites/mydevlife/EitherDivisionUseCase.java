package net.azurewebsites.mydevlife;

import io.vavr.control.Either;

public class EitherDivisionUseCase {

    private EitherDivisionUseCase() {
    }

    public static Either<String, Integer> divideNumber(int num1, int num2) {

        try {
            return Either.right(num1 / num2);
        } catch (ArithmeticException ex) {
            return Either.left("Can't divide a number per zero!");
        } catch (Exception ex) {
            return Either.left("Problem when try to divide the numbers! Reason: " + ex.getMessage());
        }

    }

}
