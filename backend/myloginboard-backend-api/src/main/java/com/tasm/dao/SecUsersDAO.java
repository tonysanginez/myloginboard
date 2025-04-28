package com.tasm.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.tasm.exceptions.BOException;
import com.tasm.model.sec.SecUsers;

import lombok.NonNull;

@Service
public class SecUsersDAO extends BaseDAO<SecUsers, Long> {
	
	protected SecUsersDAO() {
		super(SecUsers.class);
	}

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void persist(SecUsers t) 
	throws PersistenceException {
		super.persist(t);
	}

	@Override
	public void update(SecUsers t) 
	throws PersistenceException {
		super.update(t);
	}

	@Override
	public Optional<SecUsers> find(@NonNull Long id) {
		return super.find(id);
	}

	public SecUsers validarUsuarioActivo(String strUsuario) 
	throws BOException {
		try {
			StringBuilder stbQuery = new StringBuilder();
			stbQuery.append("select u ");
			stbQuery.append("  from SecUsers u ");
			stbQuery.append(" where u.username = :username ");
			
			Query query = em.createQuery(stbQuery.toString());
			query.setParameter("username", strUsuario);
			
			return (SecUsers) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
}