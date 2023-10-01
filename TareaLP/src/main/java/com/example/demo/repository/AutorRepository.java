package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

}
