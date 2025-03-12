package org.rekonvald.lab9Mongo.entity;

import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    @Id
    private String id;

    @Size(min = 3, max = 50, message = "Ім'я користувача має бути від 3 до 50 символів")
    private String username;

    private String name;

    private String surname;

    @Size(min = 6, max = 100, message = "Пароль має бути від 6 до 100 символів")
    private String password;

    @Size(min = 10, max = 13, message = "Телефон має містити від 10 до 13 символів")
    private String phone;
}