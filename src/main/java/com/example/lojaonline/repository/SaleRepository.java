package com.example.lojaonline.repository;

import java.util.UUID;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.lojaonline.entity.sale.Sale;

public interface SaleRepository extends JpaRepository<Sale, UUID> {
	List<Sale> findAllBySellerId(UUID sellerID);
}
