package org.rekonvald.lab5.controller.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.rekonvald.lab5.entity.Address;
import org.rekonvald.lab5.entity.Order;
import org.rekonvald.lab5.entity.OrderStatus;
import org.rekonvald.lab5.entity.User;
import org.rekonvald.lab5.service.AddressService;
import org.rekonvald.lab5.service.OrderService;
import org.rekonvald.lab5.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ui/orders")
@RequiredArgsConstructor
public class OrderUIController {

    private final OrderService orderService;
    private final AddressService addressService;
    private final UserService userService;

    @GetMapping("/")
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.getAllOrders();
        List<Address> addresses = addressService.getAllAddresses();
        List<User> users = userService.getAllUsers();
        Order order = new Order();
        order.setStatus(null);
        order.setAddress(null);
        order.setUser(null);
        model.addAttribute("orders", orders);
        model.addAttribute("addresses", addresses);
        model.addAttribute("users", users);
        model.addAttribute("statuses", OrderStatus.values());
        model.addAttribute("order", order);

        return "orders_list";
    }

    @PostMapping("/add")
    public String addOrder(@Valid @ModelAttribute Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Order> orders = orderService.getAllOrders();
            List<Address> addresses = addressService.getAllAddresses();
            List<User> users = userService.getAllUsers();
            model.addAttribute("orders", orders);
            model.addAttribute("addresses", addresses);
            model.addAttribute("users", users);
            model.addAttribute("statuses", OrderStatus.values());
            model.addAttribute("order", order);
            return "orders_list";
        }

        orderService.createOrder(order);
        return "redirect:/ui/orders/";
    }



    @GetMapping("/edit/{id}")
    public String editOrderForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("order", orderService.getOrderById(id));
        model.addAttribute("addresses", addressService.getAllAddresses());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("statuses", OrderStatus.values());
        return "edit_order";
    }

    @PostMapping("/edit/{id}")
    public String editOrder(@PathVariable("id") Long id, @Valid @ModelAttribute("order") Order order, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("addresses", addressService.getAllAddresses());
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("statuses", OrderStatus.values());
            return "edit_order";
        }

        order.setId(id);
        orderService.updateOrder(id, order);
        return "redirect:/ui/orders/";
    }

    @GetMapping("/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return "redirect:/ui/orders/";
    }

    @GetMapping("/cancel/{id}")
    public String cancelOrder(@PathVariable("id") Long id) {
        orderService.cancelDelivery(id);
        return "redirect:/ui/orders/";
    }
}

