package org.rekonvald.lab6.controller.external;

public record RecipeRequest(
        String ingredients,
        int strict,
        String diet
) {}
