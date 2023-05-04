package com.tasm.model.gen;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "gen_detalles_actividad")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GenDetallesActividad implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_DETALLE_ACTIVIDAD")
    private Long codigoDetalleActividad;
	
    @NotNull
    @Size(max = 11)
    @Column(name = "CODIGO_TIPO_ACTIVIDAD")
    private Integer codigoTipoActividad;
	
    @NotNull
    @Size(max = 80)
    @Column(name = "NOMBRE_DETALLE_ACTIVIDAD")
    private String nombreDetalleActividad;
    
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
