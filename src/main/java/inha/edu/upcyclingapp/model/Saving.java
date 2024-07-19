package inha.edu.upcyclingapp.model;


import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "savings")
public class Saving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "bank", nullable = false, length = 100)
    private String bank;

    @Column(name = "savings_name", nullable = false, length = 100)
    private String savingsName;

    @Column(name = "interest_rate", nullable = false)
    private BigDecimal interestRate;

    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}