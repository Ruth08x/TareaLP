package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Alquileres;

@Repository
public interface AlquileresRepository extends JpaRepository<Alquileres, Long>{

}
