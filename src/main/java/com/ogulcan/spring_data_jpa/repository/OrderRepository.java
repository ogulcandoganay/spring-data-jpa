package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    // ordertracking number'a gore order bulmak icin query method yazıyoruz
    Order findByOrderTrackingNumber(String orderTrackingNumber);

    // Sipariş durumuna göre siparişleri getir
    List<Order> findByStatus(String status);

    // 1t1 undirectional belli bir fiyattan yuksek ve belirli bir ülkeye ait olan sorguları getiren custom query
    @Query("SELECT p FROM Order p where p.totalPrice > :price AND p.billingAdress.country = :country")
    List<Order> findByCustomQueryByPriceAndCountry(@Param("price") BigDecimal totalPrice,
                                                   @Param("country") String country);

    // 1t1 undirectionalfiyatti belli bir fiyat üzerinde olan ve durumu "delivered" olan kayıtları getiren custom query
    @Query("SELECT p FROM Order p where p.totalPrice > :price AND p.status = :status")
    List<Order> findByPriceAndStatus(@Param("price") BigDecimal price,
                                     @Param("status") String status);

//     Belirli bir ülke (country) ve belirli bir fiyat aralığı (totalPrice) için siparişleri (orders) bul.
//     Bu siparişlerin adreslerini (adress) güncelle. Yeni şehir (city) ve posta kodu (zipCode) değerlerini
//     veritabanına yansıtan bir custom query yaz
    @Modifying
    @Transactional
    @Query("UPDATE Adress a SET a.city = :newCity, a.zipCode = :newZipCode " +
            "WHERE a.country = :country AND a.order.totalPrice BETWEEN :minPrice AND :maxPrice")
    int findByCustomRequestAndUpdate(@Param("newCity") String city,
                                     @Param("newZipCode") String newZipCode,
                                     @Param("country") String country,
                                     @Param("minPrice") BigDecimal minPrice,
                                     @Param("maxPrice") BigDecimal maxPrice);

}