package org.rekonvald.lab3.controller;

import org.rekonvald.lab3.entity.Order;
import org.rekonvald.lab3.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/order/status/{id}")
    public ResponseEntity<String> getOrderStatus(@PathVariable String id) {
        return ResponseEntity.ok(orderService.getOrderStatus(id));
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }

    @PutMapping("/order")
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrder(order));
    }

    @PatchMapping("/order/cancel/{id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelDelivery(id));
    }
}