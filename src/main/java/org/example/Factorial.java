package org.example;

import java.math.BigInteger;

public class Factorial {
    static BigInteger factorial(int n) {
        if (n == 0) {
            return BigInteger.ONE;
        }
        return BigInteger.valueOf(n).multiply(factorial(n - 1));
    }
}
