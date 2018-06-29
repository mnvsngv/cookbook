package com.mnvsngv.dao;

import com.mnvsngv.models.Recipe;

import java.util.List;

interface RecipeDao {
    void addRecipe(Recipe recipe);
    List<Recipe> getAllRecipes();
}
