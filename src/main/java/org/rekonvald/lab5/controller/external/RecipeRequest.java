package org.rekonvald.lab5.controller.external;

public record RecipeRequest(
        String ingredients,
        int strict,
        String diet
) {}
