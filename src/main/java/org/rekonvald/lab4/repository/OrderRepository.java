package org.rekonvald.lab4.repository;

import org.rekonvald.lab4.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}