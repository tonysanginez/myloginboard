package com.tasm.model.sec;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.tasm.util.ActivoConverter;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@Table(name = "sec_companies")
public class SecCompanies implements Serializable{
private static final long serialVersionUID = 1L;
	
	@Id
    @NotNull
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_company")
	private Long idCompany;
	
	@Column(name = "name")
	private String name;
	
	@Convert(converter = ActivoConverter.class)
	@Column(name = "status")
	private boolean status;
	
	@Column(name = "entry_date")
	private LocalDateTime entryDate;
	
	@Column(name = "entry_user")
	private String entryUser;
	
	@Column(name = "modify_date")
	private LocalDateTime modifyDate;
	
	@Column(name = "modify_user")
	private String modifyUser;
}
