package com.example.apelsin.controller;

import com.example.apelsin.dto.ApiResponse;
import com.example.apelsin.dto.CustomerDTO;
import com.example.apelsin.entity.Customer;
import com.example.apelsin.repository.CustomerRepository;
import com.example.apelsin.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    final CustomerRepository customerRepository;
    final CustomerService customerService;

    @GetMapping("/customers_without_orders")
    public HttpEntity<?> customers_without_orders(){
        LocalDateTime start = LocalDateTime.of(2019,12,31,24,59);
        Timestamp timestamp = Timestamp.valueOf(start);
        LocalDateTime end = LocalDateTime.of(2020,12,31,24,59);
        Timestamp timestamp1 = Timestamp.valueOf(end);
//        List<Customer> customerByOrder = customerRepository.getCustomerByOrder(timestamp, timestamp1);
//        return ResponseEntity.ok().body(customerByOrder);
        List<Customer> customers = customerService.customers_without_orders();
        return ResponseEntity.ok().body(customers);
    }

//    @GetMapping("/customers_last_orders")
//    public HttpEntity<?> customers_last_orders(){
//        List<CustomerDTO> customerDTOS = customerRepository.getCustomerByO();
//
//    }
    @GetMapping()
    public HttpEntity<?> getAll(){
        List<Customer> all = customerRepository.findAll();
        return ResponseEntity.ok().body(all);
    }
    @PostMapping
    public HttpEntity<?> add(@RequestBody CustomerDTO dto){
        Customer save = customerService.save(dto);
        return ResponseEntity.status(201).body(save);
    }
    @DeleteMapping("{id}")
    public HttpEntity<?> delete(@RequestParam Integer id){
        customerRepository.deleteById(id);
        return ResponseEntity.ok().body("Deleted!!!");
    }
    @PutMapping("{id}")
    public HttpEntity<?> edit(@RequestBody CustomerDTO dto, @PathVariable Integer id){
        ApiResponse response = customerService.edit(dto, id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
    @GetMapping("{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Optional<Customer> byId = customerRepository.findById(id);
        if (byId.isPresent())
            return ResponseEntity.ok().body(byId.get());
        return ResponseEntity.status(404).body("Not Found");
    }

}
