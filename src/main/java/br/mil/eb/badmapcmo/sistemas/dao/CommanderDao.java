/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.domain.Commander;
import br.mil.eb.badmapcmo.sistemas.domain.Military;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tenbenites
 */

@Repository
@Transactional
public class CommanderDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(Commander commander) {
        
	       System.out.println("\n\n\n\n\n\n\n ENTROU NA ROTINA DE SALVAMENTO");
        entityManager.persist(commander);
        
    }
    
    public void delete(Long id) {
	entityManager.remove(entityManager.getReference(Commander.class, id));		

    }
    
    public void update(Commander commander) {
        entityManager.merge(commander);		
    }
        
    @Transactional(readOnly = true)
    public Commander getById(Long id) {
        String jpql = "from Commander c where c.id = :id";
        TypedQuery<Commander> query = entityManager.createQuery(jpql, Commander.class);
        query.setParameter("id", id);
        try{
            return query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }
    }
        
    @Transactional(readOnly = true)
    public List<Commander> getAll() {                                      
             
        String jpql = "from Commander c";
        TypedQuery<Commander> query = entityManager.createQuery(jpql, Commander.class);
        return query.getResultList();
    }
}
