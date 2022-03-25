package com.example.apelsin.repository;

import com.example.apelsin.entity.Category;
import com.example.apelsin.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
