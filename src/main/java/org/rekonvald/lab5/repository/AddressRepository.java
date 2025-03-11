package org.rekonvald.lab5.repository;

import org.rekonvald.lab5.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}