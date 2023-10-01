package com.example.demo.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper=false)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Table(name="TBL_LECTOR")
public class Lector {
	@Id
	@Column(name = "ID_LECTOR")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqLectores")
    @SequenceGenerator(name = "seqLectores", allocationSize = 1, sequenceName = "SEQ_LECTORES")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "NOMBRE")  
    private String nombre;
	
	@Column(name = "TELEFONO")
    private int telefono;
	
	@Column(name = "DIRECCION")  
    private String direccion;
	
	@Column(name = "CODIGO_POSTAL")   
    private String codigopostal;
	
	@Column(name = "OBSERVACIONES")   
    private String observaciones;
	
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "lector")
	@JsonIgnore
	private Set<Alquileres> alquileres;
}
