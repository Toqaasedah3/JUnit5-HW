package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.najah.code.Product;

@DisplayName("Product Test Class")
class ProductTest {

    @Test
    @DisplayName("Create product with valid name and price")
    void testConstructorValid() {
        Product p = new Product("Book", 50);

        assertEquals("Book", p.getName());
        assertEquals(50, p.getPrice());
        assertEquals(0, p.getDiscount());
    }

    @Test
    @DisplayName("Final price without discount should equal price")
    void testFinalPriceNoDiscount() {
        Product p = new Product("Pen", 20);

        assertEquals(20, p.getFinalPrice());
    }

    @Test
    @DisplayName("Apply valid discount")
    void testApplyValidDiscount() {
        Product p = new Product("Phone", 100);
        p.applyDiscount(10);

        assertEquals(10, p.getDiscount());
        assertEquals(90, p.getFinalPrice());
    }

    @Test
    @DisplayName("Apply zero discount should not change price")
    void testApplyZeroDiscount() {
        Product p = new Product("Notebook", 30);
        p.applyDiscount(0);

        assertEquals(0, p.getDiscount());
        assertEquals(30, p.getFinalPrice());
    }

    @Test
    @DisplayName("Apply negative discount should throw exception")
    void testApplyNegativeDiscount() {
        Product p = new Product("Tablet", 200);

        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(-5));
    }

    @Test
    @DisplayName("Apply discount greater than 50 should throw exception")
    void testApplyDiscountAbove50() {
        Product p = new Product("Laptop", 500);

        assertThrows(IllegalArgumentException.class, () -> p.applyDiscount(51));
    }

    @Test
    @DisplayName("Boundary test for maximum allowed discount (50%)")
    void testMaxAllowedDiscount() {
        Product p = new Product("Gift", 40);
        p.applyDiscount(50);

        assertEquals(50, p.getDiscount());
        assertEquals(20, p.getFinalPrice());
    }

    @Test
    @DisplayName("Multiple assertions test")
    void testMultipleAssertions() {
        Product p = new Product("Bag", 80);
        p.applyDiscount(20);

        assertAll(
            () -> assertEquals("Bag", p.getName()),
            () -> assertEquals(80, p.getPrice()),
            () -> assertEquals(20, p.getDiscount()),
            () -> assertEquals(64, p.getFinalPrice())
        );
    }
}