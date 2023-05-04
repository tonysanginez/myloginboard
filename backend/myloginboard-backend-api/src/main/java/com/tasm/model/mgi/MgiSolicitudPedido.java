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
@Table(name = "mgi_solicitud_pedido")
public class MgiSolicitudPedido {
	@Id
	@NotNull
	@EqualsAndHashCode.Include
	@Column(name = "CODIGO_SOLICITUD")
	private Long codigoSolicitud;
	
	@NotNull
    @Column(name = "CODIGO_EMPRESA")
    private Short codigoEmpresa;
	
	@Column(name = "CODIGO_CLIENTE")
	private Long codigoCliente;
	
	@Column(name = "ESTADO_SOLICITUD")
	private String estadoSolicitud;
	
	@Column(name = "FECHA_SOLICITUD"/* , insertable = false, updatable = false */)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaSolicitud;
	
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
	
	@Column(name = "FECHA_CANCELACION"/* , insertable = false, updatable = false */)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCancelacion;

	@Size(max = 15)
	@Column(name = "USUARIO_CANCELACION")
	private String usuarioCancelacion;
	
	@Column(name = "FECHA_APROBACION"/* , insertable = false, updatable = false */)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaAprobacion;

	@Size(max = 15)
	@Column(name = "USUARIO_APROBACION")
	private String usuarioAprobacion;
	
	@Size(min = 1, max = 1)
	@Column(name = "ES_REGISTRADO")
	private String esRegistrado;
	
	@Column(name = "FECHA_REGISTRO_PEDIDO"/* , insertable = false, updatable = false */)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaRegistroPedido;

	@Size(max = 15)
	@Column(name = "USUARIO_REGISTRO")
	private String usuarioRegistro;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "TELEFONO")
	private String telefono;

	@Column(name = "OBSERVACION")
	private String observacion;
	
	@Column(name = "FECHA_ENTREGA"/* , insertable = false, updatable = false */)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaEntrega;
	
	@Column(name = "CODIGO_TARIFARIO")
    private Short codigoTarifario;
	
	@Column(name = "CODIGO_PLANTILLA")
    private Short codigoPlantilla;

	public Long getCodigoSolicitud() {
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public Short getCodigoEmpresa() {
		return codigoEmpresa;
	}

	public void setCodigoEmpresa(Short codigoEmpresa) {
		this.codigoEmpresa = codigoEmpresa;
	}

	public Long getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(Long codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getEstadoSolicitud() {
		return estadoSolicitud;
	}

	public void setEstadoSolicitud(String estadoSolicitud) {
		this.estadoSolicitud = estadoSolicitud;
	}

	public Date getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(Date fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
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

	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}

	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}

	public String getUsuarioCancelacion() {
		return usuarioCancelacion;
	}

	public void setUsuarioCancelacion(String usuarioCancelacion) {
		this.usuarioCancelacion = usuarioCancelacion;
	}

	public Date getFechaAprobacion() {
		return fechaAprobacion;
	}

	public void setFechaAprobacion(Date fechaAprobacion) {
		this.fechaAprobacion = fechaAprobacion;
	}

	public String getUsuarioAprobacion() {
		return usuarioAprobacion;
	}

	public void setUsuarioAprobacion(String usuarioAprobacion) {
		this.usuarioAprobacion = usuarioAprobacion;
	}

	public String getEsRegistrado() {
		return esRegistrado;
	}

	public void setEsRegistrado(String esRegistrado) {
		this.esRegistrado = esRegistrado;
	}

	public Date getFechaRegistroPedido() {
		return fechaRegistroPedido;
	}

	public void setFechaRegistroPedido(Date fechaRegistroPedido) {
		this.fechaRegistroPedido = fechaRegistroPedido;
	}

	public String getUsuarioRegistro() {
		return usuarioRegistro;
	}

	public void setUsuarioRegistro(String usuarioRegistro) {
		this.usuarioRegistro = usuarioRegistro;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Short getCodigoTarifario() {
		return codigoTarifario;
	}

	public void setCodigoTarifario(Short codigoTarifario) {
		this.codigoTarifario = codigoTarifario;
	}

	public Short getCodigoPlantilla() {
		return codigoPlantilla;
	}

	public void setCodigoPlantilla(Short codigoPlantilla) {
		this.codigoPlantilla = codigoPlantilla;
	}

	

	
	
}
