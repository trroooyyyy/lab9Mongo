package org.rekonvald.lab7.controller.external;

public record RecipeRequest(
        String ingredients,
        int strict,
        String diet
) {}
