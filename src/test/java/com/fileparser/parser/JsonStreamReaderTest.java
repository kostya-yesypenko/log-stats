package com.fileparser.parser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.List;

import com.fileparser.model.Order;

import org.junit.jupiter.api.Test;

class JsonStreamReaderTest {

    private final JsonStreamReader parser = new JsonStreamReader();

    @Test
    void testParseSingleFile() throws ParserException {
        File file = new File("src/test/resources/test_data/orders1.json");
        List<Order> orders = parser.parse(file);

        assertNotNull(orders, "Parsed list should not be null");
        assertFalse(orders.isEmpty(), "Parsed list should not be empty");

        // Example check: first order customer name
        assertEquals("Alice", orders.get(0).getCustomer().getName());
    }

    @Test
    void testParseMultipleFiles() throws ParserException {
        File file1 = new File("src/test/resources/test_data/orders1.json");
        File file2 = new File("src/test/resources/test_data/orders2.json");

        List<Order> orders1 = parser.parse(file1);
        List<Order> orders2 = parser.parse(file2);

        assertEquals(3, orders1.size(), "orders1 should have 2 items");
        assertEquals(2, orders2.size(), "orders2 should have 1 item");
    }
}
