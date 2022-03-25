package com.example.apelsin.service;

import com.example.apelsin.dto.ApiResponse;
import com.example.apelsin.dto.CustomerDTO;
import com.example.apelsin.entity.Customer;
import com.example.apelsin.entity.Order;
import com.example.apelsin.repository.CustomerRepository;
import com.example.apelsin.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final OrderRepository orderRepository;
    final CustomerRepository customerRepository;

    public List<Customer> customers_without_orders(){
        LocalDateTime start = LocalDateTime.of(2019, 12, 31, 24,59);
        LocalDateTime end = LocalDateTime.of(2020, 12, 31, 24,59);
        List<Order> orders = orderRepository.findAllByActiveTrueAndCreatedAtBetween(start, end);
        List<Customer> customersOrd = new ArrayList<>();
        for (Order order : orders) {
            customersOrd.add(order.getCustomer());
        }
        List<Customer> customerList = customerRepository.findAll();
        customerList.removeAll(customersOrd);
        return customerList;
    }

    public Customer save(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setAddress(dto.getAddress());
        customer.setCountry(dto.getCountry());
        customer.setPhone(dto.getPhone());
        customer.setName(dto.getName());
        Customer save = customerRepository.save(customer);
        return save;
    }

    public ApiResponse edit(CustomerDTO dto, Integer id) {
        Optional<Customer> byId = customerRepository.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("EROR!!!", false, 400);
        }else {
            Customer customer = byId.get();
            customer.setName(dto.getName());
            customer.setCountry(dto.getCountry());
            customer.setAddress(dto.getAddress());
            customer.setPhone(dto.getPhone());
            customerRepository.save(customer);
            return new ApiResponse("Edited!!!", true, 200);
        }
    }
}
