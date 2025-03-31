package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // ordertracking number'a gore order bulmak icin query method yazıyoruz
    Order findByOrderTrackingNumber(String orderTrackingNumber);

    // Sipariş durumuna göre siparişleri getir
    List<Order> findByStatus(String status);
}
