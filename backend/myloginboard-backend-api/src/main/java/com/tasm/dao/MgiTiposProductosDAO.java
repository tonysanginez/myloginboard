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

import com.tasm.dto.TiposProductosDTO;
import com.tasm.model.mgi.MgiTiposProductos;

@Service
public class MgiTiposProductosDAO extends BaseDAO<MgiTiposProductos, Long> {

    @PersistenceContext
    private EntityManager em;

    protected MgiTiposProductosDAO() {
        super(MgiTiposProductos.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void persist(MgiTiposProductos t) throws PersistenceException {
        super.persist(t);
    }

    public Long nextCodigoTipoProducto() {
        try {
            StringBuilder stbQuery = new StringBuilder();
            stbQuery.append("SELECT max(p.codigoTipoProducto) FROM MgiTiposProductos p");
            Query query = em.createQuery(stbQuery.toString());
            Long codigoProducto = (Long) query.getSingleResult();
            return codigoProducto + 1;
        } catch (NoResultException e) {
            return Long.MAX_VALUE;
        }
    } 

    @SuppressWarnings("unchecked")
    public Map<String, Object> obtenerTipoProductos(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
            String nombreTipoProducto, Long codigoTipoProducto,String estado) {
        Map<String, Object> resultado = new HashMap<>();
        StringBuilder strQuery = new StringBuilder();
        try {
            strQuery.append(" SELECT  p.codigo_tipo_producto as codigoTipoProducto, ");
            strQuery.append("         p.nombre_tipo_producto as nombreTipoProducto, "); 
            strQuery.append("         p.estado as estado, ");
            strQuery.append("         p.fecha_ingreso as fechaIngreso, ");
            strQuery.append("         p.usuario_ingreso as usuarioIngreso ");
            strQuery.append(" FROM    mgi_tipos_productos p "); 
            strQuery.append(" WHERE   p.codigo_empresa = :codigoEmpresa "); 

            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                strQuery.append(" AND     p.nombre_tipo_producto LIKE :filtroGeneral ");
            }
            if (!ObjectUtils.isEmpty(estado)) {
                strQuery.append(" AND     p.estado = :estado ");
            }
            if (!ObjectUtils.isEmpty(nombreTipoProducto)) {
                strQuery.append(" AND     p.nombre_tipo_producto LIKE :nombreTipoProducto ");
            }
            if (!ObjectUtils.isEmpty(codigoTipoProducto)) {
                strQuery.append(" AND     p.codigo_tipo_producto = :codigoTipoProducto ");
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
            if (!ObjectUtils.isEmpty(nombreTipoProducto)) {
                query.setParameter("nombreTipoProducto", "%" + nombreTipoProducto + "%");
            }
            if (!ObjectUtils.isEmpty(codigoTipoProducto)) {
                query.setParameter("codigoTipoProducto", codigoTipoProducto);
            } 
            
            Integer totalProductos = query.getResultList().size();
            
            query.setFirstResult((page * perPage) - perPage);
            query.setMaxResults(perPage);
            List<Tuple> lsResult = query.getResultList();
            List<TiposProductosDTO> lsProductos = new ArrayList<>();
            if (lsResult != null) {
                TiposProductosDTO objProducto = null;
                for (Tuple tuple : lsResult){
                    objProducto = new TiposProductosDTO();
                    objProducto.setCodigoTipoProducto(tuple.get("codigoTipoProducto") != null ? tuple.get("codigoTipoProducto", Number.class).longValue() : null);
                    objProducto.setNombreTipoProducto(tuple.get("nombreTipoProducto", String.class)); 
                    objProducto.setEstado(tuple.get("estado", String.class));
                    objProducto.setFechaIngreso(tuple.get("fechaIngreso", Date.class).toString());
                    objProducto.setUsuarioIngreso(tuple.get("usuarioIngreso", String.class)); 
                    lsProductos.add(objProducto);
                }
            }
            resultado.put("tiposProductos", lsProductos);
            resultado.put("totalItems", totalProductos);
            resultado.put("itemsPerPage", perPage);
            resultado.put("totalPages", (int) Math.ceil(totalProductos.doubleValue() / perPage.doubleValue()));
            return resultado;
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
