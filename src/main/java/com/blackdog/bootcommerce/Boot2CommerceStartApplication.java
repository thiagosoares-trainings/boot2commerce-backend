package com.blackdog.bootcommerce;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.yaml.snakeyaml.util.ArrayUtils;

import com.blackdog.bootcommerce.model.Categoria;
import com.blackdog.bootcommerce.model.Produto;
import com.blackdog.bootcommerce.repository.CategoriaRepository;
import com.blackdog.bootcommerce.repository.ProdutoRepository;

@SpringBootApplication
public class Boot2CommerceStartApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Boot2CommerceStartApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Categoria 01", null);
		Categoria c2 = new Categoria(null, "Categoria 02", null);
				
		Produto p1 = new Produto(null, "Produto 01", 1000.00D, null);
		Produto p2 = new Produto(null, "Produto 02", 2000.00D, null);
		Produto p3 = new Produto(null, "Produto 03", 3000.00D, null);
		
		c1.setProdutos(Arrays.asList(p1, p2, p3));
		c2.setProdutos(Arrays.asList(p1, p2));
		
		p1.setCategorias(Arrays.asList(c1,c2));
		p2.setCategorias(Arrays.asList(c1,c2));
		p3.setCategorias(Arrays.asList(c1));
		
		categoriaRepository.saveAll(Arrays.asList(c1,c2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		
		
	}
}
