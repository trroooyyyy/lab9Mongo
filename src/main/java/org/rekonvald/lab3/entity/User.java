package org.rekonvald.lab3.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;

    @NotEmpty(message = "Ім'я користувача не може бути порожнім")
    private String username;

    @NotEmpty(message = "Пароль не може бути порожнім")
    private String password;
}
