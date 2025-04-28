package com.tasm.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tasm.bo.IUsuariosBO;
import com.tasm.dao.GenMenuOpcionesDAO;
import com.tasm.dao.SecUsersDAO;
import com.tasm.dao.GenUsuariosXSucursalDAO;
import com.tasm.dto.OpcionesMenuDTO;
import com.tasm.dto.OrganizacionesDTO;
import com.tasm.dto.SucursalesAccesoUsuarioDTO;
import com.tasm.dto.VerificacionCuentaDTO;
import com.tasm.exceptions.BOException;
import com.tasm.model.gen.GenUsuarios;
import com.tasm.model.sec.SecUsers;
import com.tasm.util.GenericUtil; 

@Service
public class UsuariosBOImpl implements IUsuariosBO {
	 
	 
	@Autowired
	private GenMenuOpcionesDAO objGenMenuOpcionesDAO; 
	@Autowired
	private GenUsuariosXSucursalDAO objGenUsuariosXSucursalDAO; 
	@Autowired
	private SecUsersDAO objSecUsersDAO;
	
	@Override
	public List<OpcionesMenuDTO> consultarOpcionesMenuAccesoUsuario(
			Long bgdSecuenciaUsuario,  
			Integer intCodigoSucursal, 
			Short shoCodigoEmpresa) throws BOException {
		// Valida si la SecuenciaUsuario es null o vacio
//		GenericUtil.validarCampoRequeridoBO(bgdSecuenciaUsuario == null ? null : String.valueOf(bgdSecuenciaUsuario), "seg.campos.secuenciaUsuario"); 
		// Valida si la el codigoSucursal es null o vacio
//		GenericUtil.validarCampoRequeridoBO(intCodigoSucursal == null ? null : String.valueOf(intCodigoSucursal), "seg.campos.codigoSucursal");
//		String strEsActivo = "S";
 		List<OpcionesMenuDTO> lsOpcionesUsuario = objGenMenuOpcionesDAO.consultarOpcionesMenuAccesoUsuario(bgdSecuenciaUsuario, shoCodigoEmpresa.intValue(), intCodigoSucursal);
 		if (lsOpcionesUsuario == null || lsOpcionesUsuario.isEmpty()) {
			throw new BOException("seg.warn.usuarioAccesoOpcionesModulo");
		}
  		return lsOpcionesUsuario;
	}
	    
	
	@Override
	public List<SucursalesAccesoUsuarioDTO> consultarSucursalesXUsuario(Long secuenciaUsuario) throws BOException {
		// Valida campos obligatorios
		GenericUtil.validarCampoRequeridoBO(secuenciaUsuario==null?null:String.valueOf(secuenciaUsuario),"seg.campos.secuenciaUsuario");
		 
		return objGenUsuariosXSucursalDAO.consultarSucursalesXUsuario(secuenciaUsuario);
	}
	
	@Override
	public VerificacionCuentaDTO verificarCuenta(String strUsuario) throws BOException {
	
		GenericUtil.validarCampoRequeridoBO(strUsuario, "tasm.campos.usuario");
		
		VerificacionCuentaDTO objVerifCuenta = new VerificacionCuentaDTO();
		SecUsers objUsuario = objSecUsersDAO.validarUsuarioActivo(strUsuario);

 		if (objUsuario == null) {
			throw new BOException("tasm.warn.usuarioNoExiste");
		} else if (!objUsuario.isStatus()) {
			throw new BOException("tasm.warn.cuentaInactiva");
		}
 		objVerifCuenta.setCuentaActiva(true);
		objVerifCuenta.setTipoAcceso(null);
		objVerifCuenta.setPrimerNombre(objUsuario.getUsername());
		 
 		return objVerifCuenta;
	}
	@Override
	public List<OrganizacionesDTO> consultarOrganizaciones() throws BOException {
		// Valida campos obligatorios
		//GenericUtil.validarCampoRequeridoBO(secuenciaUsuario==null?null:String.valueOf(secuenciaUsuario),"seg.campos.secuenciaUsuario");
		 
		return objGenUsuariosXSucursalDAO.consultarOrganizaciones();
	}
}
