package com.mnvsngv.dao;

import com.google.cloud.datastore.*;
import com.mnvsngv.models.Recipe;
import com.mnvsngv.util.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GoogleDatastoreRecipeDao implements RecipeDao {
    private final String ENTITY_KIND = "Recipe";
    private final String SPICES = "spices";
    private final String INGREDIENTS = "ingredients";
    private final String STEPS = "steps";

    private final Datastore DATASTORE;
    private final KeyFactory KEY_FACTORY;

    public GoogleDatastoreRecipeDao() {
        DATASTORE = DatastoreOptions.getDefaultInstance().getService();
        KEY_FACTORY = DATASTORE.newKeyFactory().setKind(ENTITY_KIND);

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
                .build();
        Iterator<Entity> iterator = DATASTORE.run(query);

        List<Recipe> recipeList = new ArrayList<>();
        iterator.forEachRemaining(entity -> recipeList.add(convertEntityToRecipe(entity)));

        return recipeList;
    }

    @Override
    public List<Recipe> searchRecipes(List<String> spices, List<String> ingredients) {
        List<Recipe> allRecipes = getAllRecipes();
        List<Recipe> validRecipes = new ArrayList<>();

        for(Recipe recipe : allRecipes) {
            if(recipe.getSpices().containsAll(spices)
                    && recipe.getIngredients().containsAll(ingredients)) {
                validRecipes.add(recipe);
            }
        }

        return validRecipes;
    }

    @Override
    public void deleteRecipe(String recipeName) {
        DATASTORE.delete(KEY_FACTORY.newKey(recipeName));
    }

    private Recipe convertEntityToRecipe(Entity entity) {
        Recipe recipe = new Recipe();

        recipe.setName(entity.getKey().getName());
        recipe.setSteps(entity.getString(STEPS));
        recipe.setIngredients(Utils.convertCsvToList(
                entity.getString(INGREDIENTS)
        ));
        recipe.setSpices(Utils.convertCsvToList(
                entity.getString(SPICES)
        ));

        return recipe;
    }
}
