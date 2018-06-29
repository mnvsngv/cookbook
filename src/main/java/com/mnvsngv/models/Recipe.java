package com.mnvsngv.models;

import java.util.List;

public class Recipe {
    private String name;
    private List<String> ingredients;
    private List<String> spices;
    private String steps;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public List<String> getSpices() {
        return spices;
    }

    public void setSpices(List<String> spices) {
        this.spices = spices;
    }

    public String getSteps() {
        return steps;
    }

    public void setSteps(String steps) {
        this.steps = steps;
    }
}
