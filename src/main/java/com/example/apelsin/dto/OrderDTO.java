package com.example.apelsin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.id.IntegralDataTypeHolder;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDTO {
    private Integer customer_id;
    private List<DetailDTO> detailDTOS;
    private LocalDateTime due;
}
