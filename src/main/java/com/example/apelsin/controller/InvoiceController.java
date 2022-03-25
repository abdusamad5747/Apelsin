package com.example.apelsin.controller;

import com.example.apelsin.dto.OverpaidInvoiceDTO;
import com.example.apelsin.entity.Invoice;
import com.example.apelsin.repository.InvoiceRepository;
import com.example.apelsin.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/invoice")
@RequiredArgsConstructor
public class InvoiceController {
    final InvoiceService invoiceService;
    final InvoiceRepository invoiceRepository;

    @GetMapping("/expired_invoices")
    public HttpEntity<?> expired_invoices(){
        List<Invoice> allByDueLessThan = invoiceRepository.findAllByDueLessThanAndActiveTrue(LocalDateTime.now());
        return ResponseEntity.ok().body(allByDueLessThan);
    }
    @GetMapping("/overpaid_invoices")
    public HttpEntity<?> overpaid_invoices(){
        List<OverpaidInvoiceDTO> overpaidInvoices = invoiceService.getOverpaidInvoices();
        return ResponseEntity.ok().body(overpaidInvoices);
    }

}
