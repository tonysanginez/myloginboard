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

import com.tasm.dto.ProveedoresDTO;
import com.tasm.model.gen.GenProveedores;
@Service
public class GenProveedoresDAO extends BaseDAO<GenProveedores, Long>{
	
	@PersistenceContext
    private EntityManager em;

    protected GenProveedoresDAO() {
        super(GenProveedores.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public void persist(GenProveedores t) throws PersistenceException {
        super.persist(t);
    }
    
    public Long nextProveedores() {
        try {
            StringBuilder stbQuery = new StringBuilder();
            stbQuery.append("SELECT max(p.codigoInstitucion) FROM GenProveedores p");
            Query query = em.createQuery(stbQuery.toString());
            Long codigo= query.getSingleResult()==null?(long) 0:(Long) query.getSingleResult();
            return codigo + 1;
        } catch (NoResultException e) {
            return Long.MAX_VALUE;
        }
    }
	

	@SuppressWarnings("unchecked")
	public Map<String, Object> obtenerProveedores(Long codigoInstitucion, String numeroIdentificacion,
			String nombreInstitucion, String nombreComercial, String direccion, String correoElectronico,
			String telefonoMovil, String nombrePersonaContacto, Short codigoEmpresa, Integer page, Integer perPage,
			String filtroGeneral, String estado) {
		
        Map<String, Object> resultado = new HashMap<>();
        StringBuilder strQuery = new StringBuilder();
        try {
            //instituciones
            strQuery.append("SELECT   i.codigo_institucion as codigoInstitucion, ");
            strQuery.append("         i.codigo_empresa as codigoEmpresa, ");
            strQuery.append("         i.codigo_detalle_actividad as codigoDetalleActividad, ");
            strQuery.append("         i.codigo_tipo_identificacion as codigoTipoIdentificacion, ");
            strQuery.append("         i.numero_identificacion as numeroIdentificacion, ");
            strQuery.append("         i.nombre_institucion as nombreInstitucion , ");
            strQuery.append("         i.nombre_comercial as nombreComercial, ");
            strQuery.append("         i.direccion as direccion, ");
            strQuery.append("         i.pagina_web as paginaWeb, ");
            strQuery.append("         i.correo_electronico as correoElectronico, ");
            strQuery.append("         i.codigo_pais_tel_movil as codigoPaisTelMovil, ");
            strQuery.append("         i.telefono_movil as telefonoMovil, ");
            strQuery.append("         i.telefono_movil_e164 as telefonoMovilE164, ");
            strQuery.append("         i.codigo_pais_tel_fijo as codigoPaisTelFijo, ");
            strQuery.append("         i.telefono_fijo as telefonoFijo, ");
            strQuery.append("         i.telefono_fijo_e164 as telefonoFijoE164, ");
            strQuery.append("         i.nombre_persona_contacto as nombrePersonaContacto, ");
            strQuery.append("         i.codigo_pais_tel_movil_contacto as codigoPaisTelMovilContacto, ");
            strQuery.append("         i.telefono_movil_contacto as telefonoMovilContacto, ");
            strQuery.append("         i.telefono_movil_contacto_e164 as telefonoMovilContactoE164, ");
            strQuery.append("         i.nombre_representante_legal as nombreRepresentanteLegal, ");
            strQuery.append("         i.nombre_presidente as nombrePresidente, ");
            strQuery.append("         i.nombre_gerente as nombreGerente, ");
            strQuery.append("         i.registro_sanitario as registroSanitario, ");
            strQuery.append("         i.tipo_persona as tipoPersona, ");
            strQuery.append("         i.es_contribuyente_especial as esContribuyenteEspecial, ");
            strQuery.append("         i.es_gubernamental as esGubernamental, ");
            strQuery.append("         i.es_contribuyente_rise as esContribuyenterise, ");
            strQuery.append("         i.lleva_contabilidad as llevaContabilidad, ");
            strQuery.append("         i.es_extranjero as esExtranjero, ");
            strQuery.append("         i.es_proveedor as esProveedor, ");
            strQuery.append("         i.fecha_ingreso as fechaIngreso,");
            //proveedores
            strQuery.append("         p.codigo_forma_pago as codigoFormasPago,");
            strQuery.append("         p.plazo_otorgado as plazoOtorgado,");
            strQuery.append("         p.direccion_retiro as direccionRetiro,");
            strQuery.append("         p.nombre_contacto_retiro as nombreContactoRetiro,");
            strQuery.append("         p.estado as estado, ");
            //formadepago
            strQuery.append("         fp.nombre_forma_pago as nombreFormaPago,");
            //tipos_identificacion
            strQuery.append("         ti.nombre_tipo_identificacion as nombreTipoIdentificacion,");
            //detalles_actividad
            strQuery.append("         da.nombre_detalle_actividad as nombreDetalleActividad,");
            //tipos actividad
            strQuery.append("         da.codigo_tipo_actividad as codigoTipoActividad,");
            strQuery.append("         ta.nombre_tipo_actividad as nombreTipoActividad");
            strQuery.append(" FROM    gen_instituciones i JOIN gen_proveedores p");
            strQuery.append("         ON i.codigo_institucion = p.codigo_institucion");
            strQuery.append("         LEFT JOIN gen_detalles_actividad da");
            strQuery.append("         ON i.codigo_detalle_actividad = da.codigo_detalle_actividad ");
            strQuery.append("         LEFT JOIN gen_tipos_identificacion ti");
            strQuery.append("         ON i.codigo_tipo_identificacion = ti.codigo_tipo_identificacion ");
            strQuery.append("         LEFT JOIN gen_formas_pago fp");
            strQuery.append("         ON p.codigo_forma_pago = fp.codigo_forma_pago");
            strQuery.append("         LEFT JOIN gen_tipos_actividad ta");
            strQuery.append("         ON da.codigo_tipo_actividad = ta.codigo_tipo_actividad ");
            strQuery.append(" WHERE   p.estado = :estado ");
            strQuery.append(" AND     i.codigo_empresa = :codigoEmpresa ");
            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                strQuery.append(" AND     (i.numero_identificacion LIKE :filtroGeneral ");
                strQuery.append(" OR      i.nombre_institucion LIKE :filtroGeneral ");
                strQuery.append(" OR      i.nombre_comercial LIKE :filtroGeneral ");
                strQuery.append(" OR      i.direccion LIKE :filtroGeneral ");
                strQuery.append(" OR      i.correo_electronico LIKE :filtroGeneral ");
                strQuery.append(" OR      i.telefono_movil LIKE :filtroGeneral ");
                strQuery.append(" OR      i.nombre_persona_contacto LIKE :filtroGeneral) ");
            }
            
            if (!ObjectUtils.isEmpty(codigoInstitucion)) {
                strQuery.append(" AND     i.codigo_institucion = :codigoInstitucion ");
            }
            if (!ObjectUtils.isEmpty(numeroIdentificacion)) {
                strQuery.append(" AND     i.numero_identificacion = :numeroIdentificacion ");
            }
            if (!ObjectUtils.isEmpty(nombreInstitucion)) {
                strQuery.append(" AND     i.nombre_institucion LIKE :nombreInstitucion ");
            }
            if (!ObjectUtils.isEmpty(nombreComercial)) {
                strQuery.append(" AND     i.nombre_comercial LIKE :nombreComercial ");
            }
            if (!ObjectUtils.isEmpty(direccion)) {
                strQuery.append(" AND     i.direccion LIKE :direccion ");
            }
            if (!ObjectUtils.isEmpty(correoElectronico)) {
                strQuery.append(" AND     i.correo_electronico LIKE :correoElectronico ");
            }
            if (!ObjectUtils.isEmpty(telefonoMovil)) {
                strQuery.append(" AND     i.telefono_movil LIKE :telefonoMovil ");
            }
            if (!ObjectUtils.isEmpty(nombrePersonaContacto)) {
                strQuery.append(" AND     i.nombre_persona_contacto LIKE :nombrePersonaContacto ");
            }
            
            TypedQuery<Tuple> query = (TypedQuery<Tuple>) em.createNativeQuery(strQuery.toString(), Tuple.class);            
            query.setParameter("estado", estado);
            query.setParameter("codigoEmpresa", codigoEmpresa);
            //filtros
            if (!ObjectUtils.isEmpty(filtroGeneral)) {
                query.setParameter("filtroGeneral", "%" + filtroGeneral + "%");
            }
            if (!ObjectUtils.isEmpty(codigoInstitucion)) {
                query.setParameter("codigoInstitucion", codigoInstitucion);
            }
            if (!ObjectUtils.isEmpty(numeroIdentificacion)) {
                query.setParameter("numeroIdentificacion",numeroIdentificacion);
            }
            if (!ObjectUtils.isEmpty(nombreInstitucion)) {
                query.setParameter("nombreInstitucion", "%"+nombreInstitucion+"%");
            }
            if (!ObjectUtils.isEmpty(nombreComercial)) {
                query.setParameter("nombreComercial", "%" + nombreComercial + "%");
            }
            if (!ObjectUtils.isEmpty(direccion)) {
                query.setParameter("direccion", "%" + direccion+ "%");
            }
            if (!ObjectUtils.isEmpty(correoElectronico)) {
                query.setParameter("correoElectronico", "%" + correoElectronico + "%");
            }
            if (!ObjectUtils.isEmpty(telefonoMovil)) {
                query.setParameter("telefonoMovil", "%" + telefonoMovil+"%");
            }
            if (!ObjectUtils.isEmpty(nombrePersonaContacto)) {
                query.setParameter("nombrePersonaContacto", "%" + nombrePersonaContacto + "%");
            }

            Integer totalProveedores = query.getResultList().size();
            
            query.setFirstResult((page * perPage) - perPage);
            query.setMaxResults(perPage);
            List<Tuple> lsResult = query.getResultList();
            List<ProveedoresDTO> lsProveedores = new ArrayList<>();
       
            if (lsResult != null) {
            	ProveedoresDTO objProveedoresDTO = null;
                for (Tuple tuple : lsResult){
                	objProveedoresDTO = ProveedoresDTO.builder()
                			.codigoInstitucion(tuple.get("codigoInstitucion", Number.class).longValue())
                			.codigoEmpresa(tuple.get("codigoEmpresa", Number.class).intValue())
                			.codigoDetalleActividad(tuple.get("codigoDetalleActividad", Number.class).intValue())
                			.codigoTipoIdentificacion(tuple.get("codigoTipoIdentificacion", Number.class).intValue())
                			.numeroIdentificacion(tuple.get("numeroIdentificacion", String.class))
                			.nombreInstitucion(tuple.get("nombreInstitucion", String.class))
                			.nombreComercial(tuple.get("nombreComercial", String.class))
                			.direccion(tuple.get("direccion", String.class))
                			.paginaWeb(tuple.get("paginaWeb", String.class))
                			.correoElectronico(tuple.get("correoElectronico", String.class))
                			.codigoPaisTelMovil(tuple.get("codigoPaisTelMovil") != null ? tuple.get("codigoPaisTelMovil", Number.class).intValue() : null)
                			.telefonoMovil(tuple.get("telefonoMovil", String.class))
                			.telefonoMovilE164(tuple.get("telefonoMovilE164", String.class))
                			.codigoPaisTelFijo(tuple.get("codigoPaisTelFijo") != null ? tuple.get("codigoPaisTelFijo", Number.class).intValue() : null)
                			.telefonoFijo(tuple.get("telefonoFijo", String.class))
                			.telefonoFijoE164(tuple.get("telefonoFijoE164", String.class))
                			.nombrePersonaContacto(tuple.get("nombrePersonaContacto", String.class))		
                			.codigoPaisTelMovilContacto(tuple.get("codigoPaisTelMovilContacto") != null ? tuple.get("codigoPaisTelMovilContacto", Number.class).intValue() : null)
                			.telefonoMovilContacto(tuple.get("telefonoMovilContacto", String.class))
                			.telefonoMovilContactoE164(tuple.get("telefonoMovilContactoE164", String.class))
                			.nombreRepresentanteLegal(tuple.get("nombreRepresentanteLegal", String.class))
                			.nombrePresidente(tuple.get("nombrePresidente", String.class))
                			.nombreGerente(tuple.get("nombreGerente", String.class))
                			.registroSanitario(tuple.get("registroSanitario", String.class))
                			.tipoPersona(tuple.get("tipoPersona", String.class))
                			.esContribuyenteEspecial(tuple.get("esContribuyenteEspecial", String.class))
                			.esGubernamental(tuple.get("esGubernamental", String.class))
                			.esContribuyenterise(tuple.get("esContribuyenterise", String.class))
                			.llevaContabilidad(tuple.get("llevaContabilidad", String.class))
                			.esExtranjero(tuple.get("esExtranjero", String.class))
                			.esProveedor(tuple.get("esProveedor", String.class))
                			.fechaIngreso(tuple.get("fechaIngreso", Date.class).toString())
                			//proveedor
                			.codigoFormasPago(tuple.get("codigoFormasPago") != null ? tuple.get("codigoFormasPago", Number.class).intValue() : null)
                			.plazoOtorgado(tuple.get("plazoOtorgado", String.class))
                			.direccionRetiro(tuple.get("direccionRetiro", String.class))
                			.nombreContactoRetiro(tuple.get("nombreContactoRetiro", String.class))	
                			.estado(tuple.get("estado", String.class))
                			//formadepago
                			.nombreFormaPago(tuple.get("nombreFormaPago", String.class))
                			//tipo_identificacion
                			.nombreTipoIdentificacion(tuple.get("nombreTipoIdentificacion", String.class))
                			//detalle_actividad
                			.nombreDetalleActividad(tuple.get("nombreDetalleActividad", String.class))
                			//tipo_actividad
                			.codigoTipoActividad(tuple.get("codigoTipoActividad") != null ? tuple.get("codigoTipoActividad", Number.class).intValue() : null)
                			.nombreTipoActividad(tuple.get("nombreTipoActividad", String.class))
                			.build();
                    lsProveedores.add(objProveedoresDTO);
                }
            }
            resultado.put("Proveedores", lsProveedores);
            resultado.put("totalItems", totalProveedores);
            resultado.put("itemsPerPage", perPage);
            resultado.put("totalPages", (int) Math.ceil(totalProveedores.doubleValue() / perPage.doubleValue()));
            return resultado;
        } catch (NoResultException e) {
            return null;
        }
        

	}

    
    
}
