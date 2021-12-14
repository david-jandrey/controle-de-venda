package com.bethaCode.vendas.service;

import com.bethaCode.vendas.model.entity.Vendedor;
import com.bethaCode.vendas.model.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository repo;

    public Vendedor find(Integer id) {
        Optional<Vendedor> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "O cliente " + id + " não existe em nossa Aplicação!"));
    }

    @Transactional
    public Vendedor insert(Vendedor obj) {
        obj.setId(null);
        obj = repo.save(obj);
        return obj;
    }

  

   

    

   
}