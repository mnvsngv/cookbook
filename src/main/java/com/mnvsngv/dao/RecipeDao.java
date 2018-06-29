package com.mnvsngv.dao;

import com.mnvsngv.models.Recipe;

import java.util.List;

public interface RecipeDao {
    void addRecipe(Recipe recipe);
    List<Recipe> getAllRecipes();
    List<Recipe> searchRecipes(List<String> spices, List<String> ingredients);
    void deleteRecipe(String recipeName);
}
