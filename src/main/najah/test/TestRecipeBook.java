package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import main.najah.code.Recipe;
import main.najah.code.RecipeBook;

@DisplayName("RecipeBook Test Class")
@Execution(ExecutionMode.CONCURRENT)
class TestRecipeBook {

    private RecipeBook recipeBook;

    @BeforeEach
    void setUp() {
        recipeBook = new RecipeBook();
        System.out.println("RecipeBook created");
    }

    private Recipe makeRecipe(String name) {
        Recipe recipe = new Recipe();
        recipe.setName(name);
        return recipe;
    }

    @Test
    @DisplayName("Initial recipe array size should be 4")
    void testGetRecipesInitialSize() {
        Recipe[] recipes = recipeBook.getRecipes();

        assertEquals(4, recipes.length);
        assertNotNull(recipes);
    }

    @Test
    @DisplayName("Add recipe successfully")
    void testAddRecipeValid() {
        Recipe recipe = makeRecipe("Latte");

        boolean added = recipeBook.addRecipe(recipe);
        Recipe[] recipes = recipeBook.getRecipes();

        assertTrue(added);
        assertEquals("Latte", recipes[0].getName());
    }

    @Test
    @DisplayName("Adding duplicate recipe should fail")
    void testAddDuplicateRecipe() {
        Recipe recipe1 = makeRecipe("Mocha");
        Recipe recipe2 = makeRecipe("Mocha");

        boolean firstAdd = recipeBook.addRecipe(recipe1);
        boolean secondAdd = recipeBook.addRecipe(recipe2);

        assertTrue(firstAdd);
        assertFalse(secondAdd);
    }

    @Test
    @DisplayName("Add recipe should fail when recipe book is full")
    void testAddRecipeWhenFull() {
        assertTrue(recipeBook.addRecipe(makeRecipe("R1")));
        assertTrue(recipeBook.addRecipe(makeRecipe("R2")));
        assertTrue(recipeBook.addRecipe(makeRecipe("R3")));
        assertTrue(recipeBook.addRecipe(makeRecipe("R4")));

        boolean added = recipeBook.addRecipe(makeRecipe("R5"));

        assertFalse(added);
        assertEquals(4, recipeBook.getRecipes().length);
    }

    @Test
    @DisplayName("Delete existing recipe should return recipe name")
    void testDeleteRecipeValid() {
        recipeBook.addRecipe(makeRecipe("Espresso"));

        String deletedName = recipeBook.deleteRecipe(0);

        assertEquals("Espresso", deletedName);
        assertEquals("", recipeBook.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Delete non-existing recipe should return null")
    void testDeleteRecipeInvalid() {
        String deletedName = recipeBook.deleteRecipe(0);

        assertNull(deletedName);
    }

    @Test
    @DisplayName("Edit existing recipe should return old recipe name")
    void testEditRecipeValid() {
        Recipe oldRecipe = makeRecipe("Cappuccino");
        Recipe newRecipe = makeRecipe("Americano");

        recipeBook.addRecipe(oldRecipe);
        String editedName = recipeBook.editRecipe(0, newRecipe);

        assertEquals("Cappuccino", editedName);
        assertEquals("", recipeBook.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("Edit non-existing recipe should return null")
    void testEditRecipeInvalid() {
        Recipe newRecipe = makeRecipe("Flat White");

        String editedName = recipeBook.editRecipe(0, newRecipe);

        assertNull(editedName);
    }

    @ParameterizedTest
    @DisplayName("Parameterized add recipe test")
    @CsvSource({
        "Latte",
        "Mocha",
        "Tea"
    })
    void testAddRecipeParameterized(String recipeName) {
        Recipe recipe = makeRecipe(recipeName);

        boolean added = recipeBook.addRecipe(recipe);

        assertTrue(added);
        assertEquals(recipeName, recipeBook.getRecipes()[0].getName());
    }

    @Test
    @DisplayName("RecipeBook methods should execute within timeout")
    void testTimeout() {
        assertTimeout(Duration.ofMillis(200), () -> {
            recipeBook.addRecipe(makeRecipe("QuickCoffee"));
            recipeBook.getRecipes();
        });
    }
}