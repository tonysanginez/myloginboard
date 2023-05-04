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
@Table(name = "mgi_movimiento_inventario")
public class MgiMovimientoInventario {
	@Id
	@NotNull
	@EqualsAndHashCode.Include
	@Column(name = "CODIGO_MOVIMIENTO_INVENTARIO")
	private Long codigoMovimientoInventario;
	
	@NotNull
    @Column(name = "CODIGO_EMPRESA")
    private Short codigoEmpresa;
	
	@Column(name="CODIGO_TIPO_DOCUMENTO")
	private Integer codigoTipoDocumento;	
	
	@Size(max = 300)
	@Column(name = "DESCRIPCION")
	private String descripcion;
	
	@Column(name = "CODIGO_INSTITUCION")
	private Long codigoInstitucion;
	
	@Size(max = 100)
	@Column(name = "REFERENCIA_COMPROBANTE")
	private String referenciaComprobante;
	
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

	public Long getCodigoMovimientoInventario() {
		return codigoMovimientoInventario;
	}

	public void setCodigoMovimientoInventario(Long codigoMovimientoInventario) {
		this.codigoMovimientoInventario = codigoMovimientoInventario;
	}

	public Short getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(Short codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public Integer getCodigoTipoDocumento() {
		return codigoTipoDocumento;
	}

	public void setCodigoTipoDocumento(Integer codigoTipoDocumento) {
		this.codigoTipoDocumento = codigoTipoDocumento;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getCodigoInstitucion() {
		return codigoInstitucion;
	}

	public void setCodigoInstitucion(Long codigoInstitucion) {
		this.codigoInstitucion = codigoInstitucion;
	}

	public String getReferenciaComprobante() {
		return referenciaComprobante;
	}

	public void setReferenciaComprobante(String referenciaComprobante) {
		this.referenciaComprobante = referenciaComprobante;
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
