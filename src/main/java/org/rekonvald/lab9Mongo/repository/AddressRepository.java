package org.rekonvald.lab9Mongo.repository;

import org.rekonvald.lab9Mongo.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, String> {
}