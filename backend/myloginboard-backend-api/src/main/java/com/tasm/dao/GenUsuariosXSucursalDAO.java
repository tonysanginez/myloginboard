package com.tasm.dao;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.stereotype.Service;

import com.tasm.dto.OrganizacionesDTO;
import com.tasm.dto.SucursalesAccesoUsuarioDTO;
import com.tasm.model.gen.GenUsuariosXSucursal;

import lombok.NonNull;

@Service
public class GenUsuariosXSucursalDAO extends BaseDAO<GenUsuariosXSucursal, BigDecimal> {

	@PersistenceContext
	private EntityManager em;

	protected GenUsuariosXSucursalDAO() {
		super(GenUsuariosXSucursal.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
	@Override
	public void persist(GenUsuariosXSucursal t) throws PersistenceException {
		super.persist(t);
	}

	@Override
	public void update(GenUsuariosXSucursal t) throws PersistenceException {
		super.update(t);
	}

	@Override
	public Optional<GenUsuariosXSucursal> find(@NonNull BigDecimal id) {
		return super.find(id);
	}

	/**
	 * Consulta las sucursales a las que tiene acceso un usuario. 
	 */
	public List<SucursalesAccesoUsuarioDTO> consultarSucursalesXUsuario(Long secuenciaUsuario) {
		return em.createQuery(
			"SELECT u.secuenciaUsuario AS secuenciaUsuario,\n"
			+ "su.genSucursalesCPK.codigoEmpresa AS codigoEmpresa,\n" 
			+ "su.genSucursalesCPK.codigoSucursal AS codigoSucursal,\n"
			+ "su.nombreSucursal AS nombreSucursal\n"
			+ "FROM GenUsuarios u, GenUsuariosXSucursal us, GenSucursales su\n"
			+ "WHERE u.secuenciaUsuario = :secuenciaUsuario\n"
			+ "AND us.genUsuarios = u\n"
			+ "AND us.estado = 'A'\n"
			+ "AND su.genSucursalesCPK = us.genSucursales.genSucursalesCPK\n"
			+ "AND su.estado = 'A'", Tuple.class)
		.setParameter("secuenciaUsuario", secuenciaUsuario)
		.getResultList()
		.stream()
		.map(tuple -> {return SucursalesAccesoUsuarioDTO.builder()
						.secuenciaUsuario(new BigDecimal(tuple.get("secuenciaUsuario", Number.class).toString()))
						.codigoEmpresa(tuple.get("codigoEmpresa", Number.class).intValue())
						.codigoSucursal(tuple.get("codigoSucursal", Number.class).intValue())
						.nombreSucursal(tuple.get("nombreSucursal", String.class)).build();})
		.collect(Collectors.toList());
	}
	
	 

	@SuppressWarnings("unchecked")
	public List<BigDecimal> consultarSecuenciaUsuarioXSucursal(BigDecimal bgdSecuenciaUsuario) {
		StringBuilder strSQL = new StringBuilder();
		strSQL.append("SELECT us.secuenciaUsuXSuc");
		strSQL.append(" FROM GenUsuarios u, GenUsuariosXSucursal us, GenSucursales su");
		strSQL.append(" WHERE u.secuenciaUsuario = :secuenciaUsuario");
		strSQL.append(" AND us.GenUsuarios = u");
		strSQL.append(" AND su.GenSucursalesCPK = us.GenSucursales.GenSucursalesCPK");
		Query query = em.createQuery(strSQL.toString());
		query.setParameter("secuenciaUsuario", bgdSecuenciaUsuario);
		List<BigDecimal> lsResult = query.getResultList();
		if (lsResult != null && !lsResult.isEmpty()) {
			return lsResult;
		}
		return null;
	}
	
 
	public GenUsuariosXSucursal findUsuarioXSucursal(BigDecimal bgdSecuenciaUsuario, Integer intCodigoSucursal, Short shoCodigoEmpresa) {
		try {
			return em.createQuery(
					"SELECT us\n" + 
					"FROM GenUsuariosXSucursal us\n" + 
					"WHERE us.GenUsuarios.secuenciaUsuario = :secuenciaUsuario\n" + 
					"AND us.GenSucursales.GenSucursalesCPK.codigoSucursal = :codigoSucursal\n" + 
					"AND us.GenSucursales.GenSucursalesCPK.codigoEmpresa = :codigoEmpresa", GenUsuariosXSucursal.class)
					.setParameter("secuenciaUsuario", bgdSecuenciaUsuario)
					.setParameter("codigoSucursal", intCodigoSucursal)
					.setParameter("codigoEmpresa", shoCodigoEmpresa)
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	 
	
	public List<GenUsuariosXSucursal> consultarXUsuarioNotInListaSucursales(
			BigDecimal bgdSecuenciaUsuario, Set<Integer> lsSucursales, Short shoCodigoEmpresa) {
		String strSucursales = "";
		for (Integer intSucursal : lsSucursales) {
			strSucursales = strSucursales + (!strSucursales.equals("")? ","+intSucursal : intSucursal);
		}
		return em.createQuery(
				"SELECT us\n" + 
				"FROM GenUsuariosXSucursal us\n" + 
				"WHERE us.GenUsuarios.secuenciaUsuario = :secuenciaUsuario\n" +
				"AND us.GenSucursales.GenSucursalesCPK.codigoEmpresa = :codigoEmpresa\n" +
				"AND us.GenSucursales.GenSucursalesCPK.codigoSucursal NOT IN (" + strSucursales + ")\n" +
				"AND us.estado = 'A'", GenUsuariosXSucursal.class)
				.setParameter("secuenciaUsuario", bgdSecuenciaUsuario)
				.setParameter("codigoEmpresa", shoCodigoEmpresa)
				.getResultList();
	}
	/**
	 * Consulta las empresas que existen TS 09/03/2022
	 */
	public List<OrganizacionesDTO> consultarOrganizaciones() {
		return em.createQuery(
			"SELECT e.codigoEmpresa AS codigoEmpresa,\n" 
			+ "e.nombreEmpresa AS nombreEmpresa,\n"
			+ "e.estado AS estado\n"
			+ "FROM GenEmpresas e\n"
			+ "WHERE e.estado = 'A'\n", Tuple.class)
		.getResultList()
		.stream()
		.map(tuple -> {return OrganizacionesDTO.builder()
						.codigoEmpresa(tuple.get("codigoEmpresa", Number.class).intValue())
						.nombreEmpresa(tuple.get("nombreEmpresa", String.class))
						.estado(tuple.get("estado", String.class))
						.identificadorLoginUrl("AliFresh").build();})
		.collect(Collectors.toList());
	}
}
