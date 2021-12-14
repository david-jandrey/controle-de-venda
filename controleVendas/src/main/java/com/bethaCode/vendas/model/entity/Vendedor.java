package com.bethaCode.vendas.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    @NotEmpty(message = "O campo nome deve ser informado.")
    private String nome;

    @Column
    private Double comissao;

    @Column(length = 1)
    private char sexo;

    @Column(name = "created_at")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate criadoEm;

    @JsonIgnore
    @OneToMany(mappedBy="cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        setCriadoEm(LocalDate.now());
    }
    public Vendedor(){

    }

    public Vendedor(Integer id, String nome, Double comissao, char sexo) {
        this.id = id;
        this.nome = nome;
        this.comissao = comissao;
        this.sexo = sexo;
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
        Vendedor other = (Vendedor) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}