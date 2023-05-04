package com.tasm.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import org.springframework.stereotype.Service;

import com.tasm.model.gen.GenPaises;

@Service
public class GenPaisesDAO extends BaseDAO<GenPaises, Integer> {
	@PersistenceContext
	 private EntityManager em;
	 
	 protected GenPaisesDAO() {
	        super(GenPaises.class);
	    }

	    @Override
	    protected EntityManager getEntityManager() {
	        return em;
	    }

	    @Override
	    public void persist(GenPaises t) throws PersistenceException {
	        super.persist(t);
	    }
}
