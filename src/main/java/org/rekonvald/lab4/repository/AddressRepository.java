package org.rekonvald.lab4.repository;

import org.rekonvald.lab4.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}