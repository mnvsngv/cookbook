package com.mnvsngv.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mnvsngv.dao.GoogleDatastoreRecipeDao;
import com.mnvsngv.models.Recipe;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;

@RestController
public class RecipeController {

    @PostMapping("/add")
    public void greeting(@RequestBody String body) {
        Gson gson = new Gson();
        Type recipeType = new TypeToken<Recipe>() {}.getType();

        Recipe recipe = gson.fromJson(body, recipeType);
        new GoogleDatastoreRecipeDao().addRecipe(recipe);
    }
}