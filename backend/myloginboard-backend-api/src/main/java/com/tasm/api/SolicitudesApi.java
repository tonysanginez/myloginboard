package com.tasm.api;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tasm.bo.ISolicitudesBO;
import com.tasm.dto.response.ResponseOk;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.util.MensajesUtil;



@RestController
@RequestMapping("/v1/solicitudes")
@CrossOrigin
public class SolicitudesApi {
	private static final Logger logger = LogManager.getLogger(AutenticacionApi.class.getName());
	@Autowired
	private ISolicitudesBO objISolicitudesBO;
	
	@RequestMapping(value = "/cab", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> obtenerSolicitudPedido(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @RequestParam(value = "codigoEmpresa", defaultValue = "1")  Short codigoEmpresa,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "perPage", defaultValue = "10", required = false) Integer perPage,
            @RequestParam(value = "filtroGeneral", required = false) String filtroGeneral,
            @RequestParam(value = "codigoCliente", required = false) Integer codigoCliente,
            @RequestParam(value = "estadoSolicitud", required = false) String estadoSolicitud,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "order", required = false) String order) {
        try {
            Map<String, Object> data = objISolicitudesBO.obtenerSolicitudPedido(codigoEmpresa, page, perPage, filtroGeneral,  
            		codigoCliente,  estadoSolicitud);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    data), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
	@RequestMapping(value = "/det", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> obtenerDetalleSolicitudPedido(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "perPage", defaultValue = "10", required = false) Integer perPage,
            @RequestParam(value = "filtroGeneral", required = false) String filtroGeneral,
            @RequestParam(value = "codigoSolicitud", required = false) Integer codigoSolicitud,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "order", required = false) String order) {
        try {
            Map<String, Object> data = objISolicitudesBO.obtenerDetalleSolicitudPedido(page, perPage, filtroGeneral, 
                     codigoSolicitud);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    data), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
}
