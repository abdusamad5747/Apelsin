package com.example.apelsin.repository;

import com.example.apelsin.dto.ProductDTO;
import com.example.apelsin.entity.Customer;
import com.example.apelsin.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "select d.product_id, count(*),sum(d.quantity)  from product p join detail d on p.id = d.product_id group by d.product_id having count(*)>=10 ",nativeQuery = true)
    List<ProductDTO> getProductByCount();
}
