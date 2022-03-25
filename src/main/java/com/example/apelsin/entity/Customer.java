package com.example.apelsin.entity;

import com.example.apelsin.entity.temlate.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedEntityGraph;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Customer extends AbsNameEntity {
    private String country;
    private String address;
    private String phone;
}
