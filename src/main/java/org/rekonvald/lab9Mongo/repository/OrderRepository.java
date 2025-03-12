package org.rekonvald.lab9Mongo.repository;

import org.rekonvald.lab9Mongo.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
}