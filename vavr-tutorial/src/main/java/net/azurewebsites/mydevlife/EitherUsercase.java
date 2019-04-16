package net.azurewebsites.mydevlife;

import io.vavr.control.Either;

public class EitherUsercase {

    private EitherUsercase() {
    }

    public static Either<String, Integer> divideNumber(int numerator, int denominator) {

        if (denominator == 0) {
            return Either.left("Can't divide a number per zero!");
        } else {
            return Either.right(numerator / denominator);
        }

    }

}
