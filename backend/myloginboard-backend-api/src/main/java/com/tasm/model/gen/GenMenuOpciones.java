package com.tasm.model.gen;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
@Table(name = "gen_menu_opciones")
public class GenMenuOpciones implements Serializable {
	
	private static final long serialVersionUID = 1L; 

	@Id
    @NotNull
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO_MENU")
	private Integer codigoMenu; 

	@Size(max = 80)
	@Column(name = "NOMBRE")
	private String nombre; 

	@Size(max = 20)
	@Column(name = "NOMBRE_CORTO")
	private String nombreCorto;

	@Size(max = 2000)
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "NIVEL")
	private Integer nivel;
	
	@Column(name = "ORDEN")
	private Short orden;

	@Size(max = 4000)
	@Column(name = "URL")
	private String url;
	
	@Size(max = 500)
	@Column(name = "ICONO")
	private String icono;

	@Size(max = 1)
	@Column(name = "ES_OPCION_FINAL")
	private String esOpcionFinal;
	
	@Size(max = 1)
	@Column(name = "ES_SISTEMA")
	private String esSistema;
 
	@Size(max = 1)
	@Column(name = "ES_SELECCION_INICIAL")
	private String esSeleccionInicial; 

	@Size(max = 1)
	@Column(name = "ESTADO")
	private String estado; 
 
	@Column(name = "FECHA_INGRESO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;

	@Size(max = 15)
	@Column(name = "USUARIO_INGRESO")
	private String usuarioIngreso;

	@Column(name = "FECHA_MODIFICACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;

	@Size(max = 15)
	@Column(name = "USUARIO_MODIFICACION")
	private String usuarioModificacion;
 
 
 
 
	// RECURSIVIDAD CODIGO_OPCION-CODIGO_PADRE
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CODIGO_MENU_PADRE", referencedColumnName = "CODIGO_MENU")
	private GenMenuOpciones genMenuOpciones;

//	@OneToMany(mappedBy = "dafOpcionesMenu", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
//	private List<DafOpcionesMenu> dafOpcionesMenuList;

}
