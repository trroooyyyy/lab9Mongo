package org.rekonvald.lab7.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    @NotEmpty(message = "Адреса не може бути порожньою")
    private String streetAddress;

    @Column(length = 20, nullable = false)
    @NotEmpty(message = "Номер квартири не може бути порожнім")
    private String apartment;
}
