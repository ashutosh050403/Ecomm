package com.vision.ecomm.controller;


import com.vision.ecomm.dto.OrderDTO;
import com.vision.ecomm.model.OrderRequest;
import com.vision.ecomm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place/{userId}")
    public OrderDTO placeOrder(@PathVariable Long userId,@RequestBody OrderRequest orderRequest){

            return orderService.placeOrder(userId,orderRequest.getProductQuantities(),
                    orderRequest.getTotalAmount());

    }

    @GetMapping("/all-orders")
    public List<OrderDTO> getAllOrders(){
        return orderService.getAllOrders();
    }

    @GetMapping("/user/{userId}")
    public List<OrderDTO> getOrderByUser(@PathVariable Long userId){
        return orderService.getOrderByUser(userId);
    }

}
