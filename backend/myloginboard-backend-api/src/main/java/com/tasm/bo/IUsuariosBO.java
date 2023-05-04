package com.tasm.bo;

import java.util.List;

import com.tasm.dto.OpcionesMenuDTO;
import com.tasm.dto.OrganizacionesDTO;
import com.tasm.dto.SucursalesAccesoUsuarioDTO;
import com.tasm.dto.VerificacionCuentaDTO;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.UnauthorizedException;

public interface IUsuariosBO {
	
	  
	public List<OpcionesMenuDTO> consultarOpcionesMenuAccesoUsuario(
			Long bgdSecuenciaUsuario,  
			Integer intCodigoSucursal, 
			Short shoCodigoEmpresa) throws BOException , UnauthorizedException;
	
	public List<SucursalesAccesoUsuarioDTO> consultarSucursalesXUsuario(Long secuenciaUsuario) throws BOException ;


	public VerificacionCuentaDTO verificarCuenta(String strUsuario) throws BOException ;
	List<OrganizacionesDTO> consultarOrganizaciones() throws BOException;
}