package org.rekonvald.lab3.service;

import org.rekonvald.lab3.entity.Order;
import org.rekonvald.lab3.entity.OrderStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {

    private final List<Order> orders;
    private Long currentId;

    public OrderService() {
        orders = new ArrayList<>();
        currentId = 1L;
        orders.add(new Order(currentId++, "some order desc", OrderStatus.PREPARING, 1L));
        orders.add(new Order(currentId++, "some order desc", OrderStatus.DELIVERED, 2L));
        orders.add(new Order(currentId++, "some order desc", OrderStatus.ON_THE_WAY, 3L));
    }

    public List<Order> getAllOrders() {
        return orders;
    }

    public Order getOrderById(Long id) {
        return orders.stream().filter(order -> order.getId().equals(id)).findFirst().orElseThrow(() -> new NoSuchElementException("Order not found"));
    }

    public Order createOrder(Order order) {
        order.setId(currentId++);
        orders.add(order);
        return order;
    }

    public Order updateOrder(Order newOrder) {
        Order existingOrder = getOrderById(newOrder.getId());

        existingOrder.setDescription(newOrder.getDescription());
        existingOrder.setStatus(newOrder.getStatus());
        existingOrder.setAddressId(newOrder.getAddressId());

        return existingOrder;
    }

    public Order cancelDelivery(Long id) {
        Order order = getOrderById(id);
        order.setStatus(OrderStatus.CANCELLED);
        return order;
    }

    public OrderStatus getOrderStatus(Long id) {
        Order order = getOrderById(id);
        return order.getStatus();
    }

    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orders.remove(order);
    }
}
