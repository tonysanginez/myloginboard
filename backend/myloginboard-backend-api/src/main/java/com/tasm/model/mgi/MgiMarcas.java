package com.tasm.model.mgi;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;

@Entity
@Table(name = "mgi_marcas")
public class MgiMarcas {
	@Id
	@NotNull
	@EqualsAndHashCode.Include
	@Column(name = "CODIGO_MARCA")
	private Integer codigoMarca;
	
	@NotNull
    @Column(name = "CODIGO_EMPRESA")
    private Short codigoEmpresa;
	
	@Size(max = 80)
	@Column(name = "NOMBRE_MARCA")
	private String nombreMarca;
	
	@NotNull
	@Size(min = 1, max = 1)
	@Column(name = "ESTADO")
	private String estado;
	
	@Column(name = "FECHA_INGRESO"/* , insertable = false, updatable = false */)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaIngreso;

	@Size(max = 15)
	@Column(name = "USUARIO_INGRESO")
	private String usuarioIngreso;

	@Column(name = "FECHA_MODIFICACION"/* , insertable = false, updatable = false */)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaModificacion;

	@Size(max = 15)
	@Column(name = "USUARIO_MODIFICACION")
	private String usuarioModificacion;
	

	public Integer getCodigoMarca() {
		return codigoMarca;
	}

	public void setCodigoMarca(Integer codigoMarca) {
		this.codigoMarca = codigoMarca;
	}

	public String getNombreMarca() {
		return nombreMarca;
	}

	public void setNombreMarca(String nombreMarca) {
		this.nombreMarca = nombreMarca;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getUsuarioIngreso() {
		return usuarioIngreso;
	}

	public void setUsuarioIngreso(String usuarioIngreso) {
		this.usuarioIngreso = usuarioIngreso;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	public Short getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(Short codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	
	
}
