package com.example.demo.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Alquileres;
import com.example.demo.repository.AlquileresRepository;
import com.example.demo.service.Operaciones;

@Service
public class AlquileresServiceImpl implements Operaciones<Alquileres>{
	
	@Autowired
	private AlquileresRepository alquileresRepository;

	@Override
	public Alquileres create(Alquileres t) {
		// TODO Auto-generated method stub
		return alquileresRepository.save(t);
	}

	@Override
	public Optional<Alquileres> read(Long id) {
		// TODO Auto-generated method stub
		return alquileresRepository.findById(id);
	}

	@Override
	public List<Alquileres> readAll() {
		// TODO Auto-generated method stub
		return alquileresRepository.findAll();
	}

}