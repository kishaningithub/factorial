package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.example.Factorial.factorial;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, 2",
            "3, 6"
    })
    public void testFactorial(int n, int expected) {
        assertEquals(expected, factorial(n));
    }

}
