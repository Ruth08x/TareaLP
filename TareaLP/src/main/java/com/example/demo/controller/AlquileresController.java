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
import com.example.demo.entity.Alquileres;
import com.example.demo.serviceImpl.AlquileresServiceImpl;

@RestController
@RequestMapping(GlobalConstans.API_ALQUILERES)
public class AlquileresController {
	@Autowired
	private AlquileresServiceImpl alquilerServiceImpl;
	@GetMapping
	public ResponseEntity<List<Alquileres>> listar() {
		try {
		      List<Alquileres> alq = alquilerServiceImpl.readAll();
		      if (alq.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(alq, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping
    public ResponseEntity<Alquileres> crear(@Validated @RequestBody Alquileres alquiler){
        try {
        	Alquileres _alq = alquilerServiceImpl.create(alquiler);
            return new ResponseEntity<Alquileres>(_alq, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Alquileres> getAlquilerById(@PathVariable("id") Long id){
		Optional<Alquileres> carData = alquilerServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Alquileres>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
