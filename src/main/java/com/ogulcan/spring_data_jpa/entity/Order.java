package com.ogulcan.spring_data_jpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(
        name = "orders",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "orderTrackingNumber_unique",
                        columnNames = "orderTrackingNumber"
                )
        }
)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Adress billingAdress;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Set<OrderItem> orderItems = new HashSet<>();

    public BigDecimal getTotalPrice() {
        BigDecimal amount = new BigDecimal(0);
        for (OrderItem item : this.orderItems) {
            amount = amount.add(item.getPrice());
        }
        return amount;
    }
    // Macbook'taki Lombok hatası sebebiyle Getter Setter da oluşturuyorum

}
