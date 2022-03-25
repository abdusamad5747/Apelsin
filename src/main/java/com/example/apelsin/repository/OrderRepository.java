package com.example.apelsin.repository;

import com.example.apelsin.entity.Customer;
import com.example.apelsin.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByActiveTrueAndCreatedAtLessThan(LocalDateTime createdAt);
    List<Order> findAllByActiveTrueAndCreatedAtBetween(LocalDateTime createdAt, LocalDateTime createdAt2);
    List<Order> findAllByActiveTrue();
    Order findByIdAndActiveTrue(Integer id);
}
