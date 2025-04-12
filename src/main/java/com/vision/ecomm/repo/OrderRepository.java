package com.vision.ecomm.repo;

import com.vision.ecomm.model.Orders;
import com.vision.ecomm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Long> {


    @Query("SELECT o FROM Orders o JOIN FETCH o.user")
    List<Orders> findAllOrdersWithUsers();


    List<Orders> findByUser(User user);
}
