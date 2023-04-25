package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {
    @Test
    public void testAppShouldNotFailIfNoInputIsGiven() {
        assertDoesNotThrow(() -> App.main(new String[]{}));
    }

    @Test
    public void testAppShouldPrintTheFactorialIfTheGivenNumber() {
        ByteArrayOutputStream actual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(actual));

        App.main(new String[]{"6"});

        assertEquals("720\n", actual.toString());
    }
}
