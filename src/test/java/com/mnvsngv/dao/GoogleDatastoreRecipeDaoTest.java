package com.mnvsngv.dao;

import com.mnvsngv.models.Recipe;
import org.junit.Test;

public class GoogleDatastoreRecipeDaoTest {

    @Test(expected = IllegalArgumentException.class)
    public void addEmptyRecipe() {
        RecipeDao dao = new GoogleDatastoreRecipeDao();
        dao.addRecipe(new Recipe());
    }

    @Test
    public void getAllRecipes() {
    }
}