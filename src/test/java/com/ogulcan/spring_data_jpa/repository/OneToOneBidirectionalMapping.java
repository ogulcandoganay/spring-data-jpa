package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Adress;
import com.ogulcan.spring_data_jpa.entity.Order;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Modifying;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class OneToOneBidirectionalMapping {

    @Autowired
    private AdressRepository adressRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    void saveAdressMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("106ABC");
        order.setTotalQuantity(5);
        order.setStatus("DELIVERED");
        order.setTotalPrice(new BigDecimal(2500));

        Adress adress = new Adress();
        adress.setCity("Monaco");
        adress.setStreet("Parc de Princess");
        adress.setState("Fraples");
        adress.setCountry("Fransa");
        adress.setZipCode("10S257");

        order.setBillingAdress(adress);
        adress.setOrder(order);

        adressRepository.save(adress);
    }

    @Test
    void saveAllAdressMethod(){

        Order order = new Order();
        order.setOrderTrackingNumber("113ABC");
        order.setTotalQuantity(5);
        order.setStatus("DELIVERED");
        order.setTotalPrice(new BigDecimal(700));

        Adress adress = new Adress();
        adress.setCity("Istanbul");
        adress.setStreet("Kadıköy");
        adress.setState("TR");
        adress.setCountry("Türkiye");
        adress.setZipCode("AB1234");

        order.setBillingAdress(adress);
        adress.setOrder(order);

        Order order2 = new Order();
        order2.setOrderTrackingNumber("114ABC");
        order2.setTotalQuantity(5);
        order2.setStatus("DELIVERED");
        order2.setTotalPrice(new BigDecimal(600));

        Adress adress2 = new Adress();
        adress2.setCity("Istanbul");
        adress2.setStreet("Beşiktaş");
        adress2.setState("TR");
        adress2.setCountry("Türkiye");
        adress2.setZipCode("AB1234");

        order2.setBillingAdress(adress2);
        adress2.setOrder(order2);

        Order order3 = new Order();
        order3.setOrderTrackingNumber("115ABC");
        order3.setTotalQuantity(5);
        order3.setStatus("CANCELLED");
        order3.setTotalPrice(new BigDecimal(650));

        Adress adress3 = new Adress();
        adress3.setCity("Istanbul");
        adress3.setStreet("Seyrantepe");
        adress3.setState("TR");
        adress3.setCountry("Türkiye");
        adress3.setZipCode("AB1234");

        order3.setBillingAdress(adress3);
        adress3.setOrder(order3);

        adressRepository.saveAll(List.of(adress,adress2,adress3));
    }

    @Test
    void updateZipCodeAdressMethod(){
        Adress adress = adressRepository.findById(3L).get();
        adress.setZipCode("ID40236");
        adressRepository.save(adress);
    }

    @Test
    void updateOrderPriceMethod(){
        Order order = orderRepository.findById(4L).get();
        order.setTotalPrice(new BigDecimal(4350));
        orderRepository.save(order);
    }

    @Test
    void fetchAdressMethod(){
        Adress adress = adressRepository.findById(1L).get();
    }

    @Test
    void deleteAdressMethod(){
        adressRepository.deleteById(3L);
    }

    @Test
    void deleteByCountryTest() {
        // "Fransa" ülkesine sahip tüm adresleri sil
        adressRepository.deleteByCountry("Fransa");
    }

    @Test
    void findByCustomRequestAndUpdate() {
        int updatedCount = orderRepository.findByCustomRequestAndUpdate(
                "Ankara", "TR1234", "Türkiye", BigDecimal.valueOf(500), BigDecimal.valueOf(660)
        );
        System.out.println("Updated Rows: " + updatedCount);
    }
}
