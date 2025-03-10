package org.rekonvald.lab4.controller.address;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rekonvald.lab4.entity.Address;
import org.rekonvald.lab4.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ui/addresses")
@RequiredArgsConstructor
public class AddressUIController {

    private final AddressService addressService;

    @GetMapping("/")
    public String getAllAddresses(Model model) {
        List<Address> addresses = addressService.getAllAddresses();
        model.addAttribute("addresses", addresses);
        model.addAttribute("address", new Address());
        return "addresses_list";
    }

    @PostMapping("/add")
    public String addAddress(@Valid @ModelAttribute Address address, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Address> addresses = addressService.getAllAddresses();
            model.addAttribute("addresses", addresses);
            return "addresses_list";
        }

        addressService.createAddress(address);
        return "redirect:/ui/addresses/";
    }

    @GetMapping("/edit/{id}")
    public String editAddressForm(@PathVariable("id") Long id, Model model) {
        Address address = addressService.getAddressById(id);
        model.addAttribute("address", address);
        return "edit_address";
    }

    @PostMapping("/edit/{id}")
    public String editAddress(@PathVariable("id") Long id,@Valid @ModelAttribute("address") Address address, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Address> addresses = addressService.getAllAddresses();
            model.addAttribute("addresses", addresses);
            return "edit_address";
        }

        address.setId(id);
        addressService.updateAddress(address);
        return "redirect:/ui/addresses/";
    }

    @GetMapping("/delete/{id}")
    public String deleteAddress(@PathVariable("id") Long id) {
        addressService.deleteAddress(id);
        return "redirect:/ui/addresses/";
    }
}
