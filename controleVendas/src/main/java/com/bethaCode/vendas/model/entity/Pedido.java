package com.bethaCode.vendas.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter@Getter
public class Pedido implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern="dd/MM/yyyy HH:mm")
    private Date instante;

    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;


    @ManyToOne
    @JoinColumn(name = "vendedor_id")
    private Vendedor vendedor;


    @OneToMany(mappedBy="id.pedido")
    private Set<ItemPedido> itens = new HashSet<>();

    public Vendedor getVendedor() {
        return vendedor;
    }

    public Pedido() {
    }

    public Pedido(Integer id, Date instante, Cliente cliente, Vendedor vendedor) {
        super();
        this.id = id;
        this.instante = instante;
        this.cliente = cliente;
        this.vendedor = vendedor;
    }

    public double getValorTotal() {
        double soma = 0.0;
        for (ItemPedido ip : itens) {
            soma = soma + ip.getSubTotal();
        }
        return soma;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getInstante() {
        return instante;
    }

    public void setInstante(Date instante) {
        this.instante = instante;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }


    public Set<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(Set<ItemPedido> itens) {
        this.itens = itens;
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
        Pedido other = (Pedido) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }


}