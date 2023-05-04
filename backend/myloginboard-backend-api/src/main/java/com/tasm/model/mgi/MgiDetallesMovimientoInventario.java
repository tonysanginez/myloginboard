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
@Table(name = "mgi_detalles_movimiento_inventario")
public class MgiDetallesMovimientoInventario {
	@Id
	@NotNull
	@EqualsAndHashCode.Include
	@Column(name = "LINEA_DETALLE")
	private Long lineaDetalle;
	
	@NotNull
	@Column(name = "CODIGO_MOVIMIENTO_INVENTARIO")
	private Long codigoMovimientoInventario;
	
	@NotNull
    @Column(name = "CODIGO_EMPRESA")
    private Short codigoEmpresa;
	
	@Column(name="CODIGO_PRODUCTO")
	private Long codigoProducto;
	
	@Column(name="CANTIDAD")
	private Integer cantidad;
	
	@Column(name="COSTO_PRODUCTO")
	private Double costoProducto;
	
	@Column(name="SUBTOTAL_PRODUCTO")
	private Double subTotalProducto;
	
	@Column(name="VALOR_IVA")
	private Double valorIva;
	
	@Column(name="VALOR_DESCUENTO")
	private Double valorDescuento;
	
	@Column(name="TOTAL_PRODUCTO")
	private Double totalProducto;
	
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

	public Long getLineaDetalle() {
		return lineaDetalle;
	}

	public void setLineaDetalle(Long lineaDetalle) {
		this.lineaDetalle = lineaDetalle;
	}

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

	public Long getCodigoProducto() {
		return codigoProducto;
	}

	public void setCodigoProducto(Long codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getCostoProducto() {
		return costoProducto;
	}

	public void setCostoProducto(Double costoProducto) {
		this.costoProducto = costoProducto;
	}

	public Double getSubTotalProducto() {
		return subTotalProducto;
	}

	public void setSubTotalProducto(Double subTotalProducto) {
		this.subTotalProducto = subTotalProducto;
	}

	public Double getValorIva() {
		return valorIva;
	}

	public void setValorIva(Double valorIva) {
		this.valorIva = valorIva;
	}

	public Double getValorDescuento() {
		return valorDescuento;
	}

	public void setValorDescuento(Double valorDescuento) {
		this.valorDescuento = valorDescuento;
	}

	public Double getTotalProducto() {
		return totalProducto;
	}

	public void setTotalProducto(Double totalProducto) {
		this.totalProducto = totalProducto;
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
