package com.nelioalves.cursomc.services;

import java.awt.image.BufferedImage;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import com.nelioalves.cursomc.domain.Cidade;
import com.nelioalves.cursomc.domain.Cliente;
import com.nelioalves.cursomc.domain.Endereco;
import com.nelioalves.cursomc.domain.enums.Perfil;
import com.nelioalves.cursomc.domain.enums.TipoCliente;
import com.nelioalves.cursomc.dto.ClienteDto;
import com.nelioalves.cursomc.dto.ClienteNewDto;
import com.nelioalves.cursomc.repositories.ClienteRepository;
import com.nelioalves.cursomc.repositories.EnderecoRepository;
import com.nelioalves.cursomc.security.UserSS;
import com.nelioalves.cursomc.services.exceptions.AuthorizationException;
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


  @Autowired
  private S3Service s3Service;

  @Autowired
  private ImageService imageService;

  @Value("${img.prefix.client.profile}")
  private String prefix;

  @Value("${img.profile.size}")
  private Integer size;

  @Value("#{environment.key}")
  private String k;

  public Cliente find(Integer id) {

    UserSS user = UserService.authenticated();
    if (user == null || (!user.hasRole(Perfil.ADMIN) && !id.equals(user.getId()))) {
      throw new AuthorizationException("Acesso Negado");
    }

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

  public Page<Cliente> findPaged(Integer page, Integer linesPerPage, String orderBy,
      String direction) {

    PageRequest pageRequest =
        PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);

    return repo.findAll(pageRequest);
  }

  private void updateData(Cliente newObj, ClienteDto dto) {
    newObj.setNome(dto.getNome());
    newObj.setEmail(dto.getEmail());
  }


  public Cliente findByEmail(String email) {
    UserSS user = UserService.authenticated();
    if (user == null || !user.hasRole(Perfil.ADMIN) && !email.equals(user.getUsername())) {
      throw new AuthorizationException("Acesso negado");
    }

    Cliente obj = repo.findByEmail(email);
    if (obj == null) {
      throw new ObjectNotFoundException(
          "Objeto não encontrado! Id: " + user.getId() + ", Tipo: " + Cliente.class.getName());
    }
    return obj;
  }

  private Cliente fromDto(ClienteDto dto) {
    return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null, null);
  }


  private Cliente fromDto(ClienteNewDto dto) {
    Cliente cli = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(),
        TipoCliente.toEnum(dto.getTipo()), pe.encode(dto.getSenha()));

    Endereco end = new Endereco(null, dto.getLogradouro(), dto.getNumero(), dto.getComplemento(),
        dto.getBairro(), dto.getCep(), cli, new Cidade(dto.getCidadeId()));
    cli.getEnderecos().add(end);


    cli.getTelefones().add(dto.getTelefone1());
    if (dto.getTelefone2() != null) {
      cli.getTelefones().add(dto.getTelefone2());
    }
    if (dto.getTelefone3() != null) {
      cli.getTelefones().add(dto.getTelefone3());
    }

    return cli;
  }

  public URI uploadProfilePicture(MultipartFile multipartFile) {
    UserSS user = UserService.authenticated();
    if (user == null) {
      throw new AuthorizationException("Acesso negado");
    }

    BufferedImage jpgImage = imageService.getJpgImageFromFile(multipartFile);
    jpgImage = imageService.cropSquare(jpgImage);
    jpgImage = imageService.resize(jpgImage, size);

    String fileName = prefix + user.getId() + ".jpg";

    return s3Service.uploadFile(imageService.getInputStream(jpgImage, "jpg"), fileName, "image");

    // return s3Service.uploadFile(multipartFile);
  }

}
