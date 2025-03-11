package org.rekonvald.lab7.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.rekonvald.lab7.entity.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RestaurantDescriptionAspect {

    @Before("Pointcuts.createOrderMethod() && args(order)")
    public void setRestaurantDescriptionIfEmpty(Order order) {
        if (order.getRestaurantDescription() == null || order.getRestaurantDescription().isEmpty()) {
            order.setRestaurantDescription("НАЙБЛИЖЧИЙ");
        }
    }

    @Before(value = "Pointcuts.updateOrderMethod() && args(id, updatedOrder)", argNames = "id, updatedOrder")
    public void setRestaurantDescriptionIfEmptyOnUpdate(Long id, Order updatedOrder) {
        if (updatedOrder.getRestaurantDescription() == null || updatedOrder.getRestaurantDescription().isEmpty()) {
            updatedOrder.setRestaurantDescription("НАЙБЛИЖЧИЙ");
        }
    }
}
