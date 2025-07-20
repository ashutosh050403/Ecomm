package com.vision.ecomm.controller;

import com.vision.ecomm.dto.OrderDTO;
import com.vision.ecomm.model.OrderRequest;
import com.vision.ecomm.model.User;
import com.vision.ecomm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public OrderDTO placeOrder(@AuthenticationPrincipal User user, @RequestBody OrderRequest orderRequest) {
        return orderService.placeOrder(user.getId(), orderRequest.getProductQuantities(),
                orderRequest.getTotalAmount());
    }

    @GetMapping("/all-orders")
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/user")
    public List<OrderDTO> getOrderByUser(@AuthenticationPrincipal User user) {
        return orderService.getOrderByUser(user.getId());
    }
}