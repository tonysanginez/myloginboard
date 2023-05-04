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

import com.tasm.dto.UnidadesMedidaDTO;
import com.tasm.model.mgi.MgiUnidadesMedida;

@Service
public class MgiUnidadesMedidaDAO extends BaseDAO<MgiUnidadesMedida, Integer> {

    @PersistenceContext
    private EntityManager em;

    protected MgiUnidadesMedidaDAO() {
        super(MgiUnidadesMedida.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void persist(MgiUnidadesMedida t) throws PersistenceException {
        super.persist(t);
    }

    public Integer nextCodigoUnidadMedida() {
        try {
            StringBuilder stbQuery = new StringBuilder();
            stbQuery.append("SELECT max(p.codigoUnidadMedida) FROM MgiUnidadesMedida p");
            Query query = em.createQuery(stbQuery.toString());
            Integer codigoUnidad = (Integer) query.getSingleResult();
            return codigoUnidad + 1;
        } catch (NoResultException e) {
            return Integer.MAX_VALUE;
        }
    } 

    @SuppressWarnings("unchecked")
    public Map<String, Object> obtenerUnidadesMedida(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
            String nombreUnidadMedida, Integer codigoUnidadMedida, String abreviatura, String estado) {
        Map<String, Object> resultado = new HashMap<>();
        StringBuilder strQuery = new StringBuilder();
        try {
            strQuery.append(" SELECT  p.codigo_unidad_medida as codigoUnidadMedida, ");
            strQuery.append("         p.nombre_unidad_medida as nombreUnidadMedida, "); 
            strQuery.append("         p.abreviatura as abreviatura, "); 
            strQuery.append("         p.estado as estado, ");
            strQuery.append("         p.fecha_ingreso as fechaIngreso, ");
            strQuery.append("         p.usuario_ingreso as usuarioIngreso ");
            strQuery.append(" FROM    mgi_unidades_medida p "); 
            strQuery.append(" WHERE   p.codigo_empresa = :codigoEmpresa "); 

            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                strQuery.append(" AND     (p.nombre_unidad_medida LIKE :filtroGeneral ");
                strQuery.append(" OR      p.abreviatura LIKE :filtroGeneral) ");
            }
            if (!ObjectUtils.isEmpty(estado)) {
                strQuery.append(" AND     p.estado = :estado ");
            }
            if (!ObjectUtils.isEmpty(nombreUnidadMedida)) {
                strQuery.append(" AND     p.nombre_unidad_medida LIKE :nombreUnidadMedida ");
            }
            if (!ObjectUtils.isEmpty(codigoUnidadMedida)) {
                strQuery.append(" AND     p.codigo_unidad_medida = :codigoUnidadMedida ");
            }
            if (!ObjectUtils.isEmpty(abreviatura)) {
                strQuery.append(" AND     p.abreviatura LIKE :abreviatura ");
            }

            TypedQuery<Tuple> query = (TypedQuery<Tuple>) em.createNativeQuery(strQuery.toString(), Tuple.class);            
             query.setParameter("codigoEmpresa", codigoEmpresa);

            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                query.setParameter("filtroGeneral", "%" + filtroGeneral + "%");
            }
            if (!ObjectUtils.isEmpty(estado)) {
                query.setParameter("estado", estado);
            } 
            if (!ObjectUtils.isEmpty(nombreUnidadMedida)) {
                query.setParameter("nombreUnidadMedida", "%" + nombreUnidadMedida + "%");
            }
            if (!ObjectUtils.isEmpty(codigoUnidadMedida)) {
                query.setParameter("codigoUnidadMedida", codigoUnidadMedida);
            }
            if (!ObjectUtils.isEmpty(abreviatura)) {
                query.setParameter("abreviatura", "%" + abreviatura + "%");
            }
            
            Integer totalProductos = query.getResultList().size();
            
            query.setFirstResult((page * perPage) - perPage);
            query.setMaxResults(perPage);
            List<Tuple> lsResult = query.getResultList();
            List<UnidadesMedidaDTO> lsUnidades = new ArrayList<>();
            if (lsResult != null) {
            	UnidadesMedidaDTO Unidad = null;
                for (Tuple tuple : lsResult){
                	Unidad = new UnidadesMedidaDTO();
                	Unidad.setCodigoUnidadMedida(tuple.get("codigoUnidadMedida") != null ? tuple.get("codigoUnidadMedida", Number.class).longValue() : null);
                	Unidad.setNombreUnidadMedida(tuple.get("nombreUnidadMedida", String.class)); 
                	Unidad.setEstado(tuple.get("estado", String.class));
                	Unidad.setFechaIngreso(tuple.get("fechaIngreso", Date.class).toString());
                	Unidad.setUsuarioIngreso(tuple.get("usuarioIngreso", String.class)); 
                	Unidad.setAbreviatura(tuple.get("abreviatura", String.class)); 
                	lsUnidades.add(Unidad);
                }
            }
            resultado.put("unidades", lsUnidades);
            resultado.put("totalItems", totalProductos);
            resultado.put("itemsPerPage", perPage);
            resultado.put("totalPages", (int) Math.ceil(totalProductos.doubleValue() / perPage.doubleValue()));
            return resultado;
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
