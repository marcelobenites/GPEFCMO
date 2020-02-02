/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.domain.Zone;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**Classe de persistencia de dados uma uma região 
 * @since beta 1.0
 * @see br.mil.eb.badmapcmo.sistemas.domain.Zone
 * @author tenbenites
 */

@Repository
@Transactional
public class ZoneDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Zone> getAll() {                                      
             
        String jpql = "from Zone z";
        TypedQuery<Zone> query = entityManager.createQuery(jpql, Zone.class);
        //if(query.getResultList().isEmpty())
          //  System.out.println("RETORNO VAZIO");
        //System.out.println("Pegando Zonas!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+query.getResultList().toString());
        return query.getResultList();
    }
    
    /***/
    @Transactional(readOnly = true)
	public Zone getById(Long id) {
		String jpql = "from Zone z where z.id = :id";
		TypedQuery<Zone> query = entityManager.createQuery(jpql, Zone.class);
		query.setParameter("id", id);
		return query.getSingleResult();
    }
   
    //busca uma lista de zonas pelo nome do municipio
    @Transactional(readOnly = true)
    public List<Zone> findZone(String county_name) {
       // String county = zone.getCounty_name();
       // String state = zone.getState_name().getDescription();
        String jpql = "from Zone z where z.county_name like :county_name";
        TypedQuery<Zone> query = entityManager.createQuery(jpql, Zone.class);
        //System.out.println("achou ["+query.toString()+"]!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        query.setParameter("county_name", county_name);
        
        return query.getResultList();
    }
    
    //checa se uma zona já faz parte do banco
    @Transactional(readOnly = true)
    public Zone checkZone(Zone z) {
        String county_name= z.getCounty_name();
        String state_name= z.getState_name().getAbbreviation();
        String jpql = "from Zone z where z.county_name like :county_name and z.state_name like :state_name";
        TypedQuery<Zone> query = entityManager.createQuery(jpql, Zone.class);
        query.setParameter("county_name", county_name);
        query.setParameter("state_name", state_name);
        //System.out.println("achou ["+query.toString()+"]!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try{
            return query.getSingleResult();
        }catch (NoResultException nre){
            return z;
        }
    }
    
    public void save(Zone zone) {
        //System.out.println("Zona a ser persistida:"+zone.toString());
        entityManager.persist(zone);	
    }
    
    public void update(Zone zone) {
            entityManager.merge(zone);		
    }
    
     public void delete(Long id) {
            entityManager.remove(entityManager.getReference(Zone.class, id));		
    }
     /**Retorna todos os municípios pertencentes à area de uma unidade*/
    @Transactional(readOnly = true)
    public Object getByMUId(Long id) {
        String jpql = "from Zone z where z.frontier_militaryUnit.id = :id";
        TypedQuery<Zone> query = entityManager.createQuery(jpql, Zone.class);
        query.setParameter("id", id);        
        return query.getResultList();
    }

    
    
}
