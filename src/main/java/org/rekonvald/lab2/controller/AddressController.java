package org.rekonvald.lab2.controller;

import org.rekonvald.lab2.entity.Address;
import org.rekonvald.lab2.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressController {
  private final AddressService addressService;

  public AddressController(AddressService addressService) {
    this.addressService = addressService;
  }

  @GetMapping("/address")
  public ResponseEntity<List<Address>> getAddresses() {
    return ResponseEntity.ok(addressService.getAllAddresses());
  }

  @GetMapping("/address/{id}")
  public ResponseEntity<Address> getAddress(@PathVariable String id) {
    return ResponseEntity.ok(addressService.getAddressById(id));
  }

  @PostMapping("/address")
  public ResponseEntity<Address> createAddress(@RequestBody Address address) {
    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(addressService.createAddress(address));
  }

  @PutMapping("/address")
  public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
    return ResponseEntity
            .status(HttpStatus.OK)
            .body(addressService.updateAddress(address));
  }
}