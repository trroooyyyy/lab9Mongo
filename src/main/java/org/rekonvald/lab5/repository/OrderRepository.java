package org.rekonvald.lab5.repository;

import org.rekonvald.lab5.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}