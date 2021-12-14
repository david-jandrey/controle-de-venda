package com.bethaCode.vendas.rest;

import com.bethaCode.vendas.model.entity.Cliente;
import com.bethaCode.vendas.model.entity.Vendedor;
import com.bethaCode.vendas.model.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {this.repository = repository;}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)

    public Cliente salvar(@Valid @RequestBody Cliente pessoas) {return repository.save(pessoas);}

    @GetMapping("{id}")
    public Cliente acharPorId(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O Cliente " + id + " não existe em nossa Aplicação!"));
    }

    @GetMapping
    public List<Cliente> listar() {
        return repository.findAll();
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id){
        repository
                .findById(id)
                .map(pessoas ->{
                    repository.delete(pessoas);
                    return Void.TYPE;
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O Cliente " + id + " não existe em nossa aplicação!"));
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(@PathVariable Integer id, @Valid @RequestBody Cliente dadoAtualizado){
        repository
                .findById(id)
                .map(pessoas -> {
                    pessoas.setNome(dadoAtualizado.getNome());
                    pessoas.setIdade(dadoAtualizado.getIdade());
                    pessoas.setCpf(dadoAtualizado.getCpf());
                    pessoas.setValorCredito(dadoAtualizado.getValorCredito());
                    pessoas.setSexo(dadoAtualizado.getSexo());
                    return repository.save(pessoas);
                })
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "O Cliente " + id + " não existe em nossa aplicação!"));
    }
}