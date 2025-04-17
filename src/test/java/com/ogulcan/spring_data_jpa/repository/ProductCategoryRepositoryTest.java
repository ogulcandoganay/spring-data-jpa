//package com.ogulcan.spring_data_jpa.repository;
//
//import com.ogulcan.spring_data_jpa.entity.Product;
//import com.ogulcan.spring_data_jpa.entity.ProductCategory;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//import java.util.List;
//
//@SpringBootTest
//public class ProductCategoryRepositoryTest {
//
//    @Autowired
//    private ProductCategoryRepository productCategoryRepository;
//    @Autowired
//    private ProductRepository productRepository;
//
//    @Test
//    public void saveProductCategory() {
//        ProductCategory productCategory = new ProductCategory();
//
//        Product product = new Product();
//        product.setName("Monster Notebook");
//        product.setDescription("Abra A5 V15.5");
//        product.setSku("101ABC");
//        product.setPrice(new BigDecimal(15660));
//        product.setActive(true);
//        product.setImageUrl("abraa5.png");
//
//        productCategory.setCategoryName("Notebook");
//        productCategory.setCategoryDescription("Aradığınız notebook modelleri");
//
//        // Product nesnesinin category alanına productCategory'i atayın
//        product.setProductCategory(productCategory);
//
//        // ProductCategory'ye product nesnesini ekleyin
//        productCategory.setProduct(List.of(product));
//
//        productCategoryRepository.save(productCategory);
//
//    }
//
//    @Test
//    public void deleteCategory() {
//        productCategoryRepository.deleteById(1L);
//    }
//}
