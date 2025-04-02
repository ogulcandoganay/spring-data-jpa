package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void saveMethod(){
        // create product
        Product product = new Product();
        product.setName("product 2");
        product.setDescription("product 2 description");
        product.setSku("102ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product2.png");

        // save product
        Product savedObject = productRepository.save(product);

        // display product info
        System.out.println(savedObject.getId());
        System.out.println(savedObject.toString());
    }

    @Test
    void updateUsingSaveMethod(){

        // find or retrieve an entity by id
        Long id = 6L;
        Product product = productRepository.findById(id).get();

        // update entity information
        product.setName("updated product 1");
        product.setDescription("updated product 1 desc");

        // save updated entity
        productRepository.save(product);
    }

    @Test
    void findByIdMethod(){
        Long id = 8L;

        Product product = productRepository.findById(id).get();

        System.out.println(product.toString());
    }

    @Test
    void saveAllMethod(){

        // create product
        Product product = new Product();
        product.setName("ürün a");
        product.setDescription("ürün a description");
        product.setSku("107ABC");
        product.setPrice(new BigDecimal(60));
        product.setActive(true);
        product.setImageUrl("uruna.png");

        // create product
        Product product3 = new Product();
        product3.setName("ürün b");
        product3.setDescription("ürün b description");
        product3.setSku("108ABC");
        product3.setPrice(new BigDecimal(70));
        product3.setActive(true);
        product3.setImageUrl("urunb.png");

        productRepository.saveAll(List.of(product, product3));
    }

    @Test
    void findAllMethod(){
        List<Product> products = productRepository.findAll();
        System.out.println(products.toString());
    }

    @Test
    void deleteByIdMethod(){
        Long id = 10L;
        productRepository.deleteById(id);
    }

    @Test
    void deleteAllMethod(){
        productRepository.deleteAll();
    }

    @Test
    void countMethod(){
        Long count = productRepository.count();
        System.out.println(count);
    }

    @Test
    void existsByIdMethod(){
        Long id = 6L;
        Long id2 = 10L;
        // burda db'de olan bir id'yi sorguluyoruz.
        boolean exist = productRepository.existsById(id);
        // burda da db'de olmayanı sorguluyoruz ki false versin
        boolean exist2 = productRepository.existsById(id2);
        System.out.println(exist);
        System.out.println(exist2);
    }
}