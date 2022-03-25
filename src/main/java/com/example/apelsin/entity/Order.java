package com.example.apelsin.entity;

import com.example.apelsin.entity.temlate.AbsEntity;
import jdk.jfr.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order extends AbsEntity {
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
