package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JPQLQueries {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void findByNameOrDescriptionJPQLIndexParam(){
        Product product = productRepository.findByNameOrDescriptionJPQLIndexParam("description 2",
                "product 2 description");
        System.out.println(product.getId());
        System.out.println(product.getName());
    }
}
