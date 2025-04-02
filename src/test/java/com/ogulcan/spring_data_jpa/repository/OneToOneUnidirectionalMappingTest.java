package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Adress;
import com.ogulcan.spring_data_jpa.entity.Order;
import jakarta.persistence.EntityManager;
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
        order.setOrderTrackingNumber("105ABC");
        order.setTotalQuantity(5);
        order.setStatus("DELIVERED");
        order.setTotalPrice(new BigDecimal(9303));

        Adress adress = new Adress();
        adress.setCity("Marsillie");
        adress.setStreet("le' conte");
        adress.setState("Fraples");
        adress.setCountry("Fransa");
        adress.setZipCode("10S257");

        order.setBillingAdress(adress);

         orderRepository.save(order);
    }

    @Test
    void updateOrderMethod(){
        Order order = orderRepository.findById(1L).get();
        order.setOrderTrackingNumber("102ABC");
        order.setTotalQuantity(5);
        order.setStatus("DELIVERED");
        order.setTotalPrice(new BigDecimal(1000));

        orderRepository.save(order);
    }

    @Test
    void updateOrderAdressMethod(){
        Order order = orderRepository.findById(1L).get();

        Adress adress = order.getBillingAdress();
        adress.setZipCode("101687");
        adress.setCountry("Türkiye");

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

    // custom jpql query
    @Test
    void findByCustomQueryByPriceAndCountry(){
        List<Order> orders = orderRepository.findByCustomQueryByPriceAndCountry(
                new BigDecimal("500"), "Türkiye"
        );
        orders.forEach(order -> {
            System.out.println(order.getId());
            System.out.println(order.getTotalPrice());
            System.out.println(order.getBillingAdress().getCountry());
        });
    }

    // burda da fiyat ve statüs parametrelerine gore kayit getiren query
    @Test
    void findByPriceAndStatus(){
        List<Order> orders = orderRepository.findByPriceAndStatus(new BigDecimal("850"), "DELIVERED");
        orders.forEach(order -> {
            System.out.println(order.getId());
            System.out.println(order.getStatus());
            System.out.println(order.getOrderTrackingNumber());
        });
    }
}
