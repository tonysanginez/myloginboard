package com.tasm.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.tasm.model.gen.GenInstituciones;
@Service
public class GenInstitucionesDAO extends BaseDAO<GenInstituciones, Long>{

	@PersistenceContext
    private EntityManager em;

    protected GenInstitucionesDAO() {
        super(GenInstituciones.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void persist(GenInstituciones t) throws PersistenceException {
        super.persist(t);
    }

    public Long nextCodigoInstituciones() {
        try {
            StringBuilder stbQuery = new StringBuilder();
            stbQuery.append("SELECT max(i.codigoInstitucion) FROM GenInstituciones i");
            Query query = em.createQuery(stbQuery.toString());
            Long codigo = query.getSingleResult()==null?(long) 0:(Long) query.getSingleResult();
            return codigo + 1;
        } catch (NoResultException e) {
            return Long.MAX_VALUE;
        }
    }

}
