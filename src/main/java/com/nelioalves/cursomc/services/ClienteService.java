package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.dto.ClienteDto;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repo;

  public Cliente find(Integer id) {
    Optional<Cliente> obj = repo.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto " + id + " não encontrado"));
  }

  public ClienteDto insert(ClienteDto dto) {

    Cliente obj = new Cliente(dto);
    obj.setId(null);
    repo.save(obj);
    return new ClienteDto(obj);
  }

  public ClienteDto update(Integer id, ClienteDto dto) {

    // Verificacao de seguranca
    Cliente newObj = find(id);

    updateData(newObj, dto);

    repo.save(newObj);
    return new ClienteDto(newObj);
  }

  private void updateData(Cliente newObj, ClienteDto dto) {
    
    newObj.setNome(dto.getNome());
    newObj.setEmail(dto.getEmail());

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

}
