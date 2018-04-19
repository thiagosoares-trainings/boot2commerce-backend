package com.nelioalves.cursomc.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.nelioalves.cursomc.domain.Categoria;
import com.nelioalves.cursomc.repositories.CategoriaRepository;
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

  


}
