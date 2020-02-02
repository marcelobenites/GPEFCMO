/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.domain.CommandLog;
import br.mil.eb.badmapcmo.sistemas.domain.Commander;
import br.mil.eb.badmapcmo.sistemas.domain.MUInfo;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Registro de comandos pelos quais um militar passou para efeito de histórico.
 * @author tenbenites
 */

@Repository
@Transactional
public class CommandLogDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(CommandLog commandLog) {
		entityManager.persist(commandLog);	
    }
    
    @Transactional(readOnly = true)
    public List<Commander> getAllCmdByMU(Long id) {
            String jpql = "select cl.MU_cmd from CommandLog cl where cl.militaryUnit.id = :id";
            TypedQuery<Commander> query = entityManager.createQuery(jpql, Commander.class);
            query.setParameter("id", id);
            System.out.println("\n\n\n\n\nRetornando com a lista de comandantes"+query.getResultList().toString());
            return query.getResultList();
    }
    
    /**Retorna a lista de comandantes no log de comando para uma unidade de id :id*/
    @Transactional(readOnly = true)
    public List<Commander> getCmdByMU(Long id) {
            String jpql = "select cl.MU_cmd from CommandLog cl where cl.militaryUnit.id = :id and cl.MU_cmd.role='CMD'";
            TypedQuery<Commander> query = entityManager.createQuery(jpql, Commander.class);
            query.setParameter("id", id);
            System.out.println("\n\n\n\n\nRetornando com a lista de comandantes"+query.getResultList().toString());
            return query.getResultList();
    }
    /**Retorna a lista de subcomandantes no log de comando para uma unidade de id :id*/
    @Transactional(readOnly = true)
    public List<Commander> getSCmdByMU(Long id) {
            String jpql = "select cl.MU_cmd from CommandLog cl where cl.militaryUnit.id = :id and cl.MU_cmd.role='SCMD'";
            TypedQuery<Commander> query = entityManager.createQuery(jpql, Commander.class);
            query.setParameter("id", id);
            System.out.println("\n\n\n\n\nRetornando com a lista de comandantes"+query.getResultList().toString());
            return query.getResultList();
    }
    
    /**Remove todas os registros de comando para um militar de id :id*/
    public Boolean  removeCommandLogForMilitary(Long id){
        CommandLog cl;
        String jpql = "from CommandLog cl where cl.MU_cmd.id = :id";
        TypedQuery<CommandLog> query = entityManager.createQuery(jpql, CommandLog.class);
        query.setParameter("id", id);
        try{
            cl =  query.getSingleResult();
        }catch (NoResultException nre){
            return false;
        }
        entityManager.remove(entityManager.getReference(CommandLog.class, cl.getId()));	
        return true;
    }
    
    
}
