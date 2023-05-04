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
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.tasm.dto.DetalleSolicitudPedidoDTO;
import com.tasm.model.mgi.MgiDetalleSolicitudPedido;

@Service
public class MgiDetalleSolicitudPedidoDAO extends BaseDAO<MgiDetalleSolicitudPedido, Integer> {
	 @PersistenceContext
	 private EntityManager em;
	 
	 protected MgiDetalleSolicitudPedidoDAO() {
	        super(MgiDetalleSolicitudPedido.class);
	    }

	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }

	    @Override
	    public void persist(MgiDetalleSolicitudPedido t) throws PersistenceException {
	        super.persist(t);
	    }
	    
	    @SuppressWarnings("unchecked")
	    public Map<String, Object> obtenerDetalleSolicitudPedido( Integer page, Integer perPage, String filtroGeneral,
	             Integer codigoSolicitud) {
	        Map<String, Object> resultado = new HashMap<>();
	        StringBuilder strQuery = new StringBuilder();
	        try {
	            strQuery.append(" SELECT     s.codigo_solicitud, ");
	            strQuery.append("         s.linea_detalle, ");
	            strQuery.append("        s.codigo_producto, ");
	            strQuery.append("       s.codigo_tarifario, ");	            
	            strQuery.append("        s.tiene_tarifario, ");
	            strQuery.append("       s.cantidad_pedido, ");
	            strQuery.append("       s.valor_tarifario, ");
	            strQuery.append("       s.subtotal, ");
	            strQuery.append("       s.iva, ");
	            strQuery.append("       s.total, ");
	            strQuery.append("       s.estado, ");
	            strQuery.append("       s.fecha_ingreso, ");
	            strQuery.append("       s.usuario_ingreso, ");
	            strQuery.append("       s.fecha_modificacion, ");
	            strQuery.append("       s.usuario_modificacion, ");
	            strQuery.append("       s.codigo_medida_conversacion ");
	            
	            strQuery.append(" FROM    mgi_detalle_solicitud_pedido s ");

	            //filtros
                if (!ObjectUtils.isEmpty(filtroGeneral)) {
                    strQuery.append(" AND     s.codigo_solicitud LIKE :filtroGeneral ");
                }
	            if (!ObjectUtils.isEmpty(codigoSolicitud)) {
	                strQuery.append(" AND     s.codigo_solicitud LIKE :codigoSolicitud ");
	            }
	            
	            TypedQuery<Tuple> query = (TypedQuery<Tuple>) em.createNativeQuery(strQuery.toString(), Tuple.class);            
	           // query.setParameter("codigoEmpresa", codigoEmpresa);

	            
	            //filtros
                if (!ObjectUtils.isEmpty(filtroGeneral)) {
                    query.setParameter("filtroGeneral", "%" + filtroGeneral + "%");
                }
                if (!ObjectUtils.isEmpty(codigoSolicitud)) {
	                query.setParameter("codigoSolicitud", codigoSolicitud);
	            }                
	           
	          
	            
	            Integer totalDetSolicitudes = query.getResultList().size();
	            
	            query.setFirstResult((page * perPage) - perPage);
	            query.setMaxResults(perPage);
	            List<Tuple> lsResult = query.getResultList();
	            List<DetalleSolicitudPedidoDTO> lsDetSolicitudes = new ArrayList<>();
	            if (lsResult != null) {
	            	DetalleSolicitudPedidoDTO objDetSolicitudes = null;
	                for (Tuple tuple : lsResult){
	                	objDetSolicitudes = new DetalleSolicitudPedidoDTO();
	                	objDetSolicitudes.setCodigoSolicitud(tuple.get("codigoSolicitud")!=null? tuple.get("codigoSolicitud", Number.class).longValue() : null);
	                	objDetSolicitudes.setLineaDetalle(tuple.get("lineaDetalle")!=null? tuple.get("lineaDetalle", Number.class).shortValue() : null);
	                	objDetSolicitudes.setCodigoProducto(tuple.get("codigo_producto",Number.class).longValue());
	                	objDetSolicitudes.setCodigoTarifario(tuple.get("codigoTarifario", Number.class).shortValue());
	                	objDetSolicitudes.setTieneTarifario(tuple.get("tieneTarifario",String.class));
	                	objDetSolicitudes.setCantidadPedido(tuple.get("cantidadPedido",Number.class).shortValue());
	                	objDetSolicitudes.setValorTarifario(tuple.get("valorTarifario",Number.class).doubleValue());
	                	objDetSolicitudes.setSubtotal(tuple.get("subtotal", Number.class).doubleValue());
	                	objDetSolicitudes.setIva(tuple.get("iva",Number.class).doubleValue());
	                	objDetSolicitudes.setTotal(tuple.get("total", Number.class).doubleValue());
	                	objDetSolicitudes.setEstado(tuple.get("estado", String.class));
	                	objDetSolicitudes.setFechaIngreso(tuple.get("fechaIngreso", Date.class).toString());
	                	objDetSolicitudes.setUsuarioIngreso(tuple.get("usuarioIngreso", String.class));
	                	objDetSolicitudes.setFechaModificacion(tuple.get("fechaModificacion", Date.class).toString());
	                	objDetSolicitudes.setUsuarioModificacion(tuple.get("usuarioModificacion", String.class));
	                	objDetSolicitudes.setCodigoMedidaConversion(tuple.get("codigoMedidaConversion", Number.class).shortValue());
	                	
	                	lsDetSolicitudes.add(objDetSolicitudes);
	                }
	            }
	            resultado.put("detSolicitudes", lsDetSolicitudes);
	            resultado.put("totalItems", totalDetSolicitudes);
	            resultado.put("itemsPerPage", perPage);
	            resultado.put("totalPages", (int) Math.ceil(totalDetSolicitudes.doubleValue() / perPage.doubleValue()));
	            return resultado;
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
}
