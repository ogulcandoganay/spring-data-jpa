package com.ogulcan.spring_data_jpa.repository;

import com.ogulcan.spring_data_jpa.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByName(String name);

    Optional<Product> findById(Long id);

    Optional<Product> findByImageUrl(String imageUrl);

    List<Product> findByNameOrDescription(String name, String description);

    List<Product> findByNameAndDescription(String name, String description);

    List<Product> findByPriceGreaterThan(BigDecimal price);

    List<Product> findByPriceLessThan(BigDecimal price);

    // kolon adi + containing parametresi o kelimenin gectigi sorguları getirir.
    List<Product> findByNameContaining(String name);

    List<Product> findByPriceBetween(BigDecimal min, BigDecimal max);

    List<Product> findByDateCreatedBetween(LocalDateTime min, LocalDateTime max);

    // containing parametresinin icerisine aranacak listeyi yolluyoruz, 1'den fazla yani.
    List<Product> findByNameIn(List<String> names);

    // define JPQL query using @Query annotation with index or position parameters
    // index parameter kullandık, daha karmasık ve az kullanılan bir yontemdir.
    @Query("SELECT p from Product p where p.name = ?1 or p.description =?2")
    Product findByNameOrDescriptionJPQLIndexParam(String name, String description);

    // yukarıdakinin aynısının burda named parameter'ini kullandık. daha okunaklı ve anlasilir.
    @Query("SELECT p from Product p where p.name = :name OR p.description =:desc")
    Product findByNameOrDescriptionJPQLNamedParam(@Param("name") String name,
                                                  @Param("desc") String description);

    // belli bir price altındaki sorguları silen custom jpql query'si
    @Modifying
    @Transactional
    @Query("DELETE FROM Product p where p.price < :price")
    void deleteByPriceLessThan(@Param("price") BigDecimal price);

    // burda icerisinde ürün adını verdiğimiz ve fiyati belli bir miktardan kücük olan ürünler icin query yazdım
    @Query("SELECT p FROM Product p WHERE p.name LIKE %:keyword% AND p.price < :price")
    List<Product> findCustomProducts(@Param("keyword") String keyword,
                                     @Param("price") BigDecimal price);

}
