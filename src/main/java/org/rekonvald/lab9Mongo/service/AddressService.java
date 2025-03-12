package org.rekonvald.lab9Mongo.service;

import lombok.RequiredArgsConstructor;
import org.rekonvald.lab9Mongo.entity.Address;
import org.rekonvald.lab9Mongo.repository.AddressRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(String id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
    }

    public Address createAddress(Address address) {
        boolean addressExists = addressRepository.findAll().stream()
                .anyMatch(existingAddress -> existingAddress.getStreetAddress().equals(address.getStreetAddress())
                        && existingAddress.getApartment().equals(address.getApartment()));

        if (addressExists) {
            throw new IllegalArgumentException("Address already exists with the same street and apartment");
        }

        return addressRepository.save(address);
    }

    public Address updateAddress(String id, Address newAddress) {
        Address existingAddress = getAddressById(id);

        boolean addressExists = addressRepository.findAll().stream()
                .anyMatch(existing -> existing.getStreetAddress().equals(newAddress.getStreetAddress())
                        && existing.getApartment().equals(newAddress.getApartment())
                        && !existing.getId().equals(id));

        if (addressExists) {
            throw new IllegalArgumentException("Address already exists with the same street and apartment");
        }

        existingAddress.setStreetAddress(newAddress.getStreetAddress());
        existingAddress.setApartment(newAddress.getApartment());

        return addressRepository.save(existingAddress);
    }

    public void deleteAddress(String id) {
        if (!addressRepository.existsById(id)) {
            throw new RuntimeException("Address not found with id: " + id);
        }
        addressRepository.deleteById(id);
    }
}