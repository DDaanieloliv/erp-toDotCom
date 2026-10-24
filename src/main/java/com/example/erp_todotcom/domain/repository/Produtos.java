package com.example.erp_todotcom.domain.repository;


import com.example.erp_todotcom.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}

