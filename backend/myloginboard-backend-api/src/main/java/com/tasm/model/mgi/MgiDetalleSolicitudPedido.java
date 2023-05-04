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
@Table(name = "mgi_detalle_solicitud_pedido")
public class MgiDetalleSolicitudPedido {
	@Id
	@NotNull
	@EqualsAndHashCode.Include
	@Column(name = "CODIGO_SOLICITUD")
	private Long codigoSolicitud;
	
	@Column(name = "LINEA_DETALLE")
	private Short lineaDetalle;
	
	@Column(name = "CODIGO_PRODUCTO")
	private Long codigoProducto;
	
	@Column(name = "CODIGO_TARIFARIO")
    private Short codigoTarifario;
	
	@Size(min = 1, max = 1)
	@Column(name = "TIENE_TARIFARIO")
	private String tieneTarifario;
	
	@Column(name = "CANTIDAD_PEDIDO")
	private Short cantidadPedido;
	
	@Column(name = "VALOR_TARIFARIO")
	private Double valorTarifario;
	
	@Column(name = "SUBTOTAL")
	private Double subtotal;
	
	@Column(name = "IVA")
	private Double iva;
	
	@Column(name = "TOTAL")
	private Double total;
	
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
	
	@Column(name = "CODIGO_MEDIDA_CONVERSION")
    private Short codigoMedidaConversion;

	public Long getCodigoSolicitud() {
		return codigoSolicitud;
	}

	public void setCodigoSolicitud(Long codigoSolicitud) {
		this.codigoSolicitud = codigoSolicitud;
	}

	public Short getLineaDetalle() {
		return lineaDetalle;
	}

	public void setLineaDetalle(Short lineaDetalle) {
		this.lineaDetalle = lineaDetalle;
	}

	public Long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Short getCodigoTarifario() {
		return codigoTarifario;
	}

	public void setCodigoTarifario(Short codigoTarifario) {
		this.codigoTarifario = codigoTarifario;
	}

	public String getTieneTarifario() {
		return tieneTarifario;
	}

	public void setTieneTarifario(String tieneTarifario) {
		this.tieneTarifario = tieneTarifario;
	}

	public Short getCantidadPedido() {
		return cantidadPedido;
	}

	public void setCantidadPedido(Short cantidadPedido) {
		this.cantidadPedido = cantidadPedido;
	}

	public Double getValorTarifario() {
		return valorTarifario;
	}

	public void setValorTarifario(Double valorTarifario) {
		this.valorTarifario = valorTarifario;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public Double getIva() {
		return iva;
	}

	public void setIva(Double iva) {
		this.iva = iva;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
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

	public Short getCodigoMedidaConversion() {
		return codigoMedidaConversion;
	}

	public void setCodigoMedidaConversion(Short codigoMedidaConversion) {
		this.codigoMedidaConversion = codigoMedidaConversion;
	}

	

	
}
