package com.example.erp_todotcom.domain.repository;


import com.example.erp_todotcom.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsPedidos extends JpaRepository<ItemPedido, Integer> {
}