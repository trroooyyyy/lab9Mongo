package org.rekonvald.lab7.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    @Size(min = 3, max = 50, message = "Ім'я користувача має бути від 3 до 50 символів")
    private String username;

    @Column(length = 50)
    private String name;

    @Column(length = 50)
    private String surname;

    @Column(length = 100, nullable = false)
    @Size(min = 6, max = 100, message = "Пароль має бути від 6 до 100 символів")
    private String password;

    @Column(length = 50, unique = true, nullable = false)
    @Size(min = 10, max = 13, message = "Телефон має містити від 10 до 13 символів")
    private String phone;

}
