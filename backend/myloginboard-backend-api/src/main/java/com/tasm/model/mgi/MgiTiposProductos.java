package com.tasm.model.mgi;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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

@Entity
@Table(name = "mgi_tipos_productos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@NamedQueries({
//    @NamedQuery(name = "ComProductos.maxCodigoProducto", query = "SELECT MAX(p.codigoProducto) FROM ComProductos p")
//})
public class MgiTiposProductos implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
    @EqualsAndHashCode.Include
    @Column(name = "CODIGO_TIPO_PRODUCTO")
    private Long codigoTipoProducto;
    
    @NotNull
    @Column(name = "CODIGO_EMPRESA")
    private Short codigoEmpresa;
    
    @NotNull
    @Size(max = 500)
    @Column(name = "NOMBRE_TIPO_PRODUCTO")
    private String nombreTipoProducto; 
    
    @NotNull
    @Size(min = 1, max = 1)
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
