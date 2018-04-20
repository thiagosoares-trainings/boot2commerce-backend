package com.nelioalves.cursomc.services;

import java.net.URI;
import java.util.Date;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.domain.ItemPedido;
import com.nelioalves.cursomc.domain.PagamentoComBoleto;
import com.nelioalves.cursomc.domain.Pedido;
import com.nelioalves.cursomc.domain.enums.EstadoPagamento;
import com.nelioalves.cursomc.dto.ClienteDto;
import com.nelioalves.cursomc.dto.ClienteNewDto;
import com.nelioalves.cursomc.repositories.ItemPedidoRepository;
import com.nelioalves.cursomc.repositories.PagamentoRepository;
import com.nelioalves.cursomc.repositories.PedidoRepository;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository repo;
  
  @Autowired
  private ItemPedidoRepository itemPedidoRepository;

  @Autowired
  private PagamentoRepository pagamentoRepo;

  
  
  @Autowired
  private ProdutoService produtoService;
  
  @Autowired
  private BoletoService boletoService; 

  public Pedido find(Integer id) {
    Optional<Pedido> obj = repo.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto " + id + " não encontrado"));
  }

  public Pedido insert(@Valid Pedido obj) {

    obj.setId(null);
    obj.setInstante(new Date());
    
    obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
    obj.getPagamento().setPedido(obj); 
    
    if(obj.getPagamento() instanceof PagamentoComBoleto) {
      
      PagamentoComBoleto pgBoleto = (PagamentoComBoleto) obj.getPagamento();
      
      boletoService.buildPagamentoBoleto(pgBoleto, obj.getInstante());
      
    } else {
     
      // TODO
    }
    
    repo.save(obj);
    pagamentoRepo.save(obj.getPagamento());
    
    for(ItemPedido item: obj.getItens()) {
      item.setDesconto(0.0);
      item.setPreco(produtoService.find(item.getProduto().getId()).getPreco());
      item.setPedido(obj);
    }
    
    itemPedidoRepository.saveAll(obj.getItens());
    
    return obj;
  }


}
