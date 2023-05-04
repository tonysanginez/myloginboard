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
@Table(name = "mgi_tipos_presentacion")
public class MgiTipoPresentacion {
	@Id
	@NotNull
	@EqualsAndHashCode.Include
	@Column(name = "CODIGO_TIPO_PRESENTACION")
	private Integer codigoTipoPresentacion;
	
	@NotNull
    @Column(name = "CODIGO_EMPRESA")
    private Short codigoEmpresa;
	
	@Size(max = 80)
	@Column(name = "NOMBRE_TIPO_PRESENTACION")
	private String nombreTipoPresentacion;
	
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

	public Integer getCodigoTipoPresentacion() {
		return codigoTipoPresentacion;
	}

	public void setCodigoTipoPresentacion(Integer codigoTipoPresentacion) {
		this.codigoTipoPresentacion = codigoTipoPresentacion;
	}

	public Short getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(Short codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public String getNombreTipoPresentacion() {
		return nombreTipoPresentacion;
	}

	public void setNombreTipoPresentacion(String nombreTipoPresentacion) {
		this.nombreTipoPresentacion = nombreTipoPresentacion;
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
	
	
}
