package com.tasm.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

import org.springframework.stereotype.Service;

import com.tasm.model.gen.GenDetallesActividad;
@Service
public class GenDetallesActividadDAO extends BaseDAO<GenDetallesActividad,Long>{

	@PersistenceContext
	private EntityManager em;
	
	protected GenDetallesActividadDAO() {
		super(GenDetallesActividad.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
    public void persist(GenDetallesActividad t) throws PersistenceException {
        super.persist(t);
    }

}
