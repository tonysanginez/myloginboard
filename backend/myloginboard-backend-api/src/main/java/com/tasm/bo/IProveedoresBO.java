package com.tasm.bo;

import java.util.Map;

import com.tasm.dto.InputCrearProveedorDTO;
import com.tasm.dto.ProveedoresDTO;
import com.tasm.exceptions.BOException;
import com.tasm.security.UsuarioLogin;


public interface IProveedoresBO {

    /**
     * Obtener todos los  proveedores.
     *
     * @param numeroIdentificacion,
     * @param nombreInstitucion,
     * @param nombreComercial,
     * @param direccion,
     * @param correoElectronico,
     * @param telefonoMovil,
     * @param nombrePersonaContacto,
     * @param codigoEmpresa
     * @param page
     * @param perPage
     * @param filtroGeneral
     * @param estado
     * @param aplicaIva
     * @throws BOException
     */
	public Map<String, Object> obtenerProveedores(Long codigoInstitucion,String numeroIdentificacion,
			String nombreInstitucion, String nombreComercial, String direccion, String correoElectronico,
			String telefonoMovil, String nombrePersonaContacto, Short codigoEmpresa, Integer page, Integer perPage,
			String filtroGeneral, String estado) throws BOException;

    /**
     * Crear un proveedor.
     * @param InputCrearProveedorDTO
     * @param UsuarioLogin
     * @throws BOException
     */
	public void crearProveedor(InputCrearProveedorDTO objInputCrearProveedorDTO, UsuarioLogin objUsuarioLogin)throws BOException;

    /**
     * Editar un proveedor.
     * @param codigoProveedor
     * @param InputCrearProveedorDTO
     * @param objUsuarioLogin 
     * @throws BOException
     */
	public void editarProveedor(Long codigoProveedor, InputCrearProveedorDTO objInputCrearProveedorDTO,
			UsuarioLogin objUsuarioLogin)throws BOException;

    /**
     * elimnar un proveedor.
     * @param codigoProveedor
     * @param objUsuarioLogin 
     * @throws BOException
     */
	public void eliminarProveedor(Long codigoProveedor, UsuarioLogin objUsuarioLogin)throws BOException;

    /**
     * obtiene un proveedor.
     * @param codigoProveedor
     * @param objUsuarioLogin 
     * @throws BOException
     */
	public ProveedoresDTO obtenerUnProveedor(Long codigoProveedor, UsuarioLogin objUsuarioLogin)throws BOException;

}
