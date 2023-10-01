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
import com.example.demo.entity.Libro;
import com.example.demo.serviceImpl.LibroServiceImpl;

@RestController
@RequestMapping(GlobalConstans.API_LIBROS)
public class LibroController{
	
	@Autowired
	private LibroServiceImpl libroServiceImpl;
	@GetMapping
	public ResponseEntity<List<Libro>> listar() {
		try {
		      List<Libro> lib = libroServiceImpl.readAll();
		      if (lib.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(lib, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping
    public ResponseEntity<Libro> crear(@Validated @RequestBody Libro libro){
        try {
        	Libro _alq = libroServiceImpl.create(libro);
            return new ResponseEntity<Libro>(_alq, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Libro> getLibroById(@PathVariable("id") Long id){
		Optional<Libro> carData = libroServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Libro>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
