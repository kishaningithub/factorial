package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigInteger;

import static org.example.Factorial.factorial;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {

    @ParameterizedTest
    @CsvSource({
            "0, 1",
            "1, 1",
            "2, 2",
            "3, 6",
            "13, 6227020800",
            "30, 265252859812191058636308480000000"
    })
    public void testFactorial(int n, BigInteger expected) {
        assertEquals(expected, factorial(n));
    }

}
