package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDto;
import com.nelioalves.cursomc.dto.ClienteNewDto;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repo;
  
  @Autowired
  private EnderecoRepository enderecoRepo;
  
  @Autowired
  private BCryptPasswordEncoder pe;


  public Cliente find(Integer id) {
    Optional<Cliente> obj = repo.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto " + id + " não encontrado"));
  }

  @Transactional
  public ClienteDto insert(ClienteNewDto dto) {

    Cliente obj = fromDto(dto);
    obj.setId(null);
    
    repo.save(obj);
    
    enderecoRepo.saveAll(obj.getEnderecos());
    
    
    return new ClienteDto(obj);
  }

  @Transactional
  public ClienteDto update(Integer id, ClienteDto dto) {

    // Verificacao de seguranca
    Cliente newObj = find(id);

    updateData(newObj, dto);

    repo.save(newObj);
    return new ClienteDto(newObj);
  }

  public void delete(Integer id) {

    try {

      repo.deleteById(id);

    } catch (org.springframework.dao.DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir um cliente com vinculos*", e);
    }
  }

  public List<ClienteDto> findAll() {


    List<Cliente> list = repo.findAll();
    List<ClienteDto> listDto =
        list.stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());

    return listDto;
  }

  public Page<Cliente> findPagged(Integer page, Integer linesPerPage, String orderBy,
      String direction) {

    PageRequest pageRequest =
        PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

    return repo.findAll(pageRequest);
  }
  
  private void updateData(Cliente newObj, ClienteDto dto) {
    newObj.setNome(dto.getNome());
    newObj.setEmail(dto.getEmail());
  }
  
  private Cliente fromDto(ClienteDto dto) {
    return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null); 
  }
  
  
  private Cliente fromDto(ClienteNewDto dto) {
    Cliente cli = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()), pe.encode(dto.getSenha()));
    
    Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(), dto.getBairro(), dto.getCep(), cli, new Cidade(dto.getCidadeId()));
    cli.getEnderecos().add(end);
    
    
    cli.getTelefones().add(dto.getTelefone1());
    if(dto.getTelefone2() != null) {
      cli.getTelefones().add(dto.getTelefone2());
    }
    if(dto.getTelefone3() != null) {
      cli.getTelefones().add(dto.getTelefone3());
    }
    
    return cli;
  }

}
