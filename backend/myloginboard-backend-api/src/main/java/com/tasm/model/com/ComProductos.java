package com.tasm.model.com;

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
@Table(name = "com_productos")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
//@NamedQueries({
//    @NamedQuery(name = "ComProductos.maxCodigoProducto", query = "SELECT MAX(p.codigoProducto) FROM ComProductos p")
//})
public class ComProductos implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
    @EqualsAndHashCode.Include
    @Column(name = "CODIGO_PRODUCTO")
    private Long codigoProducto;
    
    @NotNull
    @Column(name = "CODIGO_EMPRESA")
    private Short codigoEmpresa;
    
    @NotNull
    @Size(max = 500)
    @Column(name = "NOMBRE_PRODUCTO")
    private String nombreProducto;
    
    @Size(max = 2000)
    @Column(name = "DESCRIPCION")
    private String descripcion;
    
    @NotNull
    @Column(name = "CODIGO_TIPO_PRODUCTO")
    private Integer codigoTipoProducto;
    
    @NotNull
    @Column(name = "CODIGO_TIPO_PRESENTACION")
    private Integer codigoTipoPresentacion;
    
    @NotNull
    @Column(name = "CODIGO_MARCA")
    private Integer codigoMarca;
    
    @NotNull
    @Column(name = "CODIGO_UNIDAD_MEDIDA")
    private Integer codigoUnidadMedida;

    @NotNull
    @Size(max = 100)
    @Column(name = "CODIGO_BARRA")
    private String codigoBarra;
    
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "APLICA_IVA")
    private String aplicaIva;
    
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "APLICA_STOCK")
    private String aplicaStock;
    
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
