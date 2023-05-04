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

import com.tasm.dto.MarcaDTO;
import com.tasm.dto.SolicitudPedidoDTO;
import com.tasm.model.mgi.MgiMarcas;
import com.tasm.model.mgi.MgiSolicitudPedido;

@Service
public class MgiSolicitudPedidoDAO extends BaseDAO<MgiSolicitudPedido, Integer> {
	 @PersistenceContext
	 private EntityManager em;
	 
	 protected MgiSolicitudPedidoDAO() {
	        super(MgiSolicitudPedido.class);
	    }

	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }

	    @Override
	    public void persist(MgiSolicitudPedido t) throws PersistenceException {
	        super.persist(t);
	    }
	    
	    @SuppressWarnings("unchecked")
	    public Map<String, Object> obtenerSolicitudPedido(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
	             Integer codigoCliente, String estadoSolicitud) {
	        Map<String, Object> resultado = new HashMap<>();
	        StringBuilder strQuery = new StringBuilder();
	        try {
	            strQuery.append(" SELECT     s.codigo_solicitud, ");
	            strQuery.append("         s.codigo_empresa, ");
	            strQuery.append("        s.codigo_cliente, ");
	            strQuery.append("       s.estado_solicitud, ");	            
	            strQuery.append("        s.fecha_solicitud, ");
	            strQuery.append("       s.estado, ");
	            strQuery.append("       s.fecha_ingreso, ");
	            strQuery.append("       s.usuario_ingreso, ");
	            strQuery.append("       s.fecha_modificacion, ");
	            strQuery.append("       s.usuario_modificacion, ");
	            strQuery.append("       s.fecha_cancelacion, ");
	            strQuery.append("       s.usuario_cancelacion, ");
	            strQuery.append("       s.fecha_aprobacion, ");
	            strQuery.append("       s.usuario_aprobacion, ");
	            strQuery.append("       s.es_registrado, ");
	            strQuery.append("       s.fecha_registro_pedido, ");
	            strQuery.append("       s.usuario_registro, ");
	            strQuery.append("       s.direccion, ");
	            strQuery.append("       s.telefono, ");
	            strQuery.append("       s.observacion, ");
	            strQuery.append("       s.fecha_entrega, ");
	            strQuery.append("       s.codigo_tarifario, ");
	            strQuery.append("       s.codigo_plantilla ");
	            
	            strQuery.append(" FROM    mgi_solicitud_pedido s ");
	            strQuery.append(" WHERE   s.codigo_empresa = :codigoEmpresa ");

	            //filtros
                if (!ObjectUtils.isEmpty(filtroGeneral)) {
                    strQuery.append(" AND     s.codigo_solicitud LIKE :filtroGeneral ");
                }
	            if (!ObjectUtils.isEmpty(codigoCliente)) {
	                strQuery.append(" AND     s.codigo_cliente LIKE :codigoCliente ");
	            }
	            if (!ObjectUtils.isEmpty(estadoSolicitud)) {
	                strQuery.append(" AND     s.estado_solicitud = :estadoSolicitud ");
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
	            if (!ObjectUtils.isEmpty(estadoSolicitud)) {
	                query.setParameter("estadoSolicitud", "%" + estadoSolicitud + "%");
	            }
	          
	            
	            Integer totalSolicitudes = query.getResultList().size();
	            
	            query.setFirstResult((page * perPage) - perPage);
	            query.setMaxResults(perPage);
	            List<Tuple> lsResult = query.getResultList();
	            List<SolicitudPedidoDTO> lsSolicitudes = new ArrayList<>();
	            if (lsResult != null) {
	            	SolicitudPedidoDTO objSolicitudes = null;
	                for (Tuple tuple : lsResult){
	                	objSolicitudes = new SolicitudPedidoDTO();
	                	objSolicitudes.setCodigoSolicitud(tuple.get("codigoSolicitud")!=null? tuple.get("codigoSolicitud", Number.class).longValue() : null);
	                	objSolicitudes.setCodigoCliente(tuple.get("codigoCliente")!=null? tuple.get("codigoCliente", Number.class).longValue() : null);
	                	objSolicitudes.setEstadoSolicitud(tuple.get("estadoSolicitud", String.class));
	                	objSolicitudes.setFechaSolicitud(tuple.get("fechaSolicitud", Date.class).toString());
	                	objSolicitudes.setEstado(tuple.get("estado", String.class));	                	
	                	objSolicitudes.setFechaIngreso(tuple.get("fechaIngreso", Date.class).toString());
	                	objSolicitudes.setUsuarioIngreso(tuple.get("usuarioIngreso", String.class));
	                    objSolicitudes.setFechaModificacion(tuple.get("fechaModificacion", Date.class).toString());
	                    objSolicitudes.setUsuarioModificacion(tuple.get("usuarioModificacion", String.class));
	                    objSolicitudes.setFechaCancelacion(tuple.get("fechaCancelacion", Date.class).toString());
	                    objSolicitudes.setUsuarioCancelacion(tuple.get("usuarioCancelacion", String.class));
	                    objSolicitudes.setFechaAprobacion(tuple.get("fechaAprobacion", Date.class).toString());
	                    objSolicitudes.setUsuarioAprobacion(tuple.get("usuarioAprobacion", String.class));
	                    objSolicitudes.setEsRegistrado(tuple.get("esRegistrado", String.class));
	                    objSolicitudes.setFechaRegistroPedido(tuple.get("fechaRegistroPedido", Date.class).toString());
	                    objSolicitudes.setUsuarioRegistro(tuple.get("usuarioRegistro", String.class));
	                    objSolicitudes.setDireccion(tuple.get("direccion", String.class));
	                    objSolicitudes.setTelefono(tuple.get("telefono", String.class));
	                    objSolicitudes.setObservacion(tuple.get("observacion", String.class));
	                    objSolicitudes.setFechaEntrega(tuple.get("fechaEntrega", Date.class).toString());
	                    objSolicitudes.setCodigoTarifario(tuple.get("codigoTarifario", Number.class).shortValue());
	                    objSolicitudes.setCodigoPlantilla(tuple.get("codigoPlantilla",Number.class).shortValue());
	                    
	                	
	                	lsSolicitudes.add(objSolicitudes);
	                }
	            }
	            resultado.put("solicitudes", lsSolicitudes);
	            resultado.put("totalItems", totalSolicitudes);
	            resultado.put("itemsPerPage", perPage);
	            resultado.put("totalPages", (int) Math.ceil(totalSolicitudes.doubleValue() / perPage.doubleValue()));
	            return resultado;
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
}
