package com.example.apelsin.repository;

import com.example.apelsin.entity.Customer;
import com.example.apelsin.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
