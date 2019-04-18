package net.azurewebsites.mydevlife;

import io.vavr.control.Either;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class DivisionUseCaseTest {

    @Test
    void testShouldDivideNumberPerZero() {

        Either<String, Integer> eitherResult = EitherDivisionUseCase.divideNumber(10, 0);

        //You can use isRight or isLeft to check if value is available
        assertFalse(eitherResult.isRight());
        assertTrue(eitherResult.isLeft());

        //The left side contains the error message (using default behaviour)
        assertEquals(eitherResult.getLeft(), "Can't divide a number per zero!");

        //If you call the get() method when no exists value you will receive a NoSuchElementException exception
        assertThrows(NoSuchElementException.class, eitherResult::get);

        Map<String, Object> javaResult = JavaDivisionUseCase.divideNumber(10, 0);
        assertNull(javaResult.get("SUCCESS"));
        assertNotNull(javaResult.get("FAIL"));
        assertEquals(javaResult.get("FAIL"), "Can't divide a number per zero!");

    }

    @Test
    void testShouldDivideNumberExceptPerZero() {

        Either<String, Integer> eitherResult = EitherDivisionUseCase.divideNumber(10, 5);

        assertFalse(eitherResult.isLeft());
        assertTrue(eitherResult.isRight());
        assertThrows(NoSuchElementException.class, eitherResult::getLeft);

        //The right side contains the result of division (using default behaviour)
        assertEquals(eitherResult.get().intValue(), 2);

        Map<String, Object> javaResult = JavaDivisionUseCase.divideNumber(10, 5);
        assertNull(javaResult.get("FAIL"));
        assertNotNull(javaResult.get("SUCCESS"));
        assertEquals(javaResult.get("SUCCESS"), 2);

    }

    @Test
    void testShouldDivideNumberPerZeroUsingFunctionalOperations() {

        Either<String, Integer> eitherResult = EitherDivisionUseCase.divideNumber(10, 0);

        //You can call functional operations such as map, flatmap, filter, etc
        assertTrue(eitherResult
                .map(integer -> integer)
                .collect(Collectors.toList())
                .isEmpty());

        Map<String, Object> javaResult = JavaDivisionUseCase.divideNumber(10, 0);
        assertTrue(javaResult.entrySet()
                .stream()
                .noneMatch(entry -> "SUCCESS".equals(entry.getKey())));
    }

    @Test
    void testShouldDivideNumberExceptPerZeroUsingFunctionalOperations() {

        Either<String, Integer> eitherResult = EitherDivisionUseCase.divideNumber(10, 2);

        //You can call functional operations such as map, flatmap, filter, etc
        assertFalse(eitherResult
                .map(integer -> integer)
                .collect(Collectors.toList())
                .isEmpty());

        Map<String, Object> javaResult = JavaDivisionUseCase.divideNumber(10, 2);

        assertFalse(javaResult.entrySet()
                .stream()
                .noneMatch(entry -> "SUCCESS".equals(entry.getKey())));
    }

}
