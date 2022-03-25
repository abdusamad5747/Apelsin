package com.example.apelsin.service;

import com.example.apelsin.dto.OverpaidInvoiceDTO;
import com.example.apelsin.entity.Invoice;
import com.example.apelsin.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceService {
    final InvoiceRepository invoiceRepository;

    public List<OverpaidInvoiceDTO> getOverpaidInvoices() {
        List<Invoice> overpaidInvoices = invoiceRepository.getOverpaidInvoices();
        List<OverpaidInvoiceDTO> overpaidInvoiceDTOS = new ArrayList<>();
        for (Invoice overpaidInvoice : overpaidInvoices) {
            OverpaidInvoiceDTO dto = new OverpaidInvoiceDTO();
            dto.setAmount(overpaidInvoice.getAmount());
            dto.setId(overpaidInvoice.getId());
            overpaidInvoiceDTOS.add(dto);
        }
        return overpaidInvoiceDTOS;
    }
}
