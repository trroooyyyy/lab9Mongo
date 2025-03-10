package org.rekonvald.lab4.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
    private Long id;

    @NotEmpty(message = "Адреса не може бути порожньою")
    private String address;

    @NotEmpty(message = "Номер квартири не може бути порожнім")
    private String apartment;
}