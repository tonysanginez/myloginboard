package com.tasm.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.tasm.dto.ClienteDTO;
import com.tasm.model.com.ComClientes;

@Service
public class MgiDetallesMovimientoInventarioDAO extends BaseDAO<ComClientes,Long>{

	@PersistenceContext
	private EntityManager em;
	
	protected MgiDetallesMovimientoInventarioDAO() {
		super(ComClientes.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
    public void persist(ComClientes t) throws PersistenceException {
        super.persist(t);
    }
	
	 public Long nextCodigoCliente() {
	        try {
	            StringBuilder stbQuery = new StringBuilder();
	            stbQuery.append("SELECT max(c.codigoCliente) FROM ComClientes c");
	            Query query = em.createQuery(stbQuery.toString());
	            Long codigoCliente = (Long) query.getSingleResult();
	            return codigoCliente + 1;
	        } catch (NoResultException e) {
	            return Long.MAX_VALUE;
	        }
	    }
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> obtenerClientes(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
											  String numeroIdentificacion,
											  String codigoCliente, 
											  String razonSocial, 
											  String nombreComercial, 
											  String telefonoMovil,
											  String direccion,
											  String correoElectronico,
											  String nombreCliente,
											  String estado
											  ){
		Map<String, Object> resultado = new HashMap<>();
        StringBuilder strQuery = new StringBuilder();
        try {
            strQuery.append(" SELECT  c.codigo_cliente as codigoCliente, ");
            strQuery.append("         c.numero_identificacion as numeroIdentificacion, ");
            strQuery.append("         c.razon_social as razonSocial, ");
            strQuery.append("         c.nombre_comercial as nombreComercial, ");
            strQuery.append("         c.telefono_movil as telefonoMovil, ");
            strQuery.append("         c.direccion as direccion, ");
            strQuery.append("         c.correo_electronico as correoElectronico, ");
            strQuery.append("         c.nombre_cliente as nombreCliente, ");
            strQuery.append("         c.estado as estado, ");
            strQuery.append("         c.fecha_ingreso as fechaIngreso, ");
            strQuery.append("         tide.nombre_tipo_identificacion as tipoIdentificacion, ");
            strQuery.append("         tide.codigo_tipo_identificacion as codigoTipoIdentificacion ");
            strQuery.append(" FROM    com_clientes c, ");
            strQuery.append("         gen_tipos_identificacion tide ");
            strQuery.append(" WHERE   c.codigo_empresa = :codigoEmpresa ");
            strQuery.append(" AND     c.codigo_tipo_identificacion = tide.codigo_tipo_identificacion ");
            
            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                strQuery.append(" AND     (c.numero_identificacion LIKE :filtroGeneral ");
                strQuery.append(" OR      c.razon_social LIKE :filtroGeneral ");
                strQuery.append(" OR      c.nombre_comercial LIKE :filtroGeneral ");
                strQuery.append(" OR      c.telefono_movil LIKE :filtroGeneral ");
                strQuery.append(" OR      c.direccion LIKE :filtroGeneral ");
                strQuery.append(" OR      c.correo_electronico LIKE :filtroGeneral ");
                strQuery.append(" OR      c.nombre_cliente LIKE :filtroGeneral) ");
            }
            if (!ObjectUtils.isEmpty(codigoCliente)) {
                strQuery.append(" AND     c.codigo_producto = :codigoCliente ");
            }
            if (!ObjectUtils.isEmpty(numeroIdentificacion)) {
                strQuery.append(" AND     c.numero_identificacion LIKE :numeroIdentificacion ");
            }
            if (!ObjectUtils.isEmpty(razonSocial)) {
                strQuery.append(" AND     c.razon_social LIKE :razonSocial ");
            }
            if (!ObjectUtils.isEmpty(nombreComercial)) {
                strQuery.append(" AND     c.nombre_comercial = :nombreComercial ");
            }
            if (!ObjectUtils.isEmpty(telefonoMovil)) {
                strQuery.append(" AND     c.telefono_movil = :telefonoMovil ");
            }
            if (!ObjectUtils.isEmpty(direccion)) {
                strQuery.append(" AND     c.direccion = :direccion ");
            }
            if (!ObjectUtils.isEmpty(correoElectronico)) {
                strQuery.append(" AND     c.correo_electronico = :correoElectronico ");
            }
            if (!ObjectUtils.isEmpty(nombreCliente)) {
                strQuery.append(" AND     c.nombre_cliente = :nombreCliente ");
            }
            if (!ObjectUtils.isEmpty(estado)) {
                strQuery.append(" AND     c.estado LIKE :estado ");
            }

            TypedQuery<Tuple> query = (TypedQuery<Tuple>) em.createNativeQuery(strQuery.toString(), Tuple.class);            
            query.setParameter("codigoEmpresa", codigoEmpresa);

           
            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                query.setParameter("filtroGeneral", "%" + filtroGeneral + "%");
            }
            if (!ObjectUtils.isEmpty(codigoCliente)) {
                query.setParameter("codigoCliente", codigoCliente);
            }
            if (!ObjectUtils.isEmpty(razonSocial)) {
                query.setParameter("razonSocial", "%" + razonSocial + "%");
            }
            if (!ObjectUtils.isEmpty(nombreComercial)) {
                query.setParameter("nombreComercial", "%" + nombreComercial + "%");
            }
            if (!ObjectUtils.isEmpty(telefonoMovil)) {
                query.setParameter("telefonoMovil", telefonoMovil);
            }
            if (!ObjectUtils.isEmpty(direccion)) {
                query.setParameter("direccion", direccion);
            }
            if (!ObjectUtils.isEmpty(correoElectronico)) {
                query.setParameter("correoElectronico", correoElectronico);
            }
            if (!ObjectUtils.isEmpty(nombreCliente)) {
                query.setParameter("nombreCliente", nombreCliente);
            }
            
            Integer totalClientes = query.getResultList().size();
            
            query.setFirstResult((page * perPage) - perPage);
            query.setMaxResults(perPage);
            List<Tuple> lsResult = query.getResultList();
            List<ClienteDTO> lsClientes = new ArrayList<>();
            if (lsResult != null) {
            	ClienteDTO objCliente = null;
                for (Tuple tuple : lsResult){
                	objCliente = new ClienteDTO();
                	objCliente.setCodigoCliente(tuple.get("codigoCliente") != null ? tuple.get("codigoCliente", Number.class).longValue() : null);
                	objCliente.setNumeroIdentificacion(tuple.get("numeroIdentificacion", String.class));
                	objCliente.setRazonSocial(tuple.get("razonSocial") != null ? tuple.get("razonSocial", String.class) : null);
                	objCliente.setNombreComercial(tuple.get("nombreComercial", String.class));
                	objCliente.setTelefonoMovil(tuple.get("telefonoMovil", String.class));
                	objCliente.setDireccion(tuple.get("direccion", String.class));
                	objCliente.setCorreoElectronico(tuple.get("correoElectronico", String.class));
                	objCliente.setNombreCliente(tuple.get("nombreCliente", String.class));
                	objCliente.setTipoIdentificacion(tuple.get("tipoIdentificacion", String.class));
                	objCliente.setCodigoTipoIdentificacion(tuple.get("codigoTipoIdentificacion", Number.class).intValue());
                    objCliente.setEstado(tuple.get("estado", String.class));
                    objCliente.setFechaIngreso(tuple.get("fechaIngreso", Date.class).toString());
                    
                    lsClientes.add(objCliente);
                }
            }
            resultado.put("clientes", lsClientes);
            resultado.put("totalItems", totalClientes);
            resultado.put("itemsPerPage", perPage);
            resultado.put("totalPages", (int) Math.ceil(totalClientes.doubleValue() / perPage.doubleValue()));
            return resultado;
        } catch (NoResultException e) {
            return null;
        }
	}
}
