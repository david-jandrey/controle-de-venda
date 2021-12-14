package com.bethaCode.vendas.service;

import com.bethaCode.vendas.model.entity.ItemPedido;
import com.bethaCode.vendas.model.entity.Pedido;
import com.bethaCode.vendas.model.repository.ItemPedidoRepository;
import com.bethaCode.vendas.model.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository repo;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VendedorService vendedorService;


    public Pedido find(Integer id) {
        Optional<Pedido> obj = repo.findById(id);
        return obj.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "O pedido " + id + " não existe em nossa Aplicação!"));
    }
    @Transactional
    public Pedido insert(Pedido obj) {
        obj.setId(null);
        obj.setInstante(new Date());
        obj.setCliente(clienteService.find(obj.getCliente().getId()));
        obj.setVendedor(vendedorService.find(obj.getVendedor().getId()));

        obj = repo.save(obj);
        for (ItemPedido ip : obj.getItens()) {
            ip.setDesconto(0.0);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(obj);
        }
        itemPedidoRepository.saveAll(obj.getItens());
        return obj;
    }


}
