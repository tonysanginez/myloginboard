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
import com.tasm.model.mgi.MgiMarcas;

@Service
public class MgiMarcasDAO extends BaseDAO<MgiMarcas, Integer> {
	 @PersistenceContext
	 private EntityManager em;
	 
	 protected MgiMarcasDAO() {
	        super(MgiMarcas.class);
	    }

	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }

	    @Override
	    public void persist(MgiMarcas t) throws PersistenceException {
	        super.persist(t);
	    }

	    public Integer nextCodigoMarca() {
	        try {
	            StringBuilder stbQuery = new StringBuilder();
	            stbQuery.append("SELECT max(m.codigoMarca) FROM MgiMarcas m");
	            Query query = em.createQuery(stbQuery.toString());
	            Integer codigoMarca = (Integer) query.getSingleResult();
	            return codigoMarca + 1;
	        } catch (NoResultException e) {
	            return Integer.MAX_VALUE;
	        }
	    }
	    
	    @SuppressWarnings("unchecked")
	    public Map<String, Object> obtenerMarcas(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
	            String nombreMarca, Integer codigoMarca, String estado) {
	        Map<String, Object> resultado = new HashMap<>();
	        StringBuilder strQuery = new StringBuilder();
	        try {
	            strQuery.append(" SELECT  m.codigo_marca as codigoMarca, ");
	            strQuery.append("         m.nombre_marca as nombreMarca, ");
	            strQuery.append("         m.estado as estado, ");
	            strQuery.append("         m.fecha_ingreso as fechaIngreso ");
	            strQuery.append(" FROM    mgi_marcas m ");;
	            strQuery.append(" WHERE   m.estado = :estado ");
	            strQuery.append(" AND     m.codigo_empresa = :codigoEmpresa ");

	            //filtros
                    if (!ObjectUtils.isEmpty(filtroGeneral)) {
                        strQuery.append(" AND     m.nombre_marca LIKE :filtroGeneral ");
                    }
	            if (!ObjectUtils.isEmpty(nombreMarca)) {
	                strQuery.append(" AND     m.nombre_marca LIKE :nombreMarca ");
	            }
	            if (!ObjectUtils.isEmpty(codigoMarca)) {
	                strQuery.append(" AND     m.codigo_marca = :codigoMarca ");
	            }
	            
	            TypedQuery<Tuple> query = (TypedQuery<Tuple>) em.createNativeQuery(strQuery.toString(), Tuple.class);            
	            query.setParameter("estado", estado);
	            query.setParameter("codigoEmpresa", codigoEmpresa);

	            
	            //filtros
                    if (!ObjectUtils.isEmpty(filtroGeneral)) {
                        query.setParameter("filtroGeneral", "%" + filtroGeneral + "%");
                    }
	            if (!ObjectUtils.isEmpty(nombreMarca)) {
	                query.setParameter("nombreMarca", "%" + nombreMarca + "%");
	            }
	            if (!ObjectUtils.isEmpty(codigoMarca)) {
	                query.setParameter("codigoMarca", codigoMarca);
	            }
	            
	            Integer totalMarcas = query.getResultList().size();
	            
	            query.setFirstResult((page * perPage) - perPage);
	            query.setMaxResults(perPage);
	            List<Tuple> lsResult = query.getResultList();
	            List<MarcaDTO> lsMarcas = new ArrayList<>();
	            if (lsResult != null) {
	            	MarcaDTO objMarca = null;
	                for (Tuple tuple : lsResult){
	                	objMarca = new MarcaDTO();
	                	objMarca.setCodigoMarca(tuple.get("codigoMarca")!=null? tuple.get("codigoMarca", Number.class).intValue() : null);
	                	objMarca.setNombreMarca(tuple.get("nombreMarca", String.class));
	                	objMarca.setEstado(tuple.get("estado", String.class));
	                	objMarca.setFechaIngreso(tuple.get("fechaIngreso", Date.class).toString());
	                    
	                	lsMarcas.add(objMarca);
	                }
	            }
	            resultado.put("marcas", lsMarcas);
	            resultado.put("totalItems", totalMarcas);
	            resultado.put("itemsPerPage", perPage);
	            resultado.put("totalPages", (int) Math.ceil(totalMarcas.doubleValue() / perPage.doubleValue()));
	            return resultado;
	        } catch (NoResultException e) {
	            return null;
	        }
	    }
}
