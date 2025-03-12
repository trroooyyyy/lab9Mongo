package org.rekonvald.lab9Mongo.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Address {
    @Id
    private String id;

    @NotEmpty(message = "Адреса не може бути порожньою")
    private String streetAddress;

    @NotEmpty(message = "Номер квартири не може бути порожнім")
    private String apartment;
}