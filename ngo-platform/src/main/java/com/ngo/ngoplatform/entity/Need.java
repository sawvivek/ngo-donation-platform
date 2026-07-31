package com.ngo.ngoplatform.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "needs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Need {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private String type;      // MONEY or ITEM

    private Double targetAmount;

    private Double currentAmount = 0.0;

    private String itemRequired;

    private Integer requiredQuantity;

    private Integer receivedQuantity = 0;

    private String status = "PENDING";

    @ManyToOne
    @JoinColumn(name = "ngo_id")
    private Ngo ngo;
}