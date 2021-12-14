package com.bethaCode.vendas.model.repository;

import com.bethaCode.vendas.model.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}