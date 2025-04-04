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
        order.setOrderTrackingNumber("101ABC");
        order.setTotalQuantity(5);
        order.setStatus("DELIVERED");
        order.setTotalPrice(new BigDecimal(1500));

        Adress adress = new Adress();
        adress.setCity("Marsillie");
        adress.setStreet("le' conte");
        adress.setState("Fraples");
        adress.setCountry("Fransa");
        adress.setZipCode("F10R257");

        order.setBillingAdress(adress);

         orderRepository.save(order);
    }

    @Test
    void saveAllOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("102ABC");
        order.setTotalQuantity(15);
        order.setStatus("DELIVERED");
        order.setTotalPrice(new BigDecimal(2500));

        Adress adress = new Adress();
        adress.setCity("Istanbul");
        adress.setStreet("Medipol");
        adress.setState("Basaksehir");
        adress.setCountry("Türkiye");
        adress.setZipCode("T10R447");

        order.setBillingAdress(adress);

        Order order2 = new Order();
        order2.setOrderTrackingNumber("103ABC");
        order2.setTotalQuantity(15);
        order2.setStatus("DELIVERED");
        order2.setTotalPrice(new BigDecimal(2500));

        Adress adress2 = new Adress();
        adress2.setCity("Istanbul");
        adress2.setStreet("Boğa");
        adress2.setState("Kadıköy");
        adress2.setCountry("Türkiye");
        adress2.setZipCode("T10R889");

        order2.setBillingAdress(adress2);

        orderRepository.saveAll(List.of(order,order2));

    }

    @Test
    void updateOrderMethod(){
        Order order = orderRepository.findById(1L).get();
        order.setOrderTrackingNumber("103ABC");
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
        orderRepository.deleteById(9L);
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
