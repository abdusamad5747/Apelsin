package com.example.apelsin.entity;

import com.example.apelsin.entity.temlate.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product extends AbsNameEntity {
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private String description;
    private Double price;

}
