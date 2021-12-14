package com.bethaCode.vendas.model.repository;

import com.bethaCode.vendas.model.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
}