package com.tasm.api;

import java.io.IOException;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tasm.bo.IProductoBO;
import com.tasm.db.config.DBContextHolder;
import com.tasm.dto.InputMarcaDTO;
import com.tasm.dto.InputProductoDTO;
import com.tasm.dto.InputTiposProductosDTO;
import com.tasm.dto.InputUnidadesMedidaDTO;
import com.tasm.dto.response.ResponseOk;
import com.tasm.enums.DBTypeEnum;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.util.MensajesUtil;

import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/v1/productos")
@CrossOrigin
public class ProductosApi {

    private static final Logger logger = LogManager.getLogger(AutenticacionApi.class.getName());
    
    @Autowired
    private IProductoBO objIProductoBO;
    
    @RequestMapping(value = "", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> obtenerProductos(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @RequestParam(value = "codigoEmpresa", defaultValue = "1")  Short codigoEmpresa,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "filtroGeneral", required = false) String filtroGeneral,
            @RequestParam(value = "perPage", defaultValue = "10", required = false) Integer perPage,
            @RequestParam(value = "codigoProducto", required = false) String codigoProducto,
            @RequestParam(value = "nombreProducto", required = false) String nombreProducto,
            @RequestParam(value = "descripcion", required = false) String descripcion,
            @RequestParam(value = "codigoTipoProducto", required = false) Integer codigoTipoProducto,
            @RequestParam(value = "codigoTipoPresentacion", required = false) Integer codigoTipoPresentacion,
            @RequestParam(value = "codigoMarca", required = false) Integer codigoMarca,
            @RequestParam(value = "codigoUnidadMedida", required = false) Integer codigoUnidadMedida,
            @RequestParam(value = "codigoBarra", required = false) String codigoBarra,
            @RequestParam(value = "aplicaStock", required = false) String aplicaStock,
            //@RequestParam(value = "estado", defaultValue = "A", required = false) String estado, //Tony Sanginez 20/09/2022 se modifico porque en el listado solo traia los Activos 
            @RequestParam(value = "estado",  required = false) String estado,
            @RequestParam(value = "aplicaIva", required = false) String aplicaIva,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "order", required = false) String order) {
        try {
            Map<String, Object> data = objIProductoBO.obtenerProductos(codigoEmpresa, page, perPage, filtroGeneral, codigoProducto, nombreProducto,
                descripcion, codigoTipoProducto, codigoTipoPresentacion, codigoMarca, codigoUnidadMedida, estado, aplicaIva, aplicaStock, codigoBarra);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    data), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearProducto(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @RequestBody InputProductoDTO objProductoDTO) {
        try {
            objIProductoBO.crearProducto(objProductoDTO);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/{codigoProducto}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> editarProducto(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @PathVariable("codigoProducto") Long codigoProducto,
            @RequestBody InputProductoDTO objProductoDTO) {
        try {
            objIProductoBO.editarProducto(codigoProducto,objProductoDTO);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/{codigoProducto}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> eliminarProducto(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @PathVariable("codigoProducto") Long codigoProducto) {
        try {
            objIProductoBO.eliminarProducto(codigoProducto);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    
    @RequestMapping(value = "/tipos",method = RequestMethod.GET)  
  	public ResponseEntity<?> consultarTiposProductos(
  			@RequestHeader(value = "Accept-Language", required = false) String strLanguage,  
            @RequestParam(value = "codigoEmpresa", defaultValue = "1")  Short codigoEmpresa,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "perPage", defaultValue = "10", required = false) Integer perPage,
            @RequestParam(value = "filtroGeneral", required = false) String filtroGeneral,
            @RequestParam(value = "nombreTipoProducto", required = false) String nombreProducto,
            @RequestParam(value = "codigoTipoProducto", required = false) Long codigoTipoProducto, 
            //@RequestParam(value = "estado", defaultValue = "A", required = false) String estado,//Tony Sanginez 20/09/2022 se modifico porque en el listado solo traia los Activos
            @RequestParam(value = "estado",  required = false) String estado,
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "order", required = false) String order)
  	throws   BOException, JsonProcessingException, IOException, RestClientException{
      	 
  		DBContextHolder.setCurrentDb(DBTypeEnum.DB_TRX_TASM); 
  		try { 
    		  Map<String, Object> data = objIProductoBO.obtenerTiposProductos(codigoEmpresa, page, perPage, filtroGeneral, nombreProducto, codigoTipoProducto, estado);
          return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                  data), HttpStatus.OK);
      } catch (BOException e) {
          logger.error(e);
          throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
      }
  }
    
    @RequestMapping(value = "/tipos", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearTiposProductos(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @RequestBody InputTiposProductosDTO objTipoProductoDTO) {
        try {
            objIProductoBO.crearTiposProducto(objTipoProductoDTO);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/tipos/{codigoTipoProducto}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> editarTipoProducto(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @PathVariable("codigoTipoProducto") Long codigoTipoProducto,
            @RequestBody InputTiposProductosDTO objTipoProductoDTO) {
        try {
        	objIProductoBO.editarTiposProducto(codigoTipoProducto, objTipoProductoDTO);
        	return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/tipos/{codigoTipoProducto}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> eliminarTipoProducto(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @PathVariable("codigoTipoProducto") Long codigoTipoProducto) {
        try {
        	objIProductoBO.eliminarTiposProducto(codigoTipoProducto);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }    
    
    @RequestMapping(value = "/unidades",method = RequestMethod.GET)  
  	public ResponseEntity<?> consultarUnidades(
  			@RequestHeader(value = "Accept-Language", required = false) String strLanguage,  
            @RequestParam(value = "codigoEmpresa", defaultValue = "1")  Short codigoEmpresa,
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "perPage", defaultValue = "10", required = false) Integer perPage,
            @RequestParam(value = "filtroGeneral", required = false) String filtroGeneral,
            @RequestParam(value = "nombreUnidadMedida", required = false) String nombreUnidadMedida,
            @RequestParam(value = "codigoUnidadMedida", required = false) Integer codigoUnidadMedida, 
            @RequestParam(value = "abreviatura", required = false) String abreviatura,
            //@RequestParam(value = "estado", defaultValue = "A", required = false) String estado, //Tony Sanginez 20/09/2022 se modifico porque en el listado solo traia los Activos
            @RequestParam(value = "estado",  required = false) String estado, 
            @RequestParam(value = "sortBy", required = false) String sortBy,
            @RequestParam(value = "order", required = false) String order)
  	throws   BOException, JsonProcessingException, IOException, RestClientException{
      	 
  		DBContextHolder.setCurrentDb(DBTypeEnum.DB_TRX_TASM); 
  		try { 
    		  Map<String, Object> data = objIProductoBO.obtenerUnidadesMedida(codigoEmpresa, page, perPage, filtroGeneral, nombreUnidadMedida, codigoUnidadMedida, abreviatura, estado);
          return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                  data), HttpStatus.OK);
      } catch (BOException e) {
          logger.error(e);
          throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
      }
  }
    
    @RequestMapping(value = "/unidades", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearUnidades(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @RequestBody InputUnidadesMedidaDTO objInputUnidadesMedidaDTO) {
        try {
            objIProductoBO.crearUnidadesMedida(objInputUnidadesMedidaDTO);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/unidades/{codigoUnidadMedida}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> editarUnidades(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @PathVariable("codigoUnidadMedida") Integer codigoUnidadMedida,
            @RequestBody InputUnidadesMedidaDTO objInputUnidadesMedidaDTO) {
        try {
        	objIProductoBO.editarUnidadesMedida(codigoUnidadMedida, objInputUnidadesMedidaDTO);
        	return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
    
    @RequestMapping(value = "/unidades/{codigoUnidadMedida}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> eliminarUnidades(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @PathVariable("codigoUnidadMedida") Integer codigoUnidadMedida) {
        try {
        	objIProductoBO.eliminarUnidadesMedida(codigoUnidadMedida);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }    
}
