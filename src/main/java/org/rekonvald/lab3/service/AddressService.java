package org.rekonvald.lab3.service;

import org.rekonvald.lab3.entity.Address;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AddressService {

    private final List<Address> addresses;

    public AddressService() {
        addresses = new ArrayList<>();
        addresses.add(new Address("1", "Franka", "1A"));
        addresses.add(new Address("2", "Shevchenka", "2B"));
        addresses.add(new Address("3", "Holovna", "3C"));
    }

    public List<Address> getAllAddresses() {
        return addresses;
    }

    public Address getAddressById(String id) {
        return addresses.stream().filter(status -> status.getId().equals(id)).findFirst().orElseThrow(NoSuchElementException::new);
    }

    public Address createAddress(Address address) {
        addresses.add(address);

        return address;
    }

    public Address updateAddress(Address newAddress) {
        Address address = getAddressById(newAddress.getId());

        address.setAddress(newAddress.getAddress());
        address.setApartment(newAddress.getApartment());

        return address;
    }
}