package com.example.apelsin.repository;

import com.example.apelsin.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> findAllByDueLessThanAndActiveTrue(LocalDateTime due);
    @Query(value = "select  i as sump from invoice i join payment p on i.id=p.inv_id \n" +
            "  group by \n" +
            "i.id having sum(p.amount)>i.amount", nativeQuery = true)
    List<Invoice> getOverpaidInvoices();
    Invoice findByOrder_Id(Integer id);
}
