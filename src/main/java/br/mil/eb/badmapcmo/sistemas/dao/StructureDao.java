/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.StructureT2;
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.StructuralItem;
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.StructureT1;
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.StructureT3;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repositório de operações sobre itens de estrutura.
 * @author tenbenites
 */
@Repository
@Transactional
public class StructureDao {
    //Métodos e declarações gerais
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<StructuralItem> getAllByMUId(Long id) {
        String jpql = "from StructuralItem st where st.mu_owner.id = :id";
	TypedQuery<StructuralItem> query = entityManager.createQuery(jpql, StructuralItem.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    
    //Métodos e declarações separados por TIPO 
    
    //TIPO 1 - PNR
     /**Recupera itens de suprimento da classe 1 para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<StructureT1> getST1ByMUId(Long id) {
        String jpql = "from StructureT1 st1 where st1.mu_owner.id = :id";
	TypedQuery<StructureT1> query = entityManager.createQuery(jpql, StructureT1.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
     /**Recupera PNR de id:id*/
    @Transactional(readOnly = true)
    public StructureT1 getST1ById(Long id) {
        String jpql = "from StructureT1 st1 where st1.id = :id";
	TypedQuery<StructureT1> query = entityManager.createQuery(jpql, StructureT1.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }         
    }
    
    public void saveST1(StructureT1 structureT1) {
	entityManager.persist(structureT1);	
    }

    public void updateST1(StructureT1 structureT1) {
	entityManager.merge(structureT1);		
    }

    public void deleteST1(Long id) {
	entityManager.remove(entityManager.getReference(StructureT1.class, id));		
    }
    
    
    //TIPO 2 - Oficina
     /**Recupera itens de suprimento da classe 1 para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<StructureT2> getST2ByMUId(Long id) {
        String jpql = "from StructureT2 st2 where st2.mu_owner.id = :id and st2.structureType = 'TYPE2'";
	TypedQuery<StructureT2> query = entityManager.createQuery(jpql, StructureT2.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
     /**Recupera a oficina de id:id*/
    @Transactional(readOnly = true)
    public StructureT2 getST2ById(Long id) {
        String jpql = "from StructureT2 st2 where st2.id = :id and st2.structureType = 'TYPE2'";
	TypedQuery<StructureT2> query = entityManager.createQuery(jpql, StructureT2.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }         
    }
    
    public void saveST2(StructureT2 structureT2) {
	entityManager.persist(structureT2);	
    }

    public void updateST2(StructureT2 structureT2) {
	entityManager.merge(structureT2);		
    }

    public void deleteST2(Long id) {
	entityManager.remove(entityManager.getReference(StructureT2.class, id));		
    }
    
    //TIPO 3 - sala de armas 
     /**Recupera as salas de armas para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<StructureT3> getST3ByMUId(Long id) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n CHEGANI NA CONSULTA");
        String jpql = "from StructureT3 st3 where st3.mu_owner.id = :id";
	TypedQuery<StructureT3> query = entityManager.createQuery(jpql, StructureT3.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
     /**Recupera as salas de armas de id:id*/
    @Transactional(readOnly = true)
    public StructureT3 getST3ById(Long id) {
        String jpql = "from StructureT3 st3 where st3.id = :id";
	TypedQuery<StructureT3> query = entityManager.createQuery(jpql, StructureT3.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }         
    }
   
    public void saveST3(StructureT3 structureT3) {
	       System.out.println("\n\n\n\n\n\n\n\nSALVOU T3");
        entityManager.persist(structureT3);	
    }

    public void updateST3(StructureT3 structureT3) {
	entityManager.merge(structureT3);		
    }

    public void deleteST3(Long id) {
        entityManager.remove(entityManager.getReference(StructureT3.class, id));		
    }
    
    //TIPO 4 - estocagem
    /**Recupera as salas de armas para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<StructuralItem> getST4ByMUId(Long id) {
        String jpql = "from StructuralItem st4 where st4.mu_owner.id = :id and st4.structureType = 'TYPE4'";
	TypedQuery<StructuralItem> query = entityManager.createQuery(jpql, StructuralItem.class);
	query.setParameter("id", id);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n VOLTANDO COM STR:"+query.toString());
	return query.getResultList();        
    }
     /**Recupera estrutura genérica de estocagem de id:id*/
    @Transactional(readOnly = true)
    public StructuralItem getST4ById(Long id) {
        String jpql = "from StructuralItem st4 where st4.id = :id and st4.structureType = 'TYPE4'";
	TypedQuery<StructuralItem> query = entityManager.createQuery(jpql, StructuralItem.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }         
    }
    
    
    public void saveST4(StructuralItem structureT4) {
	entityManager.persist(structureT4);	
    }

    public void updateST4(StructuralItem structureT4) {
	entityManager.merge(structureT4);		
    }

    public void deleteST4(Long id) {
	entityManager.remove(entityManager.getReference(StructuralItem.class, id));		
    }
    
    
  
    
}
