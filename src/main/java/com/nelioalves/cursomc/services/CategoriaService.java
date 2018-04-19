package com.nelioalves.cursomc.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.dto.CategoriaDto;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
import com.nelioalves.cursomc.services.exceptions.DataIntegrityException;
import com.nelioalves.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repo;

  public Categoria find(Integer id) {
    Optional<Categoria> obj = repo.findById(id);
    return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto " + id + " não encontrado"));
  }

  public Categoria insert(Categoria obj) {
    obj.setId(null);
    return repo.save(obj);
  }

  public Categoria update(Integer id, Categoria obj) {
    
    //Verificacao de seguranca
    find(id);
    
    return repo.save(obj);
  }

  public void delete(Integer id) {
    
    try {
      
      repo.deleteById(id);
      
    } catch (org.springframework.dao.DataIntegrityViolationException e) {
      throw new DataIntegrityException("Não é possível excluir uma categoria com produtos", e);
    }
  }

  public List<CategoriaDto> findAll() {
    
    
    List<Categoria> list = repo.findAll();
    List<CategoriaDto> listDto = list.stream().map(obj -> new CategoriaDto(obj)).collect(Collectors.toList());
    
    return listDto;
  }

  public Page<Categoria> findPagged(Integer page, Integer linesPerPage, String orderBy, String direction) {
    
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    
    return repo.findAll(pageRequest);
  }
   
  


}
