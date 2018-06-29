package com.mnvsngv.controllers;

import com.mnvsngv.dao.GoogleDatastoreRecipeDao;
import com.mnvsngv.models.Recipe;
import com.mnvsngv.util.JsonUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class RecipeController {

    @PostMapping("/add")
    public void addRecipe(@RequestBody String body) {
        Recipe recipe = null;
        try {
            recipe = JsonUtils.convertJsonToObject(body, Recipe.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        new GoogleDatastoreRecipeDao().addRecipe(recipe);
    }
}