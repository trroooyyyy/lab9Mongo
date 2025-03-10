package org.rekonvald.lab3.entity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Long id;

    @NotEmpty(message = "Опис не може бути порожнім")
    private String description;

    @NotNull(message = "Статус не може бути порожнім")
    private OrderStatus status;

    @NotNull(message = "Адреса не може бути порожня")
    private Long addressId;
}