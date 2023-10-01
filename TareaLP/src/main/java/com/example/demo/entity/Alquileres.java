package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
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
@Table(name="TBL_ALQUILERES")
public class Alquileres {
	@Id
	@Column(name = "ID_ALQUILER")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqAlquileres")
    @SequenceGenerator(name = "seqAlquileres", allocationSize = 1, sequenceName = "SEQ_ALQUILERES")
    @Builder.Default
    private Long id=0L;
	
	@Column(name = "FECHA_SALIDA")	
	@Temporal(TemporalType.TIMESTAMP)
    private Date fechasalida;
	
	@Column(name = "FECHA_ENTRADA")	
	@Temporal(TemporalType.TIMESTAMP)
    private Date fechaentrada;

	
	@ManyToOne
    @JoinColumn(name="LIBRO_ID", nullable = false)
    private Libro libro;
	
	@ManyToOne
    @JoinColumn(name="LECTOR_ID", nullable = false)
    private Lector lector;
}
