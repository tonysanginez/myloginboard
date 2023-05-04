package com.tasm.bo.impl;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tasm.bo.IProveedoresBO;
import com.tasm.bo.IUtilBO;
import com.tasm.dao.GenDetallesActividadDAO;
import com.tasm.dao.GenFormasPagoDAO;
import com.tasm.dao.GenInstitucionesDAO;
import com.tasm.dao.GenPaisesDAO;
import com.tasm.dao.GenProveedoresDAO;
import com.tasm.dao.GenTiposActividadDAO;
import com.tasm.dao.GenTiposIdentificacionDAO;
import com.tasm.dto.InputCrearProveedorDTO;
import com.tasm.dto.ProveedoresDTO;
import com.tasm.enums.TipoNumero;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.exceptions.RestClientException;
import com.tasm.model.gen.GenDetallesActividad;
import com.tasm.model.gen.GenFormasPago;
import com.tasm.model.gen.GenInstituciones;
import com.tasm.model.gen.GenPaises;
import com.tasm.model.gen.GenProveedores;
import com.tasm.model.gen.GenTiposActividad;
import com.tasm.model.gen.GenTiposIdentificacion;
import com.tasm.model.gen.GenTiposIdentificacionCPK;
import com.tasm.security.UsuarioLogin;
import com.tasm.util.GenericUtil;

@Service
public class ProveedoresBOImpl implements IProveedoresBO{

	@Autowired
    private GenProveedoresDAO objGenProveedoresDAO;
	
    @Autowired
    private GenInstitucionesDAO objGenInstitucionesDAO;
    
    @Autowired
    private GenFormasPagoDAO objGenFormasPagoDAO;
    
    @Autowired
    private GenTiposIdentificacionDAO objGenTiposIdentificacionDAO;
    
    @Autowired
    private GenDetallesActividadDAO objGenDetallesActividadDAO;
    
    @Autowired
    private GenTiposActividadDAO objGenTiposActividadDAO;
    
    
    @Autowired
    private IUtilBO objIUtilBO;
    
    @Autowired
    private GenPaisesDAO objGenPaisesDAO; 

	@Override
	public Map<String, Object> obtenerProveedores(Long codigoInstitucion, String numeroIdentificacion,
			String nombreInstitucion, String nombreComercial, String direccion, String correoElectronico,
			String telefonoMovil, String nombrePersonaContacto, Short codigoEmpresa, Integer page, Integer perPage,
			String filtroGeneral, String estado) throws BOException {
		GenericUtil.validarCampoRequeridoBO(codigoEmpresa, "tasm.campos.codigoEmpresa");
		GenericUtil.validarRequeridoMayorACero(page, "tasm.campos.page");
		GenericUtil.validarRequeridoMayorACero(perPage, "tasm.campos.perPage");
		GenericUtil.validarCampoRequeridoBO(estado, "tasm.campos.estado");
        return objGenProveedoresDAO.obtenerProveedores(codigoInstitucion,
				numeroIdentificacion,
				nombreInstitucion,
				nombreComercial,
				direccion,
				correoElectronico,
				telefonoMovil,
				nombrePersonaContacto,
				codigoEmpresa, page, perPage, filtroGeneral, estado);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void crearProveedor(InputCrearProveedorDTO objInputCrearProveedorDTO, UsuarioLogin objUsuarioLogin)
			throws BOException {

		GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getCodigoEmpresa(), "tasm.campos.codigoEmpresa");
		GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getCodigoDetalleActividad(), "tasm.campos.codigoDetalleActividad");
		GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getCodigoTipoIdentificacion(), "tasm.campos.codigoTipoIdentificacion");
		GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getNumeroIdentificacion(), "tasm.campos.numeroIdentificacion");
		GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getNombreInstitucion(), "tasm.campos.nombreInstitucion");
		GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getNombreComercial(), "tasm.campos.nombreComercial");
		//validar identificacion
		Map<String, Boolean> MapvalidarIdentificacion = objIUtilBO.validarIdentificacion(objInputCrearProveedorDTO.getNumeroIdentificacion(),
										 												 objInputCrearProveedorDTO.getCodigoTipoIdentificacion().shortValue(),
										 												 objInputCrearProveedorDTO.getCodigoEmpresa().shortValue());
		if (MapvalidarIdentificacion.get("esIdentificacionValida")!=null && !(boolean)(MapvalidarIdentificacion.get("esIdentificacionValida"))) {
			throw new BOException("tasm.warn.identificacionNoValida");
		}
		//validar numero cedular
		if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getCodigoPaisTelMovil())) {
			GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getCodigoPaisTelMovil(), "tasm.campos.codigoPaisTelefonico");
			GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getTelefonoMovil(), "tasm.campos.numeroTelefonico");
			Optional<GenPaises> objGenPaises = objGenPaisesDAO.find(objInputCrearProveedorDTO.getCodigoPaisTelMovil());
			if (!objGenPaises.isPresent())
				throw new BOException("tasm.warn.tasm.campos.codigoDePaisNoExiste");
			Map<String, Object> MapvalidarTelefono = objIUtilBO.validarNumeroTelefonico(objInputCrearProveedorDTO.getTelefonoMovil(),
					 objGenPaises.get().getCodigoISO(),
					TipoNumero.MOBILE.getName());
			if (MapvalidarTelefono.get("RESULTADO_VALIDACION")!=null && !Boolean.parseBoolean(MapvalidarTelefono.get("RESULTADO_VALIDACION").toString()))
				throw new BOException("tasm.warn.numeroTelefonicoInvalido");
		}
		//validar numero telefono
		if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getCodigoPaisTelFijo())) {
			GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getCodigoPaisTelFijo(), "tasm.campos.codigoPaisConvencional");
			GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getTelefonoFijo(), "tasm.campos.numeroConvencional");
			Optional<GenPaises> objGenPaises = objGenPaisesDAO.find(objInputCrearProveedorDTO.getCodigoPaisTelFijo());
			if (!objGenPaises.isPresent())
				throw new BOException("tasm.warn.tasm.campos.codigoDePaisNoExiste");
			Map<String, Object> MapvalidarTelefono = objIUtilBO.validarNumeroTelefonico(objInputCrearProveedorDTO.getTelefonoFijo(),
					 objGenPaises.get().getCodigoISO(),
					TipoNumero.FIXED_LINE.getName());
			if (MapvalidarTelefono.get("RESULTADO_VALIDACION")!=null && !Boolean.parseBoolean(MapvalidarTelefono.get("RESULTADO_VALIDACION").toString()))
				throw new BOException("tasm.warn.numeroTelefonicoInvalido");
		}
		//proveedores
		GenInstituciones objGenInstituciones = GenInstituciones.builder()
				.codigoEmpresa(objInputCrearProveedorDTO.getCodigoEmpresa())
				.codigoDetalleActividad(objInputCrearProveedorDTO.getCodigoDetalleActividad())
				.codigoTipoIdentificacion(objInputCrearProveedorDTO.getCodigoTipoIdentificacion())
				.numeroIdentificacion(objInputCrearProveedorDTO.getNumeroIdentificacion())
				.nombreInstitucion(objInputCrearProveedorDTO.getNombreInstitucion())
				.nombreComercial(objInputCrearProveedorDTO.getNombreComercial())
				.codigoPaisTelFijo(objInputCrearProveedorDTO.getCodigoPaisTelFijo())
				.codigoPaisTelMovil(objInputCrearProveedorDTO.getCodigoPaisTelMovil())
				.codigoPaisTelMovilContacto(objInputCrearProveedorDTO.getCodigoPaisTelMovilContacto())
				.direccion(objInputCrearProveedorDTO.getDireccion())
				.paginaWeb(objInputCrearProveedorDTO.getPaginaWeb())
				.correoElectronico(objInputCrearProveedorDTO.getCorreoElectronico())
				.telefonoMovil(objInputCrearProveedorDTO.getTelefonoMovil())
				.telefonoMovilE164(objInputCrearProveedorDTO.getTelefonoMovilE164())
				.telefonoFijo(objInputCrearProveedorDTO.getTelefonoFijo())
				.telefonoFijoE164(objInputCrearProveedorDTO.getTelefonoFijoE164())
				.nombrePersonaContacto(objInputCrearProveedorDTO.getNombrePersonaContacto())
				.telefonoMovilContacto(objInputCrearProveedorDTO.getTelefonoMovilContacto())
				.telefonoMovilContactoE164(objInputCrearProveedorDTO.getTelefonoMovilContactoE164())
				.nombreRepresentanteLegal(objInputCrearProveedorDTO.getNombreRepresentanteLegal())
				.nombrePresidente(objInputCrearProveedorDTO.getNombrePresidente())
				.nombreGerente(objInputCrearProveedorDTO.getNombreGerente())
				.registroSanitario(objInputCrearProveedorDTO.getRegistroSanitario())
				.tipoPersona(objInputCrearProveedorDTO.getTipoPersona())
				.esContribuyenteEspecial(objInputCrearProveedorDTO.getEsContribuyenteEspecial())
				.esGubernamental(objInputCrearProveedorDTO.getEsGubernamental())
				.esContribuyenterise(objInputCrearProveedorDTO.getEsContribuyenterise())
				.llevaContabilidad(objInputCrearProveedorDTO.getLlevaContabilidad())
				.esExtranjero(objInputCrearProveedorDTO.getEsExtranjero())
				.esProveedor(objInputCrearProveedorDTO.getEsProveedor())
				.estado("A")
				.usuarioIngreso(objUsuarioLogin.getCodigoUsuario())
				.fechaIngreso(new Date())
				.build();
		objGenInstitucionesDAO.persist(objGenInstituciones);
		
		GenProveedores objGenProveedores = GenProveedores.builder()
				.codigoInstitucion(objGenInstituciones.getCodigoInstitucion())
				.codigoFormaPago(objInputCrearProveedorDTO.getCodigoFormasPago().longValue())
				.plazoOtorgado(objInputCrearProveedorDTO.getPlazoOtorgado())
				.direccionRetiro(objInputCrearProveedorDTO.getDireccionRetiro())
				.nombreContactoRetiro(objInputCrearProveedorDTO.getNombreContactoRetiro())
				.estado("A")
				.usuarioIngreso(objUsuarioLogin.getCodigoUsuario())
				.fechaIngreso(new Date())
				.build();
		objGenProveedoresDAO.persist(objGenProveedores);
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void editarProveedor(Long codigoProveedor, InputCrearProveedorDTO objInputCrearProveedorDTO,
			UsuarioLogin objUsuarioLogin) throws BOException {
		Optional<GenInstituciones> optGenInstituciones = objGenInstitucionesDAO.find(codigoProveedor);
		Optional<GenProveedores> optGenProveedores = objGenProveedoresDAO.find(codigoProveedor);
		if (optGenInstituciones.isPresent() && optGenProveedores.isPresent()) {
			GenInstituciones objGenInstituciones = optGenInstituciones.get();
            if (objInputCrearProveedorDTO.getCodigoEmpresa()!= null)
            	objGenInstituciones.setCodigoEmpresa(objInputCrearProveedorDTO.getCodigoEmpresa());
            if (objInputCrearProveedorDTO.getCodigoDetalleActividad()!= null)
            	objGenInstituciones.setCodigoDetalleActividad(objInputCrearProveedorDTO.getCodigoDetalleActividad());
            if (objInputCrearProveedorDTO.getCodigoTipoIdentificacion()!= null)
            	objGenInstituciones.setCodigoTipoIdentificacion(objInputCrearProveedorDTO.getCodigoTipoIdentificacion());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getNumeroIdentificacion()))
            	objGenInstituciones.setNumeroIdentificacion(objInputCrearProveedorDTO.getNumeroIdentificacion());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getNombreInstitucion()))
            	objGenInstituciones.setNombreInstitucion(objInputCrearProveedorDTO.getNombreInstitucion());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getNombreComercial()))
            	objGenInstituciones.setNombreComercial(objInputCrearProveedorDTO.getNombreComercial());
            if (objInputCrearProveedorDTO.getCodigoPaisTelFijo()!= null)
            	objGenInstituciones.setCodigoPaisTelFijo(objInputCrearProveedorDTO.getCodigoPaisTelFijo());
            if (objInputCrearProveedorDTO.getCodigoPaisTelMovil()!= null)
            	objGenInstituciones.setCodigoPaisTelMovil(objInputCrearProveedorDTO.getCodigoPaisTelMovil());
            if (objInputCrearProveedorDTO.getCodigoPaisTelMovilContacto()!= null)
            	objGenInstituciones.setCodigoPaisTelMovilContacto(objInputCrearProveedorDTO.getCodigoPaisTelMovilContacto());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getDireccion()))
            	objGenInstituciones.setDireccion(objInputCrearProveedorDTO.getDireccion());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getPaginaWeb()))
            	objGenInstituciones.setPaginaWeb(objInputCrearProveedorDTO.getPaginaWeb());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getCorreoElectronico()))
            	objGenInstituciones.setCorreoElectronico(objInputCrearProveedorDTO.getCorreoElectronico());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getTelefonoMovil()))
            	objGenInstituciones.setTelefonoMovil(objInputCrearProveedorDTO.getTelefonoMovil());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getTelefonoMovilE164()))
            	objGenInstituciones.setTelefonoMovilE164(objInputCrearProveedorDTO.getTelefonoMovilE164());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getTelefonoFijo()))
            	objGenInstituciones.setTelefonoFijo(objInputCrearProveedorDTO.getTelefonoFijo());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getTelefonoFijoE164()))
            	objGenInstituciones.setTelefonoFijoE164(objInputCrearProveedorDTO.getTelefonoFijoE164());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getNombrePersonaContacto()))
            	objGenInstituciones.setNombrePersonaContacto(objInputCrearProveedorDTO.getNombrePersonaContacto());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getTelefonoMovilContacto()))
            	objGenInstituciones.setTelefonoMovilContacto(objInputCrearProveedorDTO.getTelefonoMovilContacto());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getTelefonoMovilContactoE164()))
            	objGenInstituciones.setTelefonoMovilContactoE164(objInputCrearProveedorDTO.getTelefonoMovilContactoE164());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getNombreRepresentanteLegal()))
            	objGenInstituciones.setNombreRepresentanteLegal(objInputCrearProveedorDTO.getNombreRepresentanteLegal());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getNombrePresidente()))
            	objGenInstituciones.setNombrePresidente(objInputCrearProveedorDTO.getNombrePresidente());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getNombreGerente()))
            	objGenInstituciones.setNombreGerente(objInputCrearProveedorDTO.getNombreGerente());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getRegistroSanitario()))
            	objGenInstituciones.setRegistroSanitario(objInputCrearProveedorDTO.getRegistroSanitario());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getTipoPersona()))
            	objGenInstituciones.setTipoPersona(objInputCrearProveedorDTO.getTipoPersona());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getEsContribuyenteEspecial()))
            	objGenInstituciones.setEsContribuyenteEspecial(objInputCrearProveedorDTO.getEsContribuyenteEspecial());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getEsGubernamental()))
            	objGenInstituciones.setEsGubernamental(objInputCrearProveedorDTO.getEsGubernamental());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getEsContribuyenterise()))
            	objGenInstituciones.setEsContribuyenterise(objInputCrearProveedorDTO.getEsContribuyenterise());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getLlevaContabilidad()))
            	objGenInstituciones.setLlevaContabilidad(objInputCrearProveedorDTO.getLlevaContabilidad());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getEsExtranjero()))
            	objGenInstituciones.setEsExtranjero(objInputCrearProveedorDTO.getEsExtranjero());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getEsProveedor()))
            	objGenInstituciones.setEsProveedor(objInputCrearProveedorDTO.getEsProveedor());
            objGenInstituciones.setUsuarioModificacion(objUsuarioLogin.getCodigoUsuario());
            objGenInstituciones.setFechaModificacion(new Date());
            
    		//validar numero cedular
    		if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getCodigoPaisTelMovil())) {
    			GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getCodigoPaisTelMovil(), "tasm.campos.codigoPaisTelefonico");
    			GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getTelefonoMovil(), "tasm.campos.numeroTelefonico");
    			Optional<GenPaises> objGenPaises = objGenPaisesDAO.find(objInputCrearProveedorDTO.getCodigoPaisTelMovil());
    			if (!objGenPaises.isPresent())
    				throw new BOException("tasm.warn.tasm.campos.codigoDePaisNoExiste");
    			Map<String, Object> MapvalidarTelefono = objIUtilBO.validarNumeroTelefonico(objInputCrearProveedorDTO.getTelefonoMovil(),
    					 objGenPaises.get().getCodigoISO(),
    					TipoNumero.MOBILE.getName());
    			if (MapvalidarTelefono.get("RESULTADO_VALIDACION")!=null && !Boolean.parseBoolean(MapvalidarTelefono.get("RESULTADO_VALIDACION").toString()))
    				throw new BOException("tasm.warn.numeroTelefonicoInvalido");
    		}
    		//validar numero telefono
    		if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getCodigoPaisTelFijo())) {
    			GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getCodigoPaisTelFijo(), "tasm.campos.codigoPaisConvencional");
    			GenericUtil.validarCampoRequeridoBO(objInputCrearProveedorDTO.getTelefonoFijo(), "tasm.campos.numeroConvencional");
    			Optional<GenPaises> objGenPaises = objGenPaisesDAO.find(objInputCrearProveedorDTO.getCodigoPaisTelFijo());
    			if (!objGenPaises.isPresent())
    				throw new BOException("tasm.warn.tasm.campos.codigoDePaisNoExiste");
    			Map<String, Object> MapvalidarTelefono = objIUtilBO.validarNumeroTelefonico(objInputCrearProveedorDTO.getTelefonoFijo(),
    					 objGenPaises.get().getCodigoISO(),
    					TipoNumero.FIXED_LINE.getName());
    			if (MapvalidarTelefono.get("RESULTADO_VALIDACION")!=null && !Boolean.parseBoolean(MapvalidarTelefono.get("RESULTADO_VALIDACION").toString()))
    				throw new BOException("tasm.warn.numeroTelefonicoInvalido");
    		}
            
            objGenInstitucionesDAO.update(objGenInstituciones);
            //PROVEEDORES
			GenProveedores objGenProveedores = optGenProveedores.get();
            if (objInputCrearProveedorDTO.getCodigoFormasPago()!= null)
            	objGenProveedores.setCodigoFormaPago(objInputCrearProveedorDTO.getCodigoFormasPago().longValue());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getPlazoOtorgado()))
            	objGenProveedores.setPlazoOtorgado(objInputCrearProveedorDTO.getPlazoOtorgado());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getPlazoOtorgado()))
            	objGenProveedores.setDireccionRetiro(objInputCrearProveedorDTO.getDireccionRetiro());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getNombreContactoRetiro()))
            	objGenProveedores.setNombreContactoRetiro(objInputCrearProveedorDTO.getNombreContactoRetiro());
            if (!ObjectUtils.isEmpty(objInputCrearProveedorDTO.getEstado()))
            	objGenProveedores.setEstado(objInputCrearProveedorDTO.getEstado());
            objGenProveedores.setUsuarioModificacion(objUsuarioLogin.getCodigoUsuario());
            objGenProveedores.setFechaModificacion(new Date());
        	objGenProveedoresDAO.update(objGenProveedores);
		}else {
            throw new BOException("tasm.warn.proveedorNoExiste");
        }
	}

	@Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = { BOException.class, CustomExceptionHandler.class, RestClientException.class })
	public void eliminarProveedor(Long codigoProveedor, UsuarioLogin objUsuarioLogin) throws BOException {
		Optional<GenProveedores> optGenProveedores = objGenProveedoresDAO.find(codigoProveedor);
		if (optGenProveedores.isPresent()) {
			GenProveedores objGenProveedores = optGenProveedores.get();
			objGenProveedores.setEstado("I");
			objGenProveedores.setUsuarioModificacion(objUsuarioLogin.getCodigoUsuario());
			objGenProveedores.setFechaModificacion(new Date());
        	objGenProveedoresDAO.update(objGenProveedores);
		}else {
            throw new BOException("tasm.warn.proveedorNoExiste");
        }
		
	}

	@Override
	public ProveedoresDTO obtenerUnProveedor(Long codigoProveedor, UsuarioLogin objUsuarioLogin) throws BOException {
		Optional<GenInstituciones> optGenInstituciones = objGenInstitucionesDAO.find(codigoProveedor);
		Optional<GenProveedores> optGenProveedores = objGenProveedoresDAO.find(codigoProveedor);
		if (optGenInstituciones.isPresent() && optGenProveedores.isPresent()) {
			GenInstituciones objGenInstituciones = optGenInstituciones.get();
			GenProveedores objGenProveedores = optGenProveedores.get();			
			//instituciones
			ProveedoresDTO objProveedoresDTO = ProveedoresDTO.builder()
					.codigoInstitucion(objGenInstituciones.getCodigoInstitucion())
					.codigoEmpresa(objGenInstituciones.getCodigoEmpresa())
					.codigoDetalleActividad(objGenInstituciones.getCodigoDetalleActividad())
					.codigoTipoIdentificacion(objGenInstituciones.getCodigoTipoIdentificacion())
					.numeroIdentificacion(objGenInstituciones.getNumeroIdentificacion())
					.nombreInstitucion(objGenInstituciones.getNombreInstitucion())
					.nombreComercial(objGenInstituciones.getNombreComercial())
					.codigoPaisTelFijo(objGenInstituciones.getCodigoPaisTelFijo())
					.codigoPaisTelMovil(objGenInstituciones.getCodigoPaisTelMovil())
					.codigoPaisTelMovilContacto(objGenInstituciones.getCodigoPaisTelMovilContacto())
					.direccion(objGenInstituciones.getDireccion())
					.paginaWeb(objGenInstituciones.getPaginaWeb())
					.correoElectronico(objGenInstituciones.getCorreoElectronico())
					.telefonoMovil(objGenInstituciones.getTelefonoMovil())
					.telefonoMovilE164(objGenInstituciones.getTelefonoMovilE164())
					.telefonoFijo(objGenInstituciones.getTelefonoFijo())
					.telefonoFijoE164(objGenInstituciones.getTelefonoFijoE164())
					.nombrePersonaContacto(objGenInstituciones.getNombrePersonaContacto())
					.telefonoMovilContacto(objGenInstituciones.getTelefonoMovilContacto())
					.telefonoMovilContactoE164(objGenInstituciones.getTelefonoMovilContactoE164())
					.nombreRepresentanteLegal(objGenInstituciones.getNombreRepresentanteLegal())
					.nombrePresidente(objGenInstituciones.getNombrePresidente())
					.nombreGerente(objGenInstituciones.getNombreGerente())
					.registroSanitario(objGenInstituciones.getRegistroSanitario())
					.tipoPersona(objGenInstituciones.getTipoPersona())
					.esContribuyenteEspecial(objGenInstituciones.getEsContribuyenteEspecial())
					.esGubernamental(objGenInstituciones.getEsGubernamental())
					.esContribuyenterise(objGenInstituciones.getEsContribuyenterise())
					.llevaContabilidad(objGenInstituciones.getLlevaContabilidad())
					.esExtranjero(objGenInstituciones.getEsExtranjero())
					.esProveedor(objGenInstituciones.getEsProveedor())
					.fechaIngreso(objGenInstituciones.getFechaIngreso().toString())
					.build();
			//proveedores
			objProveedoresDTO.setCodigoFormasPago(objGenProveedores.getCodigoFormaPago()!=null?objGenProveedores.getCodigoFormaPago().intValue():null);
			objProveedoresDTO.setPlazoOtorgado(objGenProveedores.getPlazoOtorgado());
			objProveedoresDTO.setDireccionRetiro(objGenProveedores.getDireccionRetiro());
			objProveedoresDTO.setNombreContactoRetiro(objGenProveedores.getNombreContactoRetiro());
			objProveedoresDTO.setEstado(objGenProveedores.getEstado());
			//formas de pago
			if (!ObjectUtils.isEmpty(objGenProveedores.getCodigoFormaPago())) {
				Optional<GenFormasPago> optGenFormasPago = objGenFormasPagoDAO.find(objGenProveedores.getCodigoFormaPago());
				if (optGenInstituciones.isPresent())
					objProveedoresDTO.setNombreFormaPago(optGenFormasPago.get().getNombreFormaPago());
			}
			//detalle de actividad
			if (!ObjectUtils.isEmpty(objGenInstituciones.getCodigoDetalleActividad())) {
				Optional<GenDetallesActividad> optGenDetallesActividad = objGenDetallesActividadDAO.find(objGenInstituciones.getCodigoDetalleActividad()!=null?objGenInstituciones.getCodigoDetalleActividad().longValue():null);
				if (optGenDetallesActividad.isPresent()) {
					objProveedoresDTO.setNombreDetalleActividad(optGenDetallesActividad.get().getNombreDetalleActividad());
					//tipo de actividad
					Optional<GenTiposActividad> optGenTiposActividad = objGenTiposActividadDAO.find(optGenDetallesActividad.get().getCodigoTipoActividad()!=null?optGenDetallesActividad.get().getCodigoTipoActividad().longValue():null);
					if (optGenTiposActividad.isPresent()) {
						objProveedoresDTO.setCodigoTipoActividad(optGenTiposActividad.get().getCodigoTipoActividad().intValue());
						objProveedoresDTO.setNombreTipoActividad(optGenTiposActividad.get().getNombreTipoActividad());
					}
				}
			}
			//tipo de indentificacion
			if (!ObjectUtils.isEmpty(objGenInstituciones.getCodigoTipoIdentificacion())) {
				Optional<GenTiposIdentificacion> optGenTiposIdentificacion = objGenTiposIdentificacionDAO.find(new GenTiposIdentificacionCPK(objGenInstituciones.getCodigoTipoIdentificacion().shortValue(), objGenInstituciones.getCodigoEmpresa().shortValue()));
				if (optGenTiposIdentificacion.isPresent())
					objProveedoresDTO.setNombreTipoIdentificacion(optGenTiposIdentificacion.get().getNombreTipoIdentificacion());
			}
        	return objProveedoresDTO;
        	
        }else {
            throw new BOException("tasm.warn.proveedorNoExiste");
        }
	}

}
