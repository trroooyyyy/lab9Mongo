package org.rekonvald.lab9Mongo.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Order {
    @Id
    private String id;

    @NotEmpty(message = "Опис не може бути порожнім")
    private String orderDescription;

    private String restaurantDescription;

    @NotNull(message = "Статус не може бути порожнім")
    private OrderStatus status;

    @DocumentReference
    @NotNull(message = "Адреса не може бути порожньою")
    private Address address;

    @DocumentReference
    @NotNull(message = "Користувач не може бути порожнім")
    private User user;
}