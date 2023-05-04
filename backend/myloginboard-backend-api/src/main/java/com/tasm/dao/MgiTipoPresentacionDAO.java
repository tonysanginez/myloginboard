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

import com.tasm.dto.TipoPresentacionDTO;
import com.tasm.model.mgi.MgiTipoPresentacion;

@Service
public class MgiTipoPresentacionDAO extends BaseDAO<MgiTipoPresentacion, Integer> {
	 @PersistenceContext
	 private EntityManager em;
	 
	 protected MgiTipoPresentacionDAO() {
	        super(MgiTipoPresentacion.class);
	    }

	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }

	    @Override
	    public void persist(MgiTipoPresentacion t) throws PersistenceException {
	        super.persist(t);
	    }

	    public Integer nextCodigoTipoPresentacion() {
	        try {
	            StringBuilder stbQuery = new StringBuilder();
	            stbQuery.append("SELECT max(m.codigoTipoPresentacion) FROM MgiTipoPresentacion m");
	            Query query = em.createQuery(stbQuery.toString());
	            Integer codigoTipoPresentacion = (Integer) query.getSingleResult();
	            return codigoTipoPresentacion + 1;
	        } catch (NoResultException e) {
	            return Integer.MAX_VALUE;
	        }
	    }
	    
	    @SuppressWarnings("unchecked")
	    public Map<String, Object> obtenerTiposPresentacion(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
	            String nombreTipoPresentacion, Integer codigoTipoPresentacion, String estado) {
	        Map<String, Object> resultado = new HashMap<>();
	        StringBuilder strQuery = new StringBuilder();
	        try {
	            strQuery.append(" SELECT  m.codigo_tipo_presentacion as codigoTipoPresentacion, ");
	            strQuery.append("         m.nombre_tipo_presentacion as nombreTipoPresentacion, ");
	            strQuery.append("         m.estado as estado, ");
	            strQuery.append("         m.fecha_ingreso as fechaIngreso ");
	            strQuery.append(" FROM    mgi_tipos_presentacion m ");;
	            //strQuery.append(" WHERE   m.estado = :estado ");
	            //strQuery.append(" AND     m.codigo_empresa = :codigoEmpresa ");
	            strQuery.append(" WHERE     m.codigo_empresa = :codigoEmpresa ");

	            //filtros
                if (!ObjectUtils.isEmpty(filtroGeneral)) {
                        strQuery.append(" AND     m.nombre_tipo_presentacion LIKE :filtroGeneral ");
                }
	            if (!ObjectUtils.isEmpty(nombreTipoPresentacion)) {
	                strQuery.append(" AND     m.nombre_tipo_presentacion LIKE :nombreTipoPresentacion ");
	            }
	            if (!ObjectUtils.isEmpty(estado)) {
	                strQuery.append(" AND     p.estado = :estado ");
	            }
	            if (!ObjectUtils.isEmpty(codigoTipoPresentacion)) {
	                strQuery.append(" AND     m.codigo_tipo_presentacion = :codigoTipoPresentacion ");
	            }
	            
	            TypedQuery<Tuple> query = (TypedQuery<Tuple>) em.createNativeQuery(strQuery.toString(), Tuple.class);            
	            //query.setParameter("estado", estado); no cargaba el listado
	            query.setParameter("codigoEmpresa", codigoEmpresa);

	            
	            //filtros
                    if (!ObjectUtils.isEmpty(filtroGeneral)) {
                        query.setParameter("filtroGeneral", "%" + filtroGeneral + "%");
                    }
	            if (!ObjectUtils.isEmpty(nombreTipoPresentacion)) {
	                query.setParameter("nombreTipoPresentacion", "%" + nombreTipoPresentacion + "%");
	            }
	            if (!ObjectUtils.isEmpty(codigoTipoPresentacion)) {
	                query.setParameter("codigoTipoPresentacion", codigoTipoPresentacion);
	            }
	            
	            Integer totalTipoPresentacion = query.getResultList().size();
	            
	            query.setFirstResult((page * perPage) - perPage);
	            query.setMaxResults(perPage);
	            List<Tuple> lsResult = query.getResultList();
	            List<TipoPresentacionDTO> lsTipoPresentacion = new ArrayList<>();
	            if (lsResult != null) {
	            	TipoPresentacionDTO objTipoPresentacion = null;
	                for (Tuple tuple : lsResult){
	                	objTipoPresentacion = new TipoPresentacionDTO();
	                	objTipoPresentacion.setCodigoTipoPresentacion(tuple.get("codigoTipoPresentacion")!=null? tuple.get("codigoTipoPresentacion", Number.class).intValue() : null);
	                	objTipoPresentacion.setNombreTipoPresentacion(tuple.get("nombreTipoPresentacion", String.class));
	                	objTipoPresentacion.setEstado(tuple.get("estado", String.class));
	                	objTipoPresentacion.setFechaIngreso(tuple.get("fechaIngreso", Date.class).toString());
	                    
	                	lsTipoPresentacion.add(objTipoPresentacion);
	                }
	            }
	            resultado.put("tipoPresentacion", lsTipoPresentacion);
	            resultado.put("totalItems", totalTipoPresentacion);
	            resultado.put("itemsPerPage", perPage);
	            resultado.put("totalPages", (int) Math.ceil(totalTipoPresentacion.doubleValue() / perPage.doubleValue()));
	            return resultado;
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
}
