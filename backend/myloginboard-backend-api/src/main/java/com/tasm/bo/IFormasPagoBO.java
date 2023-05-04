package com.tasm.bo;

import java.util.Map;

import com.tasm.dto.FormasPagoDTO;
import com.tasm.dto.InputCrearFormasPagoDTO;
import com.tasm.exceptions.BOException;
import com.tasm.security.UsuarioLogin;

public interface IFormasPagoBO {

    /**
     * Obtener todas las formas de pago.
     *
     * @param codigoEmpresa
     * @param page
     * @param perPage
     * @param filtroGeneral
     * @param codigoFormaPago
     * @param nemonico
     * @param nombre_forma_pago
     * @param codigo_sri
     * @param estado
     * @throws BOException
     */
	public Map<String, Object> obtenerFormasPago(Short codigoEmpresa,
			Integer page,
			Integer perPage,
			String filtroGeneral,
			Integer codigoFormaPago,
			String nemonico,
			String nombre_forma_pago,
			String codigo_sri,
			String estado) throws BOException;
	
	/**
     * Crear una formas de pago.
     *
     * @param objInputFormasPagoDTO
     * @param objUsuarioLogin
     * @throws BOException
     */
    public void crearFormasPago(InputCrearFormasPagoDTO objInputFormasPagoDTO,UsuarioLogin objUsuarioLogin) throws BOException;

    
    /**
     * Editar una forma de pago.
     * @param codigoFormasPago
     * @param objInputFormasPagoDTO
     * @param objUsuarioLogin 
     * @throws BOException
     */
	public void editarFormasPago(Long codigoFormasPago, InputCrearFormasPagoDTO objInputFormasPagoDTO, UsuarioLogin objUsuarioLogin) throws BOException;

    /**
     * elimina una forma de pago.
     * @param codigoFormasPago
     * @param objUsuarioLogin 
     * @throws BOException
     */
	public void eliminarFormasPago(Long codigoFormasPago, UsuarioLogin objUsuarioLogin)throws BOException;
	
	
    /**
     * obtiene una forma de pago.
     * @param codigoFormasPago
     * @param objUsuarioLogin 
     * @throws BOException
     */

	public FormasPagoDTO obtenerUnaFormasPagos(Long codigoFormasPago, UsuarioLogin objUsuarioLogin)throws BOException;



	
}
