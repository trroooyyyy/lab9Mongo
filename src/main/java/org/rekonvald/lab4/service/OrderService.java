package org.rekonvald.lab4.service;

import lombok.RequiredArgsConstructor;
import org.rekonvald.lab4.entity.Order;
import org.rekonvald.lab4.entity.OrderStatus;
import org.rekonvald.lab4.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Order with ID " + id + " not found"));
    }

    @Transactional
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateOrder(Long id, Order updatedOrder) {
        Order existingOrder = getOrderById(id);

        existingOrder.setOrderDescription(updatedOrder.getOrderDescription());
        existingOrder.setRestaurantDescription(updatedOrder.getRestaurantDescription());
        existingOrder.setStatus(updatedOrder.getStatus());
        existingOrder.setAddress(updatedOrder.getAddress());
        existingOrder.setUser(updatedOrder.getUser());

        return orderRepository.save(existingOrder);
    }

    @Transactional
    public Order cancelDelivery(Long id) {
        Order order = getOrderById(id);
        order.setStatus(OrderStatus.CANCELLED);
        return orderRepository.save(order);
    }

    @Transactional
    public OrderStatus getOrderStatus(Long id) {
        return getOrderById(id).getStatus();
    }

    @Transactional
    public void deleteOrder(Long id) {
        Order order = getOrderById(id);
        orderRepository.delete(order);
    }
}
