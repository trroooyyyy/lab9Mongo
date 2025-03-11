package org.rekonvald.lab7.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.rekonvald.lab7.entity.Order;

public class Pointcuts {

    @Pointcut("execution(* org.rekonvald.lab7.service.OrderService.createOrder(..))")
    public void createOrderMethod() {}

    @Pointcut("execution(* org.rekonvald.lab7.service.OrderService.updateOrder(..))")
    public void updateOrderMethod() {}

    @Pointcut("execution(* org.rekonvald.lab7.controller.order.OrderUIController.*(..)) && args(order, ..)")
    public void orderControllerMethodsWithOrder(Order order) {}

    @Pointcut("execution(* org.rekonvald.lab7.controller.order.OrderUIController.*(..)) && args(id, ..)")
    public void orderControllerMethodsWithId(Long id) {}

    @Pointcut(value = "execution(* org.rekonvald.lab7.controller.order.OrderUIController.editOrder(..)) && args(id, order, ..)", argNames = "id, order")
    public void editOrderMethod(Long id, Order order) {}
}

