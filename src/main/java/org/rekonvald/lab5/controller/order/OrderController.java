package org.rekonvald.lab5.controller.order;

import lombok.RequiredArgsConstructor;
import org.rekonvald.lab5.entity.Order;
import org.rekonvald.lab5.entity.OrderStatus;
import org.rekonvald.lab5.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order")
    public ResponseEntity<List<Order>> getOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping("/order/status/{id}")
    public ResponseEntity<OrderStatus> getOrderStatus(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderStatus(id));
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(order));
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.updateOrder(id, updatedOrder));
    }

    @PatchMapping("/order/cancel/{id}")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelDelivery(id));
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
