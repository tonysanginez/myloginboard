package com.tasm.api;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasm.bo.IClienteBO;
import com.tasm.dto.InputClienteDTO;
import com.tasm.dto.response.ResponseOk;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.util.MensajesUtil;

import org.apache.logging.log4j.Logger;
@RestController
@RequestMapping("/v1/inventarios")
@CrossOrigin
public class InventariosApi {

	private static final Logger logger = LogManager.getLogger(AutenticacionApi.class.getName());
	
	@Autowired
	private IClienteBO objIClientesBO;
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> obtenerClientes(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @RequestParam(value = "codigoEmpresa", defaultValue = "1")  Short codigoEmpresa,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "filtroGeneral", required = false) String filtroGeneral,
            @RequestParam(value = "perPage", defaultValue = "10", required = false) Integer perPage,
            @RequestParam(value = "codigoCliente", required = false) String codigoCliente,
            @RequestParam(value = "numeroIdentificacion", required = false) String numeroIdentificacion,
            @RequestParam(value = "razonSocial", required = false) String razonSocial,
            @RequestParam(value = "nombreComercial", required = false) String nombreComercial,
            @RequestParam(value = "telefonoMovil", required = false) String telefonoMovil,
            @RequestParam(value = "direccion", required = false) String direccion,
            @RequestParam(value = "correoElectronico", required = false) String correoElectronico,
            @RequestParam(value = "nombreCliente", required = false) String nombreCliente,
            @RequestParam(value = "codigoTipoIdentificacion", required = false) Integer codigoTipoIdentificacion,
            //@RequestParam(value = "estado", defaultValue = "A", required = false) String estado, //tony sanginez no cargan los inactivos
            @RequestParam(value = "estado", required = false) String estado,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "order", required = false) String order) {
        try {
            Map<String, Object> data = objIClientesBO.obtenerClientes(codigoEmpresa, page, perPage, filtroGeneral, numeroIdentificacion, codigoCliente, razonSocial, nombreComercial, telefonoMovil, direccion, correoElectronico, nombreCliente, estado);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    data), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearCliente(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @RequestBody InputClienteDTO objClienteDTO) {
        try {
        	objIClientesBO.crearCliente(objClienteDTO);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/{codigoCliente}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> editarProducto(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @PathVariable("codigoCliente") Long codigoCliente,
            @RequestBody InputClienteDTO objClienteDTO) {
        try {
        	objIClientesBO.editarCliente(codigoCliente, objClienteDTO);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/{codigoCliente}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> eliminarProducto(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @PathVariable("codigoCliente") Long codigoCliente) {
        try {
        	objIClientesBO.eliminarCliente(codigoCliente);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
}
