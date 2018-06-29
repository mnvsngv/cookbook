package com.mnvsngv.dao;

import com.google.cloud.datastore.*;
import com.mnvsngv.models.Recipe;
import com.mnvsngv.util.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoogleDatastoreRecipeDao implements RecipeDao {
    private final String ENTITY_KIND = "Recipe";
    private final String NAME = "name";
    private final String SPICES = "spices";
    private final String INGREDIENTS = "ingredients";
    private final String STEPS = "steps";

    private final Datastore DATASTORE;

    public GoogleDatastoreRecipeDao() {
        DATASTORE = DatastoreOptions.getDefaultInstance().getService();
    }

    @Override
    public void addRecipe(Recipe recipe) {
        Key recipeKey = DATASTORE.newKeyFactory().setKind(ENTITY_KIND).newKey(recipe.getName());

        // Prepares the new entity
        Entity recipeEntity = Entity.newBuilder(recipeKey)
                .set(SPICES, Utils.convertListToCsv(recipe.getSpices()))
                .set(INGREDIENTS, Utils.convertListToCsv(recipe.getIngredients()))
                .set(STEPS, recipe.getSteps())
                .build();

        // Saves the entity
        DATASTORE.put(recipeEntity);
    }

    @Override
    public List<Recipe> getAllRecipes() {
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind(ENTITY_KIND)
                .setOrderBy(StructuredQuery.OrderBy.asc("created"))
                .build();
        Iterator<Entity> iterator = DATASTORE.run(query);

        List<Recipe> recipeList = new ArrayList<>();
        iterator.forEachRemaining(entity -> {
            Recipe recipe = new Recipe();
            recipe.setName(entity.getString(NAME));
            recipe.setSteps(entity.getString(STEPS));
            recipe.setIngredients(Utils.convertCsvToList(
                    entity.getString(INGREDIENTS)
            ));
            recipe.setSpices(Utils.convertCsvToList(
                    entity.getString(SPICES)
            ));
        });

        return recipeList;
    }
}
