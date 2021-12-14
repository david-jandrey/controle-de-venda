package com.bethaCode.vendas.service;

import com.bethaCode.vendas.model.entity.Produto;
import com.bethaCode.vendas.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repo;


    public Produto find(Integer id) {
        Optional<Produto> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "O produto " + id + " não existe em nossa Aplicação!"));
    }
}
