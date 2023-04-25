package org.example;

import static org.example.Factorial.factorial;

public class App {
    public static void main(String[] args) {
        if (args.length == 0){
            return;
        }
        int n = Integer.parseInt(args[0]);
        System.out.println(factorial(n));
    }
}
