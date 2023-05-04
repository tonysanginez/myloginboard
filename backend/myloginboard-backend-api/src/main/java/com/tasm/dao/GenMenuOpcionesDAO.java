package com.tasm.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.tasm.dto.OpcionesMenuDTO;
import com.tasm.model.gen.GenMenuOpciones;

import lombok.NonNull;

@Service
public class GenMenuOpcionesDAO extends BaseDAO<GenMenuOpciones, Long> {
	 
	 
	protected GenMenuOpcionesDAO() {
		super(GenMenuOpciones.class);
	}

	@PersistenceContext
	private EntityManager em;

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	@Override
	public void persist(GenMenuOpciones t) 
	throws PersistenceException {
		super.persist(t);
	}

	@Override
	public void update(GenMenuOpciones t) 
	throws PersistenceException {
		super.update(t);
	}

	@Override
	public Optional<GenMenuOpciones> find(@NonNull Long id) {
		return super.find(id);
	}
 
	
	@SuppressWarnings("unchecked")
	public List<OpcionesMenuDTO> consultarOpcionesMenuAccesoUsuario(
			Long bgdSecuenciaUsuario, Integer intCodigoEmpresa, Integer intCodigoSucursal) {
		
		StringBuilder stbQuery = new StringBuilder(); 
		stbQuery.append( "select mo.codigoMenu, ");
							stbQuery.append( "	mo.genMenuOpciones.codigoMenu, ");
							stbQuery.append( "	mo.nombre, ");                                                        
							stbQuery.append( "	mo.descripcion, ");
							stbQuery.append( "	mo.esOpcionFinal, ");
							stbQuery.append( "	mo.esSeleccionInicial, ");
							stbQuery.append( "	mo.url, ");
                                                        stbQuery.append( "	mo.nombreCorto ");
							stbQuery.append( "	from GenMenuOpciones mo, ");
							stbQuery.append( "      GenUsuariosXSucursalXRol usr, ");
							stbQuery.append( "      GenRoles r, ");
							stbQuery.append( "      GenMenuOpcionesXRoles mor, ");
							stbQuery.append( "      GenUsuariosXSucursal us ");
							stbQuery.append( " where  us.genUsuarios.secuenciaUsuario = :secuenciaUsuario	 ");
							stbQuery.append( " and	  us.genSucursales.genSucursalesCPK.codigoEmpresa = :codigoEmpresa  ");
							stbQuery.append( " and	  us.estado = 'A' ");
							stbQuery.append( " and	  us.secuenciaUsuXSuc = usr.genUsuariosXSucursal.secuenciaUsuXSuc ");
							stbQuery.append( " and	  usr.estado = 'A' ");
							stbQuery.append( " and	  usr.genRoles.secuenciaRol = r.secuenciaRol ");
							stbQuery.append( " and	  r.genEmpresas.codigoEmpresa = us.genSucursales.genSucursalesCPK.codigoEmpresa ");
							stbQuery.append( " and	  r.estado = 'A' ");
							stbQuery.append( " and	  mor.genMenuOpcionesRolCPK.secuenciaRol = r.secuenciaRol " );
							stbQuery.append( " and    mor.estado = 'A' ");
							stbQuery.append( " and	  mor.genMenuOpcionesRolCPK.codigoMenu = mo.codigoMenu ");
		
 		Query query = null;
		
		if(intCodigoSucursal!=null) {  
			stbQuery.append(  "and  us.genSucursales.genSucursalesCPK.codigoSucursal = :codigoSucursal ");
			query = em.createQuery(stbQuery.toString()); 
			query.setParameter("secuenciaUsuario", bgdSecuenciaUsuario);
			query.setParameter("codigoEmpresa", intCodigoEmpresa.shortValue());
			query.setParameter("codigoSucursal", intCodigoSucursal);
		}else {
			query = em.createQuery(stbQuery.toString()); 
			query.setParameter("secuenciaUsuario", bgdSecuenciaUsuario);
			query.setParameter("codigoEmpresa", intCodigoEmpresa.shortValue());
		}
		
		List<Object[]> lsResult = query.getResultList();
		if (lsResult != null) {
			OpcionesMenuDTO objOpcionMenu = null;
			List<OpcionesMenuDTO> lsOpcionesMenu = new ArrayList<OpcionesMenuDTO>();
			for (Object[] objOpcion : lsResult) {
				objOpcionMenu = new OpcionesMenuDTO();
				objOpcionMenu.setCodigoOpcion(Integer.parseInt(objOpcion[0].toString()));
				objOpcionMenu.setDescripcionOpcion((String) objOpcion[2]); 
                                objOpcionMenu.setNombreCorto((String) objOpcion[7]); 
				objOpcionMenu.setVista((String) objOpcion[6]);
				objOpcionMenu.setCodigoOpcionPadre(objOpcion[1] != null ? Integer.parseInt(objOpcion[1].toString()) : null);
				objOpcionMenu.setEsFinal((String) objOpcion[4]);
				lsOpcionesMenu.add(objOpcionMenu);
			}
			return lsOpcionesMenu;
		}
		return null;
	}
	
}
	
	 