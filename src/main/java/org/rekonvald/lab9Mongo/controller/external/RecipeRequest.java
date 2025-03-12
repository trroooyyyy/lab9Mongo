package org.rekonvald.lab9Mongo.controller.external;

public record RecipeRequest(
        String ingredients,
        int strict,
        String diet
) {}
