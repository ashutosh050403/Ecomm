package com.vision.ecomm.service;


import com.vision.ecomm.dto.OrderDTO;
import com.vision.ecomm.dto.OrderItemDTO;
import com.vision.ecomm.model.OrderItem;
import com.vision.ecomm.model.Orders;
import com.vision.ecomm.model.Product;
import com.vision.ecomm.model.User;
import com.vision.ecomm.repo.OrderRepository;
import com.vision.ecomm.repo.ProductRepository;
import com.vision.ecomm.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    public OrderDTO placeOrder(Long userId, Map<Long, Integer> productQuantities, double totalAmount) {
                User user = userRepository.findById(userId)
                         .orElseThrow(() -> new RuntimeException("User not found"));

        Orders order = new Orders();
        order.setUser(user);
        order.setOrderDate(new Date());
        order.setStatus("pending");
        order.setTotalAmount(totalAmount);

        List<OrderItem> orderItems=new ArrayList<>();
        List<OrderItemDTO> orderItemDTOS=new ArrayList<>();

        for (Map.Entry<Long, Integer> entry : productQuantities.entrySet()) {

            Product product=productRepository.findById(entry.getKey())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQuantity(entry.getValue());
            orderItems.add(orderItem);

            orderItemDTOS.add(new OrderItemDTO(product.getName(),product.getPrice(),entry.getValue()));
        }

            order.setOrderItems(orderItems);
            Orders saveOrder = orderRepository.save(order);
            return new OrderDTO(saveOrder.getId(),saveOrder.getTotalAmount(),saveOrder.getStatus()
                    ,saveOrder.getOrderDate(),orderItemDTOS);


    }

    public List<OrderDTO> getAllOrders() {
        List<Orders> orders = orderRepository.findAllOrdersWithUsers();
        return orders.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    private OrderDTO convertToDTO(Orders orders) {
        List<OrderItemDTO> orderItems = orders.getOrderItems().stream().map(item -> new OrderItemDTO(
                                            item.getProduct().getName(),
                                            item.getProduct().getPrice(),
                                            item.getQuantity())).collect(Collectors.toList());

        return new OrderDTO(
                orders.getId(),
                orders.getTotalAmount(),
                orders.getStatus(),
                orders.getOrderDate(),
                orders.getUser()!=null?orders.getUser().getName():"unknown",
                orders.getUser()!=null?orders.getUser().getEmail():"unknown",
                orderItems
        );

    }

    public List<OrderDTO> getOrderByUser(Long userId) {
       Optional<User> userOp=userRepository.findById(userId);
       if (userOp.isEmpty()){
           throw new RuntimeException("User not found");
       }
       User user=userOp.get();
       List<Orders> ordersList=orderRepository.findByUser(user);
       return ordersList.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
