package org.rekonvald.lab9Mongo.repository;

import org.rekonvald.lab9Mongo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}