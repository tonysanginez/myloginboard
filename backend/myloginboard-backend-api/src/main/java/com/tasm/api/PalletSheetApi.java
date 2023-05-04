package com.tasm.api;

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
import org.springframework.web.bind.annotation.RestController;

import com.tasm.bo.IProductoBO;
import com.tasm.dto.InputPalletSheetDTO;
import com.tasm.dto.response.ResponseOk;
import com.tasm.exceptions.BOException;
import com.tasm.exceptions.CustomExceptionHandler;
import com.tasm.util.MensajesUtil;

@RestController
@RequestMapping("/v1/palletSheet")
@CrossOrigin
public class PalletSheetApi {

    private static final Logger logger = LogManager.getLogger(AutenticacionApi.class.getName());
    
    @Autowired
    private IProductoBO objIProductoBO;
      
    
    @RequestMapping(value = "", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> crearUnidades(
            @RequestHeader(value = "Accept-Language", required = false) String strLanguage,
            @RequestHeader(value = HttpHeaders.AUTHORIZATION, required = false) String strAuthorization,
            @RequestBody InputPalletSheetDTO objDto) {
        try {
            objIProductoBO.crearUnidadesMedida(null);
            return new ResponseEntity<>(new ResponseOk(MensajesUtil.getMensaje("tasm.response.ok", MensajesUtil.validateSupportedLocale(strLanguage)),
                    null), HttpStatus.OK);
        } catch (BOException e) {
            logger.error(e);
            throw new CustomExceptionHandler(e.getTranslatedMessage(strLanguage), e.getData());
        }
    }
}
