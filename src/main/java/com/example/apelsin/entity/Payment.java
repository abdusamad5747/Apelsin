package com.example.apelsin.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime time;
    private Double amount;
    @ManyToOne
    @JoinColumn(name = "inv_id")
    private Invoice invoice;

}
