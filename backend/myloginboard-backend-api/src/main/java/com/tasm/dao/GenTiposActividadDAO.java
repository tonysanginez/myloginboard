package com.tasm.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Service;

import com.tasm.model.gen.GenTiposActividad;
@Service
public class GenTiposActividadDAO extends BaseDAO<GenTiposActividad, Long>{

	@PersistenceContext
    private EntityManager em;

    protected GenTiposActividadDAO() {
        super(GenTiposActividad.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void persist(GenTiposActividad t) throws PersistenceException {
        super.persist(t);
    }

}
