package org.rekonvald.lab6.repository;

import org.rekonvald.lab6.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}