package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.commons.GlobalConstans;
import com.example.demo.entity.Categoria;
import com.example.demo.serviceImpl.CategoriaServiceImpl;

@RestController
@RequestMapping(GlobalConstans.API_CATEGORIAS)
public class CategoriaController {
	@Autowired
	private CategoriaServiceImpl categoriaServiceImpl;
	@GetMapping
	public ResponseEntity<List<Categoria>> listar() {
		try {
		      List<Categoria> cat = categoriaServiceImpl.readAll();
		      if (cat.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(cat, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping
    public ResponseEntity<Categoria> crear(@Validated @RequestBody Categoria categoria){
        try {
        	Categoria _cat = categoriaServiceImpl.create(categoria);
            return new ResponseEntity<Categoria>(_cat, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getCategoriaById(@PathVariable("id") Long id){
		Optional<Categoria> carData = categoriaServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Categoria>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
