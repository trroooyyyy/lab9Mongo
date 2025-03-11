package org.rekonvald.lab6.repository;

import org.rekonvald.lab6.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}