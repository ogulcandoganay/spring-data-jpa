package com.ogulcan.spring_data_jpa.repository;


import com.ogulcan.spring_data_jpa.entity.Adress;
import com.ogulcan.spring_data_jpa.entity.Order;
import com.ogulcan.spring_data_jpa.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToManyMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    // save order along with also save it's order items

    @Test
    void saveOrderMethod(){

        Order order = new Order();
        order.setOrderTrackingNumber("100ABC");
        order.setStatus("IN_PROGRESS");

        // create order item 1
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(productRepository.findById(1L).get());
        orderItem1.setQuantity(2);
        orderItem1.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(2)));
        orderItem1.setImageUrl("image1.png");
        order.getOrderItems().add(orderItem1);

        // create order item 2
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(productRepository.findById(2L).get());
        orderItem2.setQuantity(3);
        orderItem2.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(3)));
        orderItem2.setImageUrl("image2.png");
        order.getOrderItems().add(orderItem2);

        order.setTotalPrice(order.getTotalPrice());
        order.setTotalQuantity(2);


        Adress adress = new Adress();
        adress.setCity("Pune");
        adress.setStreet("Kothurd");
        adress.setState("Maharashtra");
        adress.setCountry("USA");
        adress.setZipCode("411047");

        order.setBillingAdress(adress);

        orderRepository.save(order);
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(1L);
    }
}
