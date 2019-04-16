package net.azurewebsites.mydevlife;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class EitherTest {

    @Test
    void testShouldDivideNumberPerZero() {

        Either<String, Integer> eitherResult = EitherUsercase.divideNumber(10, 0);

        assertFalse(eitherResult.isRight());
        assertTrue(eitherResult.isLeft());
        assertEquals(eitherResult.getLeft(), "Can't divide a number per zero!");
        assertThrows(NoSuchElementException.class, eitherResult::get);

    }

    @Test
    void testShouldDivideNumberExceptPerZero() {

        Either<String, Integer> eitherResult = EitherUsercase.divideNumber(10, 5);

        assertFalse(eitherResult.isLeft());
        assertTrue(eitherResult.isRight());
        assertThrows(NoSuchElementException.class, eitherResult::getLeft);
        assertEquals(eitherResult.get().intValue(), 2);

    }

    @Test
    void testShouldDivideNumberPerZeroUsingFunctionalOperations() {

        Either<String, Integer> eitherResult = EitherUsercase.divideNumber(10, 0);

        assertTrue(eitherResult.right()
                .map(integer -> integer)
                .collect(Collectors.toList())
                .isEmpty());
    }

    @Test
    void testShouldDivideNumberExceptPerZeroUsingFunctionalOperations() {

        Either<String, Integer> eitherResult = EitherUsercase.divideNumber(10, 2);

        assertFalse(eitherResult.right()
                .map(integer -> integer)
                .collect(Collectors.toList())
                .isEmpty());
    }

}
