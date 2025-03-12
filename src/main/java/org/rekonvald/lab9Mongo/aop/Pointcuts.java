package org.rekonvald.lab9Mongo.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.rekonvald.lab9Mongo.entity.Order;

public class Pointcuts {

    @Pointcut("execution(* org.rekonvald.lab9Mongo.service.OrderService.createOrder(..))")
    public void createOrderMethod() {}

    @Pointcut("execution(* org.rekonvald.lab9Mongo.service.OrderService.updateOrder(..))")
    public void updateOrderMethod() {}

    @Pointcut("execution(* org.rekonvald.lab9Mongo.controller.order.OrderController.*(..)) && args(order, ..)")
    public void orderControllerMethodsWithOrder(Order order) {}

    @Pointcut("execution(* org.rekonvald.lab9Mongo.controller.order.OrderController.*(..)) && args(id, ..)")
    public void orderControllerMethodsWithId(String id) {}

    @Pointcut(value = "execution(* org.rekonvald.lab9Mongo.controller.order.OrderController.updateOrder(..)) && args(id, order, ..)", argNames = "id, order")
    public void editOrderMethod(String id, Order order) {}
}

