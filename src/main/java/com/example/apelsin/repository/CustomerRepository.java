package com.example.apelsin.repository;

import com.example.apelsin.dto.CustomerDTO;
import com.example.apelsin.dto.ResCustomerDTO;
import com.example.apelsin.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

//    @Query("select c.id, c.active, c.name, c.address, c.country, c.phone from customer c join orders o on c.id = o.customer_id where o.created_at " +
//            "between ?1  and ?2" )
//    List<Customer> getCustomerByOrder(Timestamp time1, Timestamp time2);
    @Query(value = "select distinct c.id, c.name, o.createdAt from orders o join customer c on o.customer=c order by c.id, o.createdAt desc ", nativeQuery = true)
    List<ResCustomerDTO> getCustomerByO();
}
