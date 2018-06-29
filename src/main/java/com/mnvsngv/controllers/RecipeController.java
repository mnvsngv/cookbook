package com.mnvsngv.controllers;

import com.mnvsngv.dao.GoogleDatastoreRecipeDao;
import com.mnvsngv.models.Recipe;
import com.mnvsngv.util.JsonUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecipeController {

    @PostMapping("/add")
    public void greeting(@RequestBody String body) {
        Recipe recipe = JsonUtils.convertJsonToObject(body, Recipe.class);
        new GoogleDatastoreRecipeDao().addRecipe(recipe);
    }
}