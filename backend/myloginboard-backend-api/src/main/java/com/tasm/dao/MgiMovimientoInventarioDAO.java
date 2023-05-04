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
import com.tasm.model.mgi.MgiMovimientoInventario;

@Service
public class MgiMovimientoInventarioDAO extends BaseDAO<MgiMovimientoInventario,Long>{

	@PersistenceContext
	private EntityManager em;
	
	protected MgiMovimientoInventarioDAO() {
		super(MgiMovimientoInventario.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
    public void persist(MgiMovimientoInventario t) throws PersistenceException {
        super.persist(t);
    }
	
	 public Long nextCodigoMovimientoInventario() {
	        try {
	            StringBuilder stbQuery = new StringBuilder();
	            stbQuery.append("SELECT max(c.codigoMovimientoInventario) FROM MgiMovimientoInventario c");
	            Query query = em.createQuery(stbQuery.toString());
	            Long codigoMovimientoInventario = (Long) query.getSingleResult();
	            return codigoMovimientoInventario + 1;
	        } catch (NoResultException e) {
	            return Long.MAX_VALUE;
	        }
	    }
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> obtenerMovimientoInventario(Short codigoEmpresa, Integer page, Integer perPage,
			String filtroGeneral, String codigoMovimientoInventario, String codigoTipoDocumento, String descripcion,
			String codigoInstitucion, String referenciaComprobante, String estado){
		Map<String, Object> resultado = new HashMap<>();
        StringBuilder strQuery = new StringBuilder();
        try {
            strQuery.append(" SELECT  c.codigo_movimiento_inventario as codigoMovimientoInventario, ");
            strQuery.append("         c.descripcion as descripcion, ");
            strQuery.append("         pro.codigo_institucion as codigoInstitucion, ");
            strQuery.append("         pro. as nombreInstitucion, ");
            strQuery.append("         c.referencia_comprobante as referenciaComprobante, ");
            strQuery.append("         c.estado as estado, ");
            strQuery.append("         c.fecha_ingreso as fechaIngreso, ");
            strQuery.append("         tipdoc.descripcion as descripcionTipoDocumento, ");
            strQuery.append("         tipdoc.codigo_tipo_documento as codigoTipoDocumento ");
            strQuery.append(" FROM    mgi_movimiento_inventario c INNER JOIN ");
            strQuery.append("         mgi_tipo_documento tipdoc ON(c.codigo_tipo_documento=tipdoc.codigo_tipo_documento and c.codigo_empresa=tipdoc.codigo_empresa)");
            strQuery.append("         LEFT JOIN gen_instituciones pro ON(c.codigo_institucion=pro.codigo_institucion and c.codigo_empresa=pro.codigo_empresa)");
            strQuery.append(" WHERE   c.codigo_empresa = :codigoEmpresa ");
            
            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                strQuery.append(" AND     (c.descripcion LIKE :filtroGeneral ");
                strQuery.append(" OR      c.referencia_comprobante LIKE :filtroGeneral) ");
            }
            if (!ObjectUtils.isEmpty(codigoMovimientoInventario)) {
                strQuery.append(" AND     c.codigo_movimiento_inventario = :codigoMovimientoInventario ");
            }
            if (!ObjectUtils.isEmpty(codigoTipoDocumento)) {
                strQuery.append(" AND     c.codigo_tipo_documento LIKE :codigoTipoDocumento ");
            }
            if (!ObjectUtils.isEmpty(descripcion)) {
                strQuery.append(" AND     c.descripcion LIKE :descripcion ");
            }
            if (!ObjectUtils.isEmpty(codigoInstitucion)) {
                strQuery.append(" AND     c.codigo_institucion = :codigoInstitucion ");
            }
            if (!ObjectUtils.isEmpty(referenciaComprobante)) {
                strQuery.append(" AND     c.referencia_comprobante = :referenciaComprobante ");
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
            if (!ObjectUtils.isEmpty(codigoMovimientoInventario)) {
                query.setParameter("codigoMovimientoInventario", codigoMovimientoInventario);
            }
            if (!ObjectUtils.isEmpty(codigoTipoDocumento)) {
                query.setParameter("codigoTipoDocumento", codigoTipoDocumento);
            }
            if (!ObjectUtils.isEmpty(descripcion)) {
                query.setParameter("descripcion", "%" + descripcion + "%");
            }
            if (!ObjectUtils.isEmpty(codigoInstitucion)) {
                query.setParameter("codigoInstitucion", codigoInstitucion);
            }
            if (!ObjectUtils.isEmpty(referenciaComprobante)) {
                query.setParameter("referenciaComprobante", "%" + referenciaComprobante + "%");
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
