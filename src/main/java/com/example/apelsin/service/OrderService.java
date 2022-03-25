package com.example.apelsin.service;

import com.example.apelsin.dto.ApiResponse;
import com.example.apelsin.dto.DetailDTO;
import com.example.apelsin.dto.OrderDTO;
import com.example.apelsin.entity.*;
import com.example.apelsin.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    final CustomerRepository customerRepository;
    final OrderRepository orderRepository;
    final OrderService orderService;
    final ProductRepository productRepository;
    final DetailRepository detailRepository;
    final InvoiceRepository invoiceRepository;

    public ApiResponse save(OrderDTO dto) {
        Order order = new Order();
        Optional<Customer> byId = customerRepository.findById(dto.getCustomer_id());
        if (!byId.isPresent()) {
            return new ApiResponse("Customer not found!", false, 404);
        }
        order.setCustomer(byId.get());
        ApiResponse response = detailSave(order, dto);
        return response;
    }
    public ApiResponse detailSave(Order order, OrderDTO dto){
        List<DetailDTO> detailDTOS = dto.getDetailDTOS();
        Double summa=0d;
        for (DetailDTO detail : detailDTOS) {
            Detail detail1 = new Detail();
            Optional<Product> byId = productRepository.findById(detail.getProduct_id());
            if (!byId.isPresent())
                return new ApiResponse("Product not found!!!", false, 404);
            Product product = byId.get();
            detail1.setOrder(order);
            summa += product.getPrice()*detail.getQuantity();
            detail1.setProduct(product);
            detail1.setQuantity(detail.getQuantity());
            detailRepository.save(detail1);
        }
        Invoice invoice = new Invoice();
        invoice.setOrder(order);
        invoice.setAmount(summa);
        invoiceRepository.save(invoice);
        return new ApiResponse("Your order has been accepted", true, 200);
    }

    public ApiResponse editSave(OrderDTO dto, Integer id) {
        List<DetailDTO> detailDTOS = dto.getDetailDTOS();
        Integer customer_id = dto.getCustomer_id();
        Optional<Order> byId = orderRepository.findById(id);
        if (byId.isEmpty())
            return new ApiResponse("Order not found!!!", false,404);
        Order order = byId.get();
        List<Detail> details = detailRepository.findAllByOrder_Id(id);
        List<Detail> detailList = new ArrayList<>();
        Double summa=0d;
        for (DetailDTO detailDTO : detailDTOS) {
            Detail detail = new Detail();
            Integer product_id = detailDTO.getProduct_id();
            Integer quantity = detailDTO.getQuantity();
            detail.setOrder(order);
            detail.setQuantity(quantity);
            Optional<Product> byId1 = productRepository.findById(product_id);
            if (byId1.isEmpty()) {
                return new ApiResponse("Product not found!!!", false, 404);
            }
            Product product = byId1.get();
            detail.setProduct(product);
            summa += product.getPrice()*quantity;
            detailList.add(detail);
        }
        details = detailList;
        Invoice invoice = invoiceRepository.findByOrder_Id(id);
        invoice.setOrder(order);
        invoice.setAmount(summa);
        invoice.setDue(dto.getDue());
        invoiceRepository.save(invoice);
        detailRepository.saveAll(details);
        return new ApiResponse("Your order has been edited", true, 200);
    }
}
