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
import com.example.demo.entity.Autor;
import com.example.demo.serviceImpl.AutorServiceImpl;

@RestController
@RequestMapping(GlobalConstans.API_AUTORES)
public class AutorController {
	@Autowired
	private AutorServiceImpl autorServiceImpl;
	
	
	@GetMapping("/listarautores")
	public ResponseEntity<List<Autor>> listar() {
		try {
		      List<Autor> aut = autorServiceImpl.readAll();
		      if (aut.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(aut, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping("/insertarautores")
    public ResponseEntity<Autor> crear(@Validated @RequestBody Autor autor){
        try {
            Autor _aut = autorServiceImpl.create(autor);
            return new ResponseEntity<Autor>(_aut, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/buscarautores/{id}")
	public ResponseEntity<Autor> getAutorById(@PathVariable("id") Long id){
		Optional<Autor> carData = autorServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Autor>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
