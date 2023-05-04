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

import com.tasm.dto.FormasPagoDTO;
import com.tasm.model.gen.GenFormasPago;

@Service
public class GenFormasPagoDAO extends BaseDAO<GenFormasPago, Long> {

	@PersistenceContext
    private EntityManager em;

    protected GenFormasPagoDAO() {
        super(GenFormasPago.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void persist(GenFormasPago t) throws PersistenceException {
        super.persist(t);
    }

    public Long nextCodigoFormasPago() {
        try {
            StringBuilder stbQuery = new StringBuilder();
            stbQuery.append("SELECT max(fp.codigoFormasPago) FROM GenFormasPago fp");
            Query query = em.createQuery(stbQuery.toString());
            Long codigoFormasPago = query.getSingleResult()==null?(long) 0:(Long) query.getSingleResult();
            return codigoFormasPago + 1;
        } catch (NoResultException e) {
            return Long.MAX_VALUE;
        }
    }
	
    @SuppressWarnings("unchecked")
	public Map<String, Object> obtenerFormasPago(Short codigoEmpresa, Integer page, Integer perPage,
			String filtroGeneral, Integer codigoFormaPago, String nemonico, String nombre_forma_pago, String codigo_sri,
			String estado) {

        Map<String, Object> resultado = new HashMap<>();
        StringBuilder strQuery = new StringBuilder();
        try {
            strQuery.append(" SELECT  fp.codigo_forma_pago as codigoFormaPago, ");
            strQuery.append("         fp.nemonico as nemonico, ");
            strQuery.append("         fp.nombre_forma_pago as nombreFormapago, ");
            strQuery.append("         fp.codigo_sri as codigoSri, ");
            strQuery.append("         fp.estado as estado, ");
            strQuery.append("         fp.fecha_ingreso as fechaIngreso ");
            strQuery.append(" FROM    gen_formas_pago fp ");;
            strQuery.append(" WHERE   fp.estado = :estado ");
            strQuery.append(" AND     fp.codigo_empresa = :codigoEmpresa ");
            
            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                strQuery.append(" AND     (fp.nemonico LIKE :filtroGeneral ");
                strQuery.append(" OR      fp.nombre_forma_pago LIKE :filtroGeneral ");
                strQuery.append(" OR      fp.codigo_sri LIKE :filtroGeneral)");
            }
            if (!ObjectUtils.isEmpty(codigoFormaPago)) {
                strQuery.append(" AND     fp.codigo_forma_pago = :codigoFormaPago ");
            }
            if (!ObjectUtils.isEmpty(nemonico)) {
                strQuery.append(" AND     fp.nemonico LIKE :nemonico ");
            }
            if (!ObjectUtils.isEmpty(nombre_forma_pago)) {
                strQuery.append(" AND     fp.nombre_forma_pago LIKE :nombre_forma_pago ");
            }
            if (!ObjectUtils.isEmpty(codigo_sri)) {
                strQuery.append(" AND     fp.codigo_sri = :codigo_sri ");
            }

            TypedQuery<Tuple> query = (TypedQuery<Tuple>) em.createNativeQuery(strQuery.toString(), Tuple.class);            
            query.setParameter("estado", estado);
            query.setParameter("codigoEmpresa", codigoEmpresa);
            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                query.setParameter("filtroGeneral", "%" + filtroGeneral + "%");
            }
            if (!ObjectUtils.isEmpty(codigoFormaPago)) {
                query.setParameter("codigoFormaPago", codigoFormaPago);
            }
            if (!ObjectUtils.isEmpty(nemonico)) {
                query.setParameter("nemonico", "%" + nemonico + "%");
            }
            if (!ObjectUtils.isEmpty(nombre_forma_pago)) {
                query.setParameter("nombre_forma_pago", "%" + nombre_forma_pago + "%");
            }
            if (!ObjectUtils.isEmpty(codigo_sri)) {
                query.setParameter("codigo_sri", codigo_sri);
            }

            Integer totalFormasPago = query.getResultList().size();
            query.setFirstResult((page * perPage) - perPage);
            query.setMaxResults(perPage);
            List<Tuple> lsResult = query.getResultList();
            List<FormasPagoDTO> lsFormaPago = new ArrayList<>();
       
            if (lsResult != null) {
            	FormasPagoDTO objFormasPago = null;
                for (Tuple tuple : lsResult){
                    objFormasPago = new FormasPagoDTO();
                    objFormasPago.setCodigoFormasPago(tuple.get("codigoFormaPago") != null ? tuple.get("codigoFormaPago", Number.class).longValue() : null);
                    objFormasPago.setNemonico(tuple.get("nemonico", String.class));
                    objFormasPago.setNombreFormaPago(tuple.get("nombreFormapago", String.class));
                    objFormasPago.setCodigoSri(tuple.get("codigoSri", String.class));
                    objFormasPago.setEstado(tuple.get("estado", String.class));
                    objFormasPago.setFechaIngreso(tuple.get("fechaIngreso", Date.class).toString());
                    lsFormaPago.add(objFormasPago);
                }
            }
            resultado.put("formasPago", lsFormaPago);
            resultado.put("totalItems", totalFormasPago);
            resultado.put("itemsPerPage", perPage);
            resultado.put("totalPages", (int) Math.ceil(totalFormasPago.doubleValue() / perPage.doubleValue()));
            return resultado;
        } catch (NoResultException e) {
            return null;
        }
		
	}

}
