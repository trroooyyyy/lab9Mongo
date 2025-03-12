package org.rekonvald.lab9Mongo.service;

import lombok.RequiredArgsConstructor;
import org.rekonvald.lab9Mongo.entity.Order;
import org.rekonvald.lab9Mongo.entity.OrderStatus;
import org.rekonvald.lab9Mongo.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Order with ID " + id + " not found"));
    }

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order updateOrder(String id, Order updatedOrder) {
        Order existingOrder = getOrderById(id);

        existingOrder.setOrderDescription(updatedOrder.getOrderDescription());
        existingOrder.setRestaurantDescription(updatedOrder.getRestaurantDescription());
        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setAddress(updatedOrder.getAddress());
        existingOrder.setUser(updatedOrder.getUser());

        return orderRepository.save(existingOrder);
    }

    public Order cancelDelivery(String id) {
        Order order = getOrderById(id);
        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    public OrderStatus getOrderStatus(String id) {
        return getOrderById(id).getStatus();
    }

    public void deleteOrder(String id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
}