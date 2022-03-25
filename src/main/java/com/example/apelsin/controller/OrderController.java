package com.example.apelsin.controller;

import com.example.apelsin.dto.ApiResponse;
import com.example.apelsin.dto.OrderDTO;
import com.example.apelsin.entity.Order;
import com.example.apelsin.repository.OrderRepository;
import com.example.apelsin.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {
    final OrderRepository orderRepository;
    final OrderService orderService;

    @GetMapping("/orders_without_details")
    public HttpEntity<?> orders_without_details(){
        LocalDateTime dateTime = LocalDateTime.of(2021, 9, 6, 12, 12 );
        List<Order> orderList = orderRepository.findAllByActiveTrueAndCreatedAtLessThan(dateTime);
        return ResponseEntity.ok().body(orderList);
    }
    @GetMapping()
    public HttpEntity<?> getAll(){
        return ResponseEntity.ok().body(orderRepository.findAll());
    }

    @GetMapping("{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id){
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok().body(byId.get());
        }
        return ResponseEntity.status(404).body("Not found");
    }
    @PostMapping
    private HttpEntity<?> add(@RequestBody OrderDTO dto){
        ApiResponse save = orderService.save(dto);
        return ResponseEntity.status(save.getStatus()).body(save);
    }
    @PutMapping("{id}")
    public HttpEntity<?> edit(@RequestBody OrderDTO dto, @PathVariable Integer id){
        ApiResponse response = orderService.editSave(dto,id);
        return ResponseEntity.status(response.getStatus()).body(response);
    }
}
