package com.tasm.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.tasm.exceptions.BOException;
import com.tasm.model.gen.GenParametrosGenerales;

import lombok.NonNull;

@Service
public class GenParametrosGeneralesDAO extends BaseDAO<GenParametrosGenerales, Long> {
	
	protected GenParametrosGeneralesDAO() {
		super(GenParametrosGenerales.class);
	}

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void persist(GenParametrosGenerales t) 
	throws PersistenceException {
		super.persist(t);
	}

	@Override
	public void update(GenParametrosGenerales t) 
	throws PersistenceException {
		super.update(t);
	}

	@Override
	public Optional<GenParametrosGenerales> find(@NonNull Long id) {
		return super.find(id);
	}

	public GenParametrosGenerales obtenerParametro(Integer codigoEmpresa, String nemonico) 
	throws BOException {
		try {
			StringBuilder stbQuery = new StringBuilder();
			stbQuery.append("select u ");
			stbQuery.append("  from GenParametrosGenerales u ");
			stbQuery.append(" where u.genParametrosGeneralesCPK.codigoEmpresa = :codigoEmpresa  ");
			stbQuery.append(" and u.genParametrosGeneralesCPK.nemonico = :nemonico ");
			
			Query query = em.createQuery(stbQuery.toString());
			query.setParameter("codigoEmpresa", codigoEmpresa.shortValue());
			query.setParameter("nemonico", nemonico);
			
			return (GenParametrosGenerales) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
}