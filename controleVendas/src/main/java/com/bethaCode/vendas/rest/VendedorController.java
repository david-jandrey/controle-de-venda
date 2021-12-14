package com.bethaCode.vendas.rest;

import com.bethaCode.vendas.model.entity.Vendedor;
import com.bethaCode.vendas.model.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/vendedor")
public class VendedorController {

    private final VendedorRepository repository;

    @Autowired
    public VendedorController(VendedorRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vendedor salvar(@Valid @RequestBody Vendedor vendedor) {
        return repository.save(vendedor);
    }

    @GetMapping
    public List<Vendedor> listar() {
        return repository.findAll();
    }

    @GetMapping("{id}")
    public Vendedor acharPorId(@PathVariable Integer id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O vendedor não foi localizado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        Optional<Vendedor> vendedor = repository.findById(id);

        if (!vendedor.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendedor não localizado.");
        }

        repository.delete(vendedor.get());
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @Valid @RequestBody Vendedor dados) {
        Vendedor vendedor = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Vendedor não localizado."));

        vendedor.setComissao(dados.getComissao());
        vendedor.setCriadoEm(dados.getCriadoEm());
        vendedor.setSexo(dados.getSexo());
        vendedor.setNome(dados.getNome());

        repository.save(vendedor);
    }
}