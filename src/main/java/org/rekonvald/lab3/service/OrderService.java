package org.rekonvald.lab3.service;

import org.rekonvald.lab3.entity.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    private final List<Order> orders;

    public OrderService() {
        orders = new ArrayList<>();
        orders.add(new Order("1", "On the way", "1"));
        orders.add(new Order("2", "Preparing", "2"));
        orders.add(new Order("3", "Delivered", "3"));
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(String id) {
        return orders.stream().filter(status -> status.getId().equals(id)).findFirst().orElseThrow(NoSuchElementException::new);
    }

    public Order createOrder(Order order) {
        orders.add(order);

        return order;
    }

    public Order updateOrder(Order newOrder) {
        Order order = getOrderById(newOrder.getId());

        order.setStatus(newOrder.getStatus());
        order.setAddressId(newOrder.getAddressId());

        return order;
    }

    public Order cancelDelivery(String id) {
        Order order = getOrderById(id);

        order.setStatus("Cancelled");

        return order;
    }

    public String getOrderStatus(String id) {
        Order order = getOrderById(id);
        return order.getStatus();
    }
}