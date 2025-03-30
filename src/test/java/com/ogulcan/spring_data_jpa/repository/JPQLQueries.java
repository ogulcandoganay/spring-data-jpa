package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
public class JPQLQueries {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findByNameOrDescriptionJPQLIndexParam(){
        Product product = productRepository.findByNameOrDescriptionJPQLIndexParam("description 3",
                "product 3 description");
        System.out.println(product.getId());
        System.out.println(product.getName());
    }

    // belli bir price altındaki sorguları silen custom jpql query'si
    @Test
    void deleteByPriceLessThan(){
        productRepository.deleteByPriceLessThan(BigDecimal.valueOf(30));
    }

    @Test
    void findCustomProducts(){
        List<Product> products = productRepository.findCustomProducts("ürün",  BigDecimal.valueOf(70.00));
        products.forEach((product) -> {
            System.out.println(product.getId());
            System.out.println(product.getName());
        });
    }


}
