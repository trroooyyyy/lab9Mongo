package org.rekonvald.lab4.service;

import org.rekonvald.lab4.entity.Address;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AddressService {

    private final List<Address> addresses;
    private Long currentId;

    public AddressService() {
        addresses = new ArrayList<>();
        currentId = 1L;
        addresses.add(new Address(currentId++, "Franka", "1A"));
        addresses.add(new Address(currentId++, "Shevchenka", "2B"));
        addresses.add(new Address(currentId++, "Holovna", "3C"));
    }

    public List<Address> getAllAddresses() {
        return addresses;
    }

    public Address getAddressById(Long id) {
        return addresses.stream()
                .filter(address -> address.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Address not found"));
    }

    public Address createAddress(Address address) {
        address.setId(currentId++);
        addresses.add(address);
        return address;
    }

    public Address updateAddress(Address newAddress) {
        Address existingAddress = getAddressById(newAddress.getId());

        existingAddress.setAddress(newAddress.getAddress());
        existingAddress.setApartment(newAddress.getApartment());

        return existingAddress;
    }

    public void deleteAddress(Long id) {
        addresses.remove(getAddressById(id));
    }
}
