package com.example.apelsin.repository;

import com.example.apelsin.entity.Customer;
import com.example.apelsin.entity.Detail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetailRepository extends JpaRepository<Detail, Integer> {
  List<Detail> findAllByOrder_Id(Integer id);
}
