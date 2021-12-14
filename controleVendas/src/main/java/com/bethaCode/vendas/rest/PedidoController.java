package com.bethaCode.vendas.rest;

import com.bethaCode.vendas.model.entity.Pedido;
import com.bethaCode.vendas.model.entity.Produto;
import com.bethaCode.vendas.model.repository.PedidoRepository;
import com.bethaCode.vendas.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.bethaCode.vendas.service.PedidoService;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    @Autowired
    private PedidoRepository repository;

    @Autowired
    public PedidoController(PedidoRepository repository){
        this.repository = repository;
    }

    @Autowired
    private PedidoService service;

    @GetMapping("{id}")
    public Pedido acharPorId(@PathVariable Integer id){
        Pedido obj = service.find(id);
        return obj;
    }

    @GetMapping
    public List<Pedido> listar() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pedido salvar(@Valid @RequestBody Pedido obj){
        System.out.println(obj);
        obj = service.insert(obj);
        return obj;
    }

}