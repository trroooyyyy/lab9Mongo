package org.rekonvald.lab7.repository;

import org.rekonvald.lab7.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}