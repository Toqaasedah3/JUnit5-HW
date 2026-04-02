package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.najah.code.Recipe;
import main.najah.code.RecipeException;

@DisplayName("Recipe Test Class")
class TestRecipe {

    @Test
    @DisplayName("Default recipe values should be zero or empty")
    void testDefaultConstructor() {
        Recipe recipe = new Recipe();

        assertEquals("", recipe.getName());
        assertEquals(0, recipe.getPrice());
        assertEquals(0, recipe.getAmtCoffee());
        assertEquals(0, recipe.getAmtMilk());
        assertEquals(0, recipe.getAmtSugar());
        assertEquals(0, recipe.getAmtChocolate());
    }

    @Test
    @DisplayName("Set valid price")
    void testSetPriceValid() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setPrice("10");

        assertEquals(10, recipe.getPrice());
    }

    @Test
    @DisplayName("Set invalid price text should throw exception")
    void testSetPriceInvalidText() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setPrice("abc"));
    }

    @Test
    @DisplayName("Set negative price should throw exception")
    void testSetPriceNegative() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setPrice("-1"));
    }

    @Test
    @DisplayName("Set valid coffee amount")
    void testSetCoffeeValid() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setAmtCoffee("5");

        assertEquals(5, recipe.getAmtCoffee());
    }

    @Test
    @DisplayName("Set invalid coffee text should throw exception")
    void testSetCoffeeInvalidText() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("bad"));
    }

    @Test
    @DisplayName("Set negative coffee should throw exception")
    void testSetCoffeeNegative() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setAmtCoffee("-1"));
    }

    @Test
    @DisplayName("Set valid milk amount")
    void testSetMilkValid() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setAmtMilk("3");

        assertEquals(3, recipe.getAmtMilk());
    }

    @Test
    @DisplayName("Set invalid milk text should throw exception")
    void testSetMilkInvalidText() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setAmtMilk("milk"));
    }

    @Test
    @DisplayName("Set negative milk should throw exception")
    void testSetMilkNegative() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setAmtMilk("-1"));
    }

    @Test
    @DisplayName("Set valid sugar amount")
    void testSetSugarValid() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setAmtSugar("2");

        assertEquals(2, recipe.getAmtSugar());
    }

    @Test
    @DisplayName("Set invalid sugar text should throw exception")
    void testSetSugarInvalidText() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setAmtSugar("sugar"));
    }

    @Test
    @DisplayName("Set negative sugar should throw exception")
    void testSetSugarNegative() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setAmtSugar("-1"));
    }

    @Test
    @DisplayName("Set valid chocolate amount")
    void testSetChocolateValid() throws RecipeException {
        Recipe recipe = new Recipe();
        recipe.setAmtChocolate("4");

        assertEquals(4, recipe.getAmtChocolate());
    }

    @Test
    @DisplayName("Set invalid chocolate text should throw exception")
    void testSetChocolateInvalidText() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setAmtChocolate("choco"));
    }

    @Test
    @DisplayName("Set negative chocolate should throw exception")
    void testSetChocolateNegative() {
        Recipe recipe = new Recipe();

        assertThrows(RecipeException.class, () -> recipe.setAmtChocolate("-1"));
    }

    @Test
    @DisplayName("Set name with valid string")
    void testSetNameValid() {
        Recipe recipe = new Recipe();
        recipe.setName("Latte");

        assertEquals("Latte", recipe.getName());
        assertEquals("Latte", recipe.toString());
    }

    @Test
    @DisplayName("Set name with null should keep old value")
    void testSetNameNull() {
        Recipe recipe = new Recipe();
        recipe.setName("Mocha");
        recipe.setName(null);

        assertEquals("Mocha", recipe.getName());
    }

    @Test
    @DisplayName("Equals should return true for same object")
    void testEqualsSameObject() {
        Recipe recipe = new Recipe();

        assertTrue(recipe.equals(recipe));
    }

    @Test
    @DisplayName("Equals should return false for null")
    void testEqualsNull() {
        Recipe recipe = new Recipe();

        assertFalse(recipe.equals(null));
    }

    @Test
    @DisplayName("Equals should return false for different class")
    void testEqualsDifferentClass() {
        Recipe recipe = new Recipe();

        assertFalse(recipe.equals("not a recipe"));
    }

    @Test
    @DisplayName("Equals should return true for same name")
    void testEqualsSameName() {
        Recipe r1 = new Recipe();
        r1.setName("Coffee");

        Recipe r2 = new Recipe();
        r2.setName("Coffee");

        assertTrue(r1.equals(r2));
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    @DisplayName("Equals should return false for different names")
    void testEqualsDifferentNames() {
        Recipe r1 = new Recipe();
        r1.setName("Coffee");

        Recipe r2 = new Recipe();
        r2.setName("Tea");

        assertFalse(r1.equals(r2));
    }
}