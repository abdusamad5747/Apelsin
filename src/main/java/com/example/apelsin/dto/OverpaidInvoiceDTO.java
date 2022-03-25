package com.example.apelsin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OverpaidInvoiceDTO {
    private Integer id;
    private Double amount;
}
