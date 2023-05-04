package com.tasm.bo;

import java.util.Map;

import com.tasm.dto.InputClienteDTO;
import com.tasm.exceptions.BOException;

public interface IClienteBO {
	/**
     * Obtener todos los clientes.
     *
     * @param codigoEmpresa
     * @param page
     * @param perPage
     * @param tipoFiltro
     * @param valorFiltro
     * @param estado
     * @throws BOException
     */
    public Map<String, Object> obtenerClientes(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
			  String numeroIdentificacion,
			  String codigoCliente, 
			  String razonSocial, 
			  String nombreComercial, 
			  String telefono,
			  String direccion,
			  String correoElectronico,
			  String nombreCliente,
			  String estado
			  ) throws BOException;

    /**
     * Crear un cliente.
     * @param objClienteDTO
     * @throws BOException
     */
    public void crearCliente(InputClienteDTO objClienteDTO) throws BOException;
    
    /**
     * Editar un cliente.
     * @param codigoCliente
     * @param objClienteDTO
     * @throws BOException
     */
    public void editarCliente(Long codigoCliente, InputClienteDTO objClienteDTO) throws BOException;
    
    /**
     * Eliminar un cliente.
     * @param codigoCliente
     * @throws BOException
     */
    public void eliminarCliente(Long codigoCliente) throws BOException;
    
}
