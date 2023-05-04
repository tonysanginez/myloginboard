package com.tasm.dao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.tasm.exceptions.BOException;
import com.tasm.helper.FormatoEmailHelper;
import com.tasm.model.gen.GenUsuarios;

import lombok.NonNull;

@Service
public class GenUsuariosDAO extends BaseDAO<GenUsuarios, Long> {
	
	protected GenUsuariosDAO() {
		super(GenUsuarios.class);
	}

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void persist(GenUsuarios t) 
	throws PersistenceException {
		super.persist(t);
	}

	@Override
	public void update(GenUsuarios t) 
	throws PersistenceException {
		super.update(t);
	}

	@Override
	public Optional<GenUsuarios> find(@NonNull Long id) {
		return super.find(id);
	}

	public GenUsuarios validarUsuarioActivo(String strUsuario) 
	throws BOException {
		try {
			StringBuilder stbQuery = new StringBuilder();
			stbQuery.append("select u ");
			stbQuery.append("  from GenUsuarios u ");
			stbQuery.append(" where (u.codigoUsuario = :usuario or u.email = :usuario) ");
			
			Query query = em.createQuery(stbQuery.toString());
			query.setParameter("usuario", FormatoEmailHelper.emailValido(strUsuario) ? strUsuario.toLowerCase() : strUsuario.toUpperCase());
			
			return (GenUsuarios) query.getSingleResult();
			
		} catch (NoResultException e) {
			return null;
		}
	}
	
}