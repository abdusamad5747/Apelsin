package com.example.apelsin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DetailDTO {
    private Integer product_id;
    private Integer quantity;

}
