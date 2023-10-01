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
import com.example.demo.entity.Editorial;
import com.example.demo.serviceImpl.EditorialServiceImpl;

@RestController
@RequestMapping(GlobalConstans.API_EDITORIALES)
public class EditorialController {
	@Autowired
	private EditorialServiceImpl editorialServiceImpl;
	@GetMapping
	public ResponseEntity<List<Editorial>> listar() {
		try {
		      List<Editorial> edi = editorialServiceImpl.readAll();
		      if (edi.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		      }
		      return new ResponseEntity<>(edi, HttpStatus.OK);
		    } catch (Exception e) {
		      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		    }
	}
	
	@PostMapping
    public ResponseEntity<Editorial> crear(@Validated @RequestBody Editorial editorial){
        try {
        	Editorial _aut = editorialServiceImpl.create(editorial);
            return new ResponseEntity<Editorial>(_aut, HttpStatus.CREATED);
          } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
          }
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Editorial> getEditorialById(@PathVariable("id") Long id){
		Optional<Editorial> carData = editorialServiceImpl.read(id);
	    if (carData.isPresent()) {
	      return new ResponseEntity<Editorial>(carData.get(), HttpStatus.OK);
	    } else {
	      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
