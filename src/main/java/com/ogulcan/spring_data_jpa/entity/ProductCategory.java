package com.ogulcan.spring_data_jpa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;
    private String categoryDescription;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    },
            fetch = FetchType.LAZY, mappedBy = "productCategory")
    private List<Product> product = new ArrayList<>();
}
