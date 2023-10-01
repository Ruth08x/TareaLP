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
import com.example.demo.entity.Lector;
import com.example.demo.serviceImpl.LectorServiceImpl;

@RestController
@RequestMapping(GlobalConstans.API_LECTORES)
public class LectorController {
	@Autowired
	private LectorServiceImpl lectorServiceImpl;
	@GetMapping
	public ResponseEntity<List<Lector>> listar() {
		try {
		      List<Lector> lec = lectorServiceImpl.readAll();
		      if (lec.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(lec, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping
    public ResponseEntity<Lector> crear(@Validated @RequestBody Lector lector){
        try {
        	Lector _lec = lectorServiceImpl.create(lector);
            return new ResponseEntity<Lector>(_lec, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Lector> getLectorById(@PathVariable("id") Long id){
		Optional<Lector> carData = lectorServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Lector>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
