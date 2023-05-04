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

import com.tasm.dto.ProductoDTO;
import com.tasm.model.com.ComProductos;

@Service
public class ComProductosDAO extends BaseDAO<ComProductos, Long> {

    @PersistenceContext
    private EntityManager em;

    protected ComProductosDAO() {
        super(ComProductos.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void persist(ComProductos t) throws PersistenceException {
        super.persist(t);
    }

    public Long nextCodigoProducto() {
        try {
            StringBuilder stbQuery = new StringBuilder();
            stbQuery.append("SELECT max(p.codigoProducto) FROM ComProductos p");
            Query query = em.createQuery(stbQuery.toString());
            Long codigoProducto = (Long) query.getSingleResult();
            return codigoProducto + 1;
        } catch (NoResultException e) {
            return Long.MAX_VALUE;
        }
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> obtenerProductos(Short codigoEmpresa, Integer page, Integer perPage, String filtroGeneral,
            String codigoProducto, String nombreProducto, String descripcion, Integer codigoTipoProducto, Integer codigoTipoPresentacion,
            Integer codigoMarca, Integer codigoUnidadMedida, String estado, String aplicaIva, String aplicaStock, String codigoBarra) {
        Map<String, Object> resultado = new HashMap<>();
        StringBuilder strQuery = new StringBuilder();
        try {
            strQuery.append(" SELECT  p.codigo_producto as codigoProducto, ");
            strQuery.append("         p.nombre_producto as nombreProducto, ");
            strQuery.append("         p.descripcion as descripcion, ");
            strQuery.append("         p.codigo_barra as codigoBarra, ");
            strQuery.append("         p.aplica_iva as aplicaIva, ");
            strQuery.append("         p.aplica_stock as aplicaStock, ");
            strQuery.append("         p.estado as estado, ");
            strQuery.append("         p.fecha_ingreso as fechaIngreso, ");
            strQuery.append("         tprod.nombre_tipo_producto as tipoProducto, ");
            strQuery.append("         tprod.codigo_tipo_producto as codigoTipoProducto, ");
            strQuery.append("         tpres.nombre_tipo_presentacion as tipoPresentacion, ");
            strQuery.append("         tpres.codigo_tipo_presentacion as codigoTipoPresentacion, ");
            strQuery.append("         m.nombre_marca as marca, ");
            strQuery.append("         m.codigo_marca as codigoMarca, ");
            strQuery.append("         um.abreviatura as unidadMedida, ");
            strQuery.append("         um.codigo_unidad_medida as codigoUnidadMedida ");
            strQuery.append(" FROM    com_productos p, ");
            strQuery.append("         mgi_tipos_productos tprod, ");
            strQuery.append("         mgi_tipos_presentacion tpres, ");
            strQuery.append("         mgi_marcas m, ");
            strQuery.append("         mgi_unidades_medida um ");
           // strQuery.append(" WHERE   p.estado = :estado ");
           // strQuery.append(" AND     p.codigo_empresa = :codigoEmpresa ");
            strQuery.append(" WHERE     p.codigo_empresa = :codigoEmpresa ");
            strQuery.append(" AND     p.codigo_tipo_producto = tprod.codigo_tipo_producto ");
            strQuery.append(" AND     p.codigo_tipo_presentacion = tpres.codigo_tipo_presentacion ");
            strQuery.append(" AND     p.codigo_marca = m.codigo_marca ");
            strQuery.append(" AND     p.codigo_unidad_medida = um.codigo_unidad_medida ");
            
            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                strQuery.append(" AND     (p.nombre_producto LIKE :filtroGeneral ");
                strQuery.append(" OR      p.descripcion LIKE :filtroGeneral ");
                strQuery.append(" OR      tprod.nombre_tipo_producto LIKE :filtroGeneral ");
                strQuery.append(" OR      tpres.nombre_tipo_presentacion LIKE :filtroGeneral ");
                strQuery.append(" OR      m.nombre_marca LIKE :filtroGeneral ");
                strQuery.append(" OR      um.nombre_unidad_medida LIKE :filtroGeneral) ");
            }
            if (!ObjectUtils.isEmpty(codigoProducto)) {
                strQuery.append(" AND     p.codigo_producto = :codigoProducto ");
            }
            if (!ObjectUtils.isEmpty(descripcion)) {
                strQuery.append(" AND     p.descripcion LIKE :descripcion ");
            }
            if (!ObjectUtils.isEmpty(nombreProducto)) {
                strQuery.append(" AND     p.nombre_producto LIKE :nombreProducto ");
            }
            if (!ObjectUtils.isEmpty(codigoTipoProducto)) {
                strQuery.append(" AND     p.codigo_tipo_producto = :codigoTipoProducto ");
            }
            if (!ObjectUtils.isEmpty(codigoTipoPresentacion)) {
                strQuery.append(" AND     p.codigo_tipo_presentacion = :codigoTipoPresentacion ");
            }
            if (!ObjectUtils.isEmpty(codigoMarca)) {
                strQuery.append(" AND     p.codigo_marca = :codigoMarca ");
            }
            if (!ObjectUtils.isEmpty(codigoUnidadMedida)) {
                strQuery.append(" AND     p.codigo_unidad_medida = :codigoUnidadMedida ");
            }
            if (!ObjectUtils.isEmpty(aplicaIva)) {
                strQuery.append(" AND     p.aplica_iva = :aplicaIva ");
            }
            if (!ObjectUtils.isEmpty(aplicaStock)) {
                strQuery.append(" AND     p.aplica_stock = :aplicaStock ");
            }
            if (!ObjectUtils.isEmpty(codigoBarra)) {
                strQuery.append(" AND     p.codigo_barra LIKE :codigoBarra ");
            }
            if (!ObjectUtils.isEmpty(estado)) {
                strQuery.append(" AND     p.estado LIKE :estado ");
            }

            TypedQuery<Tuple> query = (TypedQuery<Tuple>) em.createNativeQuery(strQuery.toString(), Tuple.class);            
            //query.setParameter("estado", estado);
            query.setParameter("codigoEmpresa", codigoEmpresa);

            if (!ObjectUtils.isEmpty(aplicaIva)) {
                query.setParameter("aplicaIva", aplicaIva);
            }
            if (!ObjectUtils.isEmpty(aplicaStock)) {
                query.setParameter("aplicaStock", aplicaStock);
            }

            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                query.setParameter("filtroGeneral", "%" + filtroGeneral + "%");
            }
            if (!ObjectUtils.isEmpty(codigoProducto)) {
                query.setParameter("codigoProducto", codigoProducto);
            }
            if (!ObjectUtils.isEmpty(nombreProducto)) {
                query.setParameter("nombreProducto", "%" + nombreProducto + "%");
            }
            if (!ObjectUtils.isEmpty(descripcion)) {
                query.setParameter("descripcion", "%" + descripcion + "%");
            }
            if (!ObjectUtils.isEmpty(codigoTipoProducto)) {
                query.setParameter("codigoTipoProducto", codigoTipoProducto);
            }
            if (!ObjectUtils.isEmpty(codigoTipoPresentacion)) {
                query.setParameter("codigoTipoPresentacion", codigoTipoPresentacion);
            }
            if (!ObjectUtils.isEmpty(codigoMarca)) {
                query.setParameter("codigoMarca", codigoMarca);
            }
            if (!ObjectUtils.isEmpty(codigoUnidadMedida)) {
                query.setParameter("codigoUnidadMedida", codigoUnidadMedida);
            }
            if (!ObjectUtils.isEmpty(codigoBarra)) {
                query.setParameter("codigoBarra", "%" + codigoBarra + "%");
            }
            
            Integer totalProductos = query.getResultList().size();
            
            query.setFirstResult((page * perPage) - perPage);
            query.setMaxResults(perPage);
            List<Tuple> lsResult = query.getResultList();
            List<ProductoDTO> lsProductos = new ArrayList<>();
            if (lsResult != null) {
                ProductoDTO objProducto = null;
                for (Tuple tuple : lsResult){
                    objProducto = new ProductoDTO();
                    objProducto.setCodigoProducto(tuple.get("codigoProducto") != null ? tuple.get("codigoProducto", Number.class).longValue() : null);
                    objProducto.setNombreProducto(tuple.get("nombreProducto", String.class));
                    objProducto.setDescripcion(tuple.get("descripcion") != null ? tuple.get("descripcion", String.class) : null);
                    objProducto.setCodigoBarra(tuple.get("codigoBarra", String.class));
                    objProducto.setTipoProducto(tuple.get("tipoProducto", String.class));
                    objProducto.setCodigoTipoProducto(tuple.get("codigoTipoProducto", Number.class).intValue());
                    objProducto.setTipoPresentacion(tuple.get("tipoPresentacion", String.class));
                    objProducto.setCodigoTipoPresentacion(tuple.get("codigoTipoPresentacion", Number.class).intValue());
                    objProducto.setMarca(tuple.get("marca", String.class));
                    objProducto.setCodigoMarca(tuple.get("codigoMarca", Number.class).intValue());
                    objProducto.setUnidadMedida(tuple.get("unidadMedida", String.class));
                    objProducto.setCodigoUnidadMedida(tuple.get("codigoUnidadMedida", Number.class).intValue());
                    objProducto.setAplicaIva(tuple.get("aplicaIva", String.class));
                    objProducto.setAplicaStock(tuple.get("aplicaStock", String.class));
                    objProducto.setEstado(tuple.get("estado", String.class));
                    objProducto.setFechaIngreso(tuple.get("fechaIngreso", Date.class).toString());
                    
                    lsProductos.add(objProducto);
                }
            }
            resultado.put("productos", lsProductos);
            resultado.put("totalItems", totalProductos);
            resultado.put("itemsPerPage", perPage);
            resultado.put("totalPages", (int) Math.ceil(totalProductos.doubleValue() / perPage.doubleValue()));
            return resultado;
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
