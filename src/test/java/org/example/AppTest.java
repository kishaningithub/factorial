package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AppTest {
    @Test
    public void testAppShouldNotFailIfNoInputIsGiven() {
        assertDoesNotThrow(App::main);
    }
}
