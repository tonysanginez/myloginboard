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
@Table(name = "gen_formas_pago")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GenFormasPago implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @NotNull
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CODIGO_FORMA_PAGO")
    private Long codigoFormasPago;
	
    @NotNull
    @Column(name = "CODIGO_EMPRESA")
    private Short codigoEmpresa;
    
    @NotNull
    @Size(max = 30)
    @Column(name = "NEMONICO")
    private String nemonico;
    
    @NotNull
    @Size(max = 45)
    @Column(name = "NOMBRE_FORMA_PAGO")
    private String nombreFormaPago;
	
    @Size(max = 2)
    @Column(name = "CODIGO_SRI")
    private String codigoSri;
    
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
