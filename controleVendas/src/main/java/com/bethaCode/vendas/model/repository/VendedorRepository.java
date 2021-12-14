package com.bethaCode.vendas.model.repository;

import com.bethaCode.vendas.model.entity.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
}