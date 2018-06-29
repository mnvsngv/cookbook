package com.mnvsngv.controllers;

import com.mnvsngv.dao.GoogleDatastoreRecipeDao;
import com.mnvsngv.dao.RecipeDao;
import com.mnvsngv.models.Recipe;
import com.mnvsngv.util.JsonUtils;
import com.mnvsngv.util.Utils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class RecipeController {
    private static final RecipeDao recipeDao = new GoogleDatastoreRecipeDao();

    @PostMapping("/add")
    public void addRecipe(@RequestBody String body) {
        Recipe recipe = null;
        try {
            recipe = JsonUtils.convertJsonToObject(body, Recipe.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        recipeDao.addRecipe(recipe);
    }

    @GetMapping("/getAll")
    public List<Recipe> getAllRecipes() {
        return recipeDao.getAllRecipes();
    }

    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam(value = "spices") String spices,
                                      @RequestParam(value = "ingredients") String ingredients) {
        return recipeDao.searchRecipes(Utils.convertCsvToList(spices),
                Utils.convertCsvToList(ingredients));
    }

    @DeleteMapping("/delete/{name}")
    public void deleteRecipe(@PathVariable(value = "name") String recipeName) {
        recipeDao.deleteRecipe(recipeName);
    }
}