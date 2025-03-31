package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Adress;
import com.ogulcan.spring_data_jpa.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.transaction.TransactionScoped;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class OneToOneUnidirectionalMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    void saveOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("102ABC");
        order.setTotalQuantity(5);
        order.setStatus("IN PROGRESS");
        order.setTotalPrice(new BigDecimal(1000));

        Adress adress = new Adress();
        adress.setCity("Istanbul");
        adress.setStreet("Pınartepe");
        adress.setState("Beylikdüzü");
        adress.setCountry("Turkiye");
        adress.setZipCode("101697");

        order.setBillingAdress(adress);

         orderRepository.save(order);
    }

    @Test
    void updateOrderMethod(){
        Order order = orderRepository.findById(8L).get();
        order.setOrderTrackingNumber("101ABC");
        order.setTotalQuantity(5);
        order.setStatus("DELIVERED");
        order.setTotalPrice(new BigDecimal(1000));

        orderRepository.save(order);
    }

    @Test
    void updateOrderAdressMethod(){
        Order order = orderRepository.findById(8L).get();

        Adress adress = order.getBillingAdress();
        adress.setZipCode("101687");

        orderRepository.save(order);
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(10L);
    }

    @Test
    void getOrderMethod(){
        List<Order> orders = orderRepository.findAll();
        orders.forEach(order -> {
            System.out.println(order.getId());
            System.out.println(order.getStatus());
            System.out.println(order.getOrderTrackingNumber());
            System.out.println(order.getTotalQuantity());
            System.out.println(order.getTotalPrice());
            System.out.println(order.getBillingAdress());
        });
    }

    // query method ornegi
    @Test
    void findByStatus() {
        List<Order> orders = orderRepository.findByStatus("DELIVERED");
        System.out.println(orders.toString());
    }
}
