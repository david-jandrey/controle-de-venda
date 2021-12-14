package com.bethaCode.vendas.model.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter@Getter
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Min(value = 18, message = "O Cliente deve ter no mínimo  18 anos!!")
    private Integer idade;

    @NotEmpty(message = "O campo Nome deve ser informado!!")
    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 11)
    private String cpf;

    @Column(length = 1)
    private char sexo;

    @Column(length = 10)
    private Double valorCredito;

    @JsonIgnore
    @OneToMany(mappedBy="cliente")
    private List<Pedido> pedidos = new ArrayList<>();


    public Cliente() {
    }

    public Cliente(Integer id, Integer idade, String nome, String cpf, char sexo, Double valorCredito) {
        this.id = id;
        this.idade = idade;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.valorCredito = valorCredito;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Cliente other = (Cliente) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}