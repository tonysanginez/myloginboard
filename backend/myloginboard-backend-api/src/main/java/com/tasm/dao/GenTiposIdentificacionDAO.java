package com.tasm.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;

import com.tasm.dto.MarcaDTO;
import com.tasm.dto.TipoIdentificacionDTO;
import com.tasm.model.gen.GenTiposIdentificacion;
import com.tasm.model.gen.GenTiposIdentificacionCPK;

import lombok.NonNull;

@Service
public class GenTiposIdentificacionDAO extends BaseDAO<GenTiposIdentificacion, GenTiposIdentificacionCPK> {
	
	protected GenTiposIdentificacionDAO() {
		super(GenTiposIdentificacion.class);
	}

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void persist(GenTiposIdentificacion t) 
	throws PersistenceException {
		super.persist(t);
	}

	@Override
	public void update(GenTiposIdentificacion t) 
	throws PersistenceException {
		super.update(t);
	}

	@Override
	public Optional<GenTiposIdentificacion> find(@NonNull GenTiposIdentificacionCPK id) {
		return super.find(id);
	}
	 @SuppressWarnings("unchecked")
	    public Map<String, Object> obtenerTiposIdentificacion(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
	            String nombreTipoIdentificacion, Integer codigoTipoIdentificacion, String nemonico, String estado) {
	        Map<String, Object> resultado = new HashMap<>();
	        StringBuilder strQuery = new StringBuilder();
	        try {
	            strQuery.append(" SELECT  m.codigo_tipo_identificacion as codigoTipoIdentificacion, ");
	            strQuery.append("         m.nombre_tipo_identificacion as nombreTipoIdentificacion, ");
	            strQuery.append("         m.nemonico as nemonico, ");
	            strQuery.append("         m.estado as estado, ");
	            strQuery.append("         m.fecha_ingreso as fechaIngreso ");
	            strQuery.append(" FROM    gen_tipos_identificacion m ");;
	            strQuery.append(" WHERE   m.estado = :estado ");
	            strQuery.append(" AND     m.codigo_empresa = :codigoEmpresa ");

	            //filtros
                 if (!ObjectUtils.isEmpty(filtroGeneral)) {
                     strQuery.append(" AND     m.nombre_marca LIKE :filtroGeneral ");
                 }
	            if (!ObjectUtils.isEmpty(nombreTipoIdentificacion)) {
	                strQuery.append(" AND     m.nombre_tipo_identificacion LIKE :nombreTipoIdentificacion ");
	            }
	            if (!ObjectUtils.isEmpty(codigoTipoIdentificacion)) {
	                strQuery.append(" AND     m.codigo_tipo_identificacion = :codigoTipoIdentificacion ");
	            }
	            
	            TypedQuery<Tuple> query = (TypedQuery<Tuple>) em.createNativeQuery(strQuery.toString(), Tuple.class);            
	            query.setParameter("estado", estado);
	            query.setParameter("codigoEmpresa", codigoEmpresa);

	            
	            //filtros
                 if (!ObjectUtils.isEmpty(filtroGeneral)) {
                     query.setParameter("filtroGeneral", "%" + filtroGeneral + "%");
                 }
	            if (!ObjectUtils.isEmpty(nombreTipoIdentificacion)) {
	                query.setParameter("nombreTipoIdentificacion", "%" + nombreTipoIdentificacion + "%");
	            }
	            if (!ObjectUtils.isEmpty(codigoTipoIdentificacion)) {
	                query.setParameter("codigoTipoIdentificacion", codigoTipoIdentificacion);
	            }
	            
	            Integer totalTiposIdentificacion = query.getResultList().size();
	            
	            query.setFirstResult((page * perPage) - perPage);
	            query.setMaxResults(perPage);
	            List<Tuple> lsResult = query.getResultList();
	            List<TipoIdentificacionDTO> lsTiposIdentificacion = new ArrayList<>();
	            if (lsResult != null) {
	            	TipoIdentificacionDTO objTipoIdentificacion = null;
	                for (Tuple tuple : lsResult){
	                	objTipoIdentificacion = new TipoIdentificacionDTO();
	                	objTipoIdentificacion.setCodigoTipoIdentificacion(tuple.get("codigoTipoIdentificacion")!=null? tuple.get("codigoTipoIdentificacion", Number.class).intValue() : null);
	                	objTipoIdentificacion.setNombreTipoIdentificacion(tuple.get("nombreTipoIdentificacion", String.class));
	                	objTipoIdentificacion.setNemonico(tuple.get("nemonico",String.class));
	                	objTipoIdentificacion.setEstado(tuple.get("estado", String.class));
	                	objTipoIdentificacion.setFechaIngreso(tuple.get("fechaIngreso", Date.class).toString());
	                    
	                	lsTiposIdentificacion.add(objTipoIdentificacion);
	                }
	            }
	            resultado.put("tiposIdentificacion", lsTiposIdentificacion);
	            resultado.put("totalItems", totalTiposIdentificacion);
	            resultado.put("itemsPerPage", perPage);
	            resultado.put("totalPages", (int) Math.ceil(totalTiposIdentificacion.doubleValue() / perPage.doubleValue()));
	            return resultado;
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
}