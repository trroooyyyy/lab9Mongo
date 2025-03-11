package org.rekonvald.lab6.controller.external;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rapidApi")
public class ExternalController {

    private final WebClient.Builder webClientBuilder;

    @Value("${rapidApi.key}")
    private String rapidApiKey;

    @GetMapping("/pizzas/{id}")
    public Mono<ResponseEntity<String>> getPizza(@PathVariable("id") int id) {
        String url = "https://pizza-and-desserts.p.rapidapi.com/pizzas/" + id;

        return webClientBuilder.build()
                .get()
                .uri(url)
                .header("x-rapidapi-key", rapidApiKey)
                .header("x-rapidapi-host", "pizza-and-desserts.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Error fetching data: " + e.getMessage())));
    }

    @PostMapping("/create-recipe")
    public Mono<ResponseEntity<String>> createRecipe(@RequestBody RecipeRequest recipeRequest) {
        String url = "https://recipe-generator-create-custom-recipes-from-your-ingredients.p.rapidapi.com/recipe";

        return webClientBuilder.build()
                .post()
                .uri(url)
                .header("x-rapidapi-key", rapidApiKey)
                .header("x-rapidapi-host", "recipe-generator-create-custom-recipes-from-your-ingredients.p.rapidapi.com")
                .header("Content-Type", "application/json")
                .bodyValue(recipeRequest)
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Error generating recipe: " + e.getMessage())));
    }

    @GetMapping("/chinese-food")
    public Mono<ResponseEntity<String>> getChineseFood() {
        String url = "https://chinese-food-db.p.rapidapi.com/";

        return webClientBuilder.build()
                .get()
                .uri(url)
                .header("x-rapidapi-key", rapidApiKey)
                .header("x-rapidapi-host", "chinese-food-db.p.rapidapi.com")
                .retrieve()
                .bodyToMono(String.class)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> Mono.just(ResponseEntity.status(500).body("Error fetching data: " + e.getMessage())));
    }
}
