package org.rekonvald.lab4.service;

import lombok.RequiredArgsConstructor;
import org.rekonvald.lab4.entity.Address;
import org.rekonvald.lab4.repository.AddressRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new RuntimeException("Address not found with id: " + id));
    }

    @Transactional
    public Address createAddress(Address address) {
        if (addressRepository.findAll().stream().anyMatch(existingAddress -> existingAddress.getStreetAddress().equals(address.getStreetAddress()) && existingAddress.getApartment().equals(address.getApartment()))) {
            throw new IllegalArgumentException("Address already exists with the same street and apartment");
        }

        return addressRepository.save(address);
    }

    @Transactional
    public Address updateAddress(Long id, Address newAddress) {
        Address existingAddress = getAddressById(id);

        if (addressRepository.findAll().stream().anyMatch(existing -> existing.getStreetAddress().equals(newAddress.getStreetAddress()) && existing.getApartment().equals(newAddress.getApartment()) && !existing.getId().equals(id))) {
            throw new IllegalArgumentException("Address already exists with the same street and apartment");
        }

        existingAddress.setStreetAddress(newAddress.getStreetAddress());
        existingAddress.setApartment(newAddress.getApartment());

        return addressRepository.save(existingAddress);
    }


    @Transactional
    public void deleteAddress(Long id) {
        if (!addressRepository.existsById(id)) {
            throw new RuntimeException("Address not found with id: " + id);
        }
        addressRepository.deleteById(id);
    }
}
