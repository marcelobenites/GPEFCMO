/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.domain.MURegionMap;
import br.mil.eb.badmapcmo.sistemas.domain.Military;
import br.mil.eb.badmapcmo.sistemas.domain.Zone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repositório de delimitação de região abrangida pela unidade militar.
 * @author tenbenites
 */
@Repository
@Transactional
public class RegionMapDao {
    @PersistenceContext
    private EntityManager entityManager;
    
    public void saveMURegion(MURegionMap regionMap) {
        
        entityManager.persist(regionMap);
        System.out.println("\n\n\n\n Salvou item de região no banco\n\n\n\n");	
    }
    
    public void deleteZoneRegistry(Long zone_id, Long militaryUnit_id) {
        System.out.println("\n\n\n\n\n\n\n\n REMOVEndo REGISTRO DE ZONA ID"+zone_id);
        Long id = this.getRegionByMUandZoneId(zone_id, militaryUnit_id).getId();
	entityManager.remove(entityManager.getReference(MURegionMap.class, id));

    }
    
    @Transactional(readOnly = true)
    public List<MURegionMap> getAll() { 
        String jpql = "from MURegionMap rm";
        TypedQuery<MURegionMap> query = entityManager.createQuery(jpql, MURegionMap.class);
        return query.getResultList();
    }
    
    
    /**Recupera um único registro para um par de ids zona : unidadeMilitar*/
    @Transactional(readOnly = true)
    public MURegionMap getRegionByMUandZoneId(Long zone_id, Long militaryUnit_id){
        String jpql = "from MURegionMap rm where rm.militaryUnit.id =:militaryUnit_id and rm.zone.id =:zone_id";
	TypedQuery<MURegionMap> query = entityManager.createQuery(jpql, MURegionMap.class);
	query.setParameter("zone_id", zone_id);
	query.setParameter("militaryUnit_id", militaryUnit_id);
	try{
            return query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }   
    
    
    }
    
    /**Recupera todas as zonas de uma região abrangida por uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<Zone> getZonesByMUId (Long id){
        System.out.println("\n\n\n\n\n\n\n\n DAO - Buscando municípios da região da unidade id:"+id);
	String jpql = "select rm.zone from MURegionMap rm where rm.militaryUnit.id =:id";
	TypedQuery<Zone> query = entityManager.createQuery(jpql, Zone.class);
	query.setParameter("id", id);
	return query.getResultList();
        
    
    
    }

    public List<MURegionMap> getRegionByMUId(Long id) {
        System.out.println("\n\n\n\n\n\n\n\n DAO - Buscando municípios da região da unidade id:"+id);
	String jpql = "from MURegionMap rm where rm.militaryUnit.id = :id";
	TypedQuery<MURegionMap> query = entityManager.createQuery(jpql, MURegionMap.class);
	query.setParameter("id", id);
	System.out.println("\n\n\n\n\n\n\n\n DAO - retornando lista:"+query.getResultList().toString());
	return query.getResultList();
    }
}
