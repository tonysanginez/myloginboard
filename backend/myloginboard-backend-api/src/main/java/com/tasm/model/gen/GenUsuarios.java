package com.tasm.model.gen;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tasm.util.ActivoConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "gen_usuarios")
public class GenUsuarios implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @NotNull
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "secuencia_usuario")
	private Long secuenciaUsuario;
	
	@Column(name = "codigo_usuario")
	private String codigoUsuario;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "contrasenia")
	private String contrasenia;
	
	@Column(name = "primer_nombre")
	private String primerNombre;
	
	@Column(name = "segundo_nombre")
	private String segundoNombre;
	
	@Column(name = "primer_apellido")
	private String primerApellido;
	
	@Column(name = "segundo_apellido")
	private String segundoApellido;
	
	@Column(name = "nombre_completo")
	private String nombreCompleto;
	
	@Column(name = "directorio_foto")
	private String directorioFoto;
	
	@Column(name = "nombre_foto")
	private String nombreFoto;
	
	@Convert(converter = ActivoConverter.class)
	@Column(name = "estado")
	private boolean estado;
	
	@Column(name = "fecha_ingreso")
	private LocalDateTime fechaIngreso;
	
	@Column(name = "usuario_ingreso")
	private String usuarioIngreso;
	
	@Column(name = "fecha_modificacion")
	private LocalDateTime fechaModificacion;
	
	@Column(name = "usuario_modificacion")
	private String usuarioModificacion;
	
}