package com.example.apelsin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResCustomerDTO {
    private Integer id;
    private String name;
    private LocalDateTime date;
}
