package org.example;

import org.junit.jupiter.api.Test;

import static org.example.Factorial.factorial;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FactorialTest {
    @Test
    public void testFactorialOf0Is1(){
        assertEquals(1, factorial(0));
    }

    @Test
    public void testFactorialOf1Is1() {
        assertEquals(1, factorial(1));
    }

}
