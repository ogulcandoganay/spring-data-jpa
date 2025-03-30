package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class QueryMethodTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod(){
        Product product = productRepository.findByName("product 3");
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByIdMethod(){
        Product product = productRepository.findById(6L).get();
        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByImageUrlMethod(){
        // bu da icimden geldi... dikkat, optional, bir product nesnesi degil, sarmalayıcıdır(wrapper) dolayisiyla
        // .get() ile icine gidip, parametre cagirimini oyle yapariz.
        Optional<Product> product = productRepository.findByImageUrl("product1.png");
        System.out.println(product.get().getId());
        System.out.println(product.get().getName());
        System.out.println(product.get().getDescription());

    }

    @Test
    void findByNameOrDescriptionMethod(){
        List<Product> products = productRepository.findByNameOrDescription("product2",
                "updated product 1x desc");

        products.forEach((p) ->{
            System.out.println(p.getId());
            System.out.println(p.getName());
            System.out.println(p.getDescription());
        });
    }

    @Test
    void findByNameAndDescriptionMethod(){
        List<Product> products = productRepository.findByNameAndDescription("product 3",
                "product 3 description");

        products.forEach((product) -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findByPriceGreaterThan(){
        List<Product> products = productRepository.findByPriceGreaterThan(BigDecimal.valueOf(100.00));
        products.forEach((product) -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findByPriceLessThan(){
        List<Product> products = productRepository.findByPriceLessThan(BigDecimal.valueOf(200.00));
        products.forEach((product) -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findByContaining(){
        List<Product> products = productRepository.findByNameContaining("product");
        products.forEach((product) -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findByPriceBetween(){
        List<Product> products = productRepository.findByPriceBetween(BigDecimal.valueOf(000.00),
                BigDecimal.valueOf(300.00));

        products.forEach((product) -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findByDateCreatedBetween(){

        // start date
        LocalDateTime startDate = LocalDateTime.of(2025,03,30, 16,40);

        // end date
        LocalDateTime endDate = LocalDateTime.of(2025,03,30, 17,50);

        List<Product> products = productRepository.findByDateCreatedBetween(startDate, endDate);

        products.forEach((product) -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }

    @Test
    void findByNameIn(){
        List<Product> products = productRepository.findByNameIn(List.of("product 2", "ürün a"));
        products.forEach((product) -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
            System.out.println(product.getDescription());
        });
    }
}
