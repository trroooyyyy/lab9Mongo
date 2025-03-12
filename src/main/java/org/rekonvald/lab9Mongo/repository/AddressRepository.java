package org.rekonvald.lab9Mongo.repository;

import org.rekonvald.lab9Mongo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}