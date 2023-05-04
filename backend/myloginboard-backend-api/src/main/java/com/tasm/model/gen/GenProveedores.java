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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "gen_proveedores")
public class GenProveedores implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
	@EqualsAndHashCode.Include
	@Column(name = "CODIGO_INSTITUCION")
	private Long codigoInstitucion;
		
    @NotNull
	@Column(name = "CODIGO_FORMA_PAGO")
	private Long codigoFormaPago;

	@Size(max = 45)
	@Column(name = "PLAZO_OTORGADO")
	private String plazoOtorgado;

	@Size(max = 1000)
	@Column(name = "DIRECCION_RETIRO")
	private String direccionRetiro;
	
	@Size(max = 120)
	@Column(name = "NOMBRE_CONTACTO_RETIRO")
	private String nombreContactoRetiro;

	@NotNull
	@Size(max = 1)
	@Column(name = "ESTADO")
	private String estado; 
 
	@NotNull
	@Column(name = "FECHA_INGRESO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;

	@NotNull
	@Size(max = 15)
	@Column(name = "USUARIO_INGRESO")
	private String usuarioIngreso;

	@Column(name = "FECHA_MODIFICACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;

	@Size(max = 15)
	@Column(name = "USUARIO_MODIFICACION")
	private String usuarioModificacion;
 
}
