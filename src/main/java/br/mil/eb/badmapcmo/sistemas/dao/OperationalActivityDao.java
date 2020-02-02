/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.domain.Military;
import br.mil.eb.badmapcmo.sistemas.domain.operations.OperationForceMap;
import br.mil.eb.badmapcmo.sistemas.domain.operations.OperationalActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe de repositório que controla transações sobre atividades operacionais.
 * @author tenbenites
 * @since beta 1.0
 * @version 1.0
 */
@Repository
@Transactional
public class OperationalActivityDao {
    @PersistenceContext
    private EntityManager entityManager;
    
    
    //GETTERS
    
    /**Retorna a lista de atividades operacionais que possuem localização (latitude,longitude) cadastrada*/
    @Transactional(readOnly = true)
    public  List<OperationalActivity> getAllMappedOA() {
        String jpql = "from OperationalActivity oa where oa.latitude IS NOT NULL";
        TypedQuery<OperationalActivity> query = entityManager.createQuery(jpql, OperationalActivity.class);
        System.out.println("\n\n\n\n\n\n\n\n\n\n BUSCOU OPERAÇÕES CADASTRADAS");
        return query.getResultList();
    }
    
    /**Retorna a lista de atividades operacionais para uma unidade de id :id*/
    @Transactional(readOnly = true)
    public OperationalActivity getById(Long id) {
        String jpql = "from OperationalActivity oa where oa.id = :id";
        TypedQuery<OperationalActivity> query = entityManager.createQuery(jpql, OperationalActivity.class);
        query.setParameter("id", id);
        try{
            return query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }
    }
    
    /**Retorna a lista de atividades operacionais para uma unidade de id :id*/
    @Transactional(readOnly = true)
    public List<OperationalActivity> getByMUId(Long id) {
        String jpql = "from OperationalActivity oa where oa.military_unit.id = :id";
        TypedQuery<OperationalActivity> query = entityManager.createQuery(jpql, OperationalActivity.class);
        query.setParameter("id", id);
        System.out.println("ENCONTRADO:"+query.getResultList().toString());
        return query.getResultList();
    }
    
    
    /**Retorna a lista de atividades operacionais em ordem alfabética para uma unidade de id :id*/
    public List<OperationalActivity>getAlphabeticalForMUId(Long id){
        String jpql = "from OperationalActivity oa where oa.military_unit.id = :id order by oa.name ";
        TypedQuery<OperationalActivity> query = entityManager.createQuery(jpql, OperationalActivity.class);
        query.setParameter("id", id);
        System.out.println("ENCONTRADO:"+query.getResultList().toString());
        return query.getResultList();
    }
    
    /**Retorna a lista de atividades operacionais em ordem cronológica inversa para uma unidade de id :id*/
    @Transactional(readOnly = true)
    public List<OperationalActivity>getNewerFirstForMUId(Long id){
        String jpql = "from OperationalActivity oa where oa.military_unit.id = :id order by oa.last_update desc";
        TypedQuery<OperationalActivity> query = entityManager.createQuery(jpql, OperationalActivity.class);
        query.setParameter("id", id);
        System.out.println("ENCONTRADO:"+query.getResultList().toString());
        return query.getResultList();
    }
    
    /**Retorna a lista de atividades operacionais em ordem cronológica para uma unidade de id :id*/
    @Transactional(readOnly = true)
    public List<OperationalActivity>getOlderFirstyForMUId(Long id){
        String jpql = "from OperationalActivity oa where oa.military_unit.id = :id order by oa.last_update";
        TypedQuery<OperationalActivity> query = entityManager.createQuery(jpql, OperationalActivity.class);
        query.setParameter("id", id);
        System.out.println("ENCONTRADO:"+query.getResultList().toString());
        return query.getResultList();
    }
    
    /**Retorna a lista de atividades operacionais separadas por status para uma unidade de id :id*/
    @Transactional(readOnly = true)
    public List<OperationalActivity>getPlanForMUId(Long id){
        String jpql = "from OperationalActivity oa where oa.military_unit.id = :id and oa.status ='PLAN'";
        TypedQuery<OperationalActivity> query = entityManager.createQuery(jpql, OperationalActivity.class);
        query.setParameter("id", id);
        System.out.println("ENCONTRADO:"+query.getResultList().toString());
        return query.getResultList();
    }
    /**Retorna a lista de atividades operacionais separadas por status para uma unidade de id :id*/
    @Transactional(readOnly = true)
    public List<OperationalActivity>getDoingForMUId(Long id){
        String jpql = "from OperationalActivity oa where oa.military_unit.id = :id and oa.status ='DOING'";
        TypedQuery<OperationalActivity> query = entityManager.createQuery(jpql, OperationalActivity.class);
        query.setParameter("id", id);
        System.out.println("ENCONTRADO:"+query.getResultList().toString());
        return query.getResultList();
    }
    /**Retorna a lista de atividades operacionais separadas por status para uma unidade de id :id*/
    @Transactional(readOnly = true)
    public List<OperationalActivity>getDoneForMUId(Long id){
        String jpql = "from OperationalActivity oa where oa.military_unit.id = :id and oa.status ='DONE'";
        TypedQuery<OperationalActivity> query = entityManager.createQuery(jpql, OperationalActivity.class);
        query.setParameter("id", id);
        System.out.println("ENCONTRADO:"+query.getResultList().toString());
        return query.getResultList();
    }
    
    public void save(OperationalActivity operationalActivity) {
	entityManager.persist(operationalActivity);	
    }
    
    public void update(OperationalActivity operationalActivity) {
        entityManager.merge(operationalActivity);		
    }

    //DELETES
    /**Deleta uma atividade operacional, excluindo também registros de efetivo escalado para a missão*/
    public void deleteOperationalActivity(Long id) {
        //recupera a lista de militares escalados 
        String jpql = "from OperationForceMap om where om.operationalActivity.id = :id";
        TypedQuery<OperationForceMap> query = entityManager.createQuery(jpql, OperationForceMap.class);
        query.setParameter("id", id);
        List<OperationForceMap> list = query.getResultList();
        //para cada militar escalado, exclui seu registro
        for(int i=0;i<list.size();i++){
                this.delete(id, list.get(i).getMilitary().getId());
            
        }
        //exclui o registro da operação no banco
	entityManager.remove(entityManager.getReference(OperationalActivity.class, id));		

    }
    /**Exclui um único registro de militar de id:milId escalado para atividade operacional de id:oaId*/
    public Boolean delete(Long oaId, Long milId) {
        String jpql = "from OperationForceMap om where om.operationalActivity.id = :oaId and om.military.id = :milId";
        TypedQuery<OperationForceMap> query = entityManager.createQuery(jpql, OperationForceMap.class);
        query.setParameter("milId", milId);
        query.setParameter("oaId", oaId);
        try{
            OperationForceMap auxOA = query.getSingleResult();
            entityManager.remove(entityManager.getReference(OperationForceMap.class, auxOA.getId()));	
            return Boolean.TRUE;
        }catch (NoResultException nre){
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nERROR NRE:"+nre.toString());
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\nmilId:"+milId+"  oaId:"+oaId);
            return Boolean.FALSE;
        }
        	

    }
    
    
    //OPERACÕES PARA OperationForceMap
        
    /**Retorna a lista de militares para uma operação de id :id*/
    @Transactional(readOnly = true)
    public List<Military> getAllMilitaryOnForceMap(Long id) {
        String jpql = "from OperationForceMap om where om.operationalActivity.id = :id";
        TypedQuery<OperationForceMap> query = entityManager.createQuery(jpql, OperationForceMap.class);
        query.setParameter("id", id);
        List<OperationForceMap> list = query.getResultList();
        List<Military> milList= new ArrayList<Military>();
        for(int i=0;i<list.size();i++){
            milList.add(list.get(i).getMilitary());        
        }
        return milList;
    }
    /**Retorna true se o militar já está cadastrado no efetivo da operação*/
    @Transactional(readOnly = true)
    public Boolean verifyMilitaryOnForceMap(Long oaId, Long milId){
        String jpql = "from OperationForceMap oa where oa.operationalActivity.id = :oaId and oa.military.id = :milId";
        TypedQuery<OperationForceMap> query = entityManager.createQuery(jpql, OperationForceMap.class);
        query.setParameter("oaId", oaId);
        query.setParameter("milId", milId);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\nBUSCA REALIZADA, retornando:"+!query.getResultList().isEmpty());
        return !query.getResultList().isEmpty();
    }
    public void saveInForceMap(OperationForceMap operationForceMap) {
	entityManager.persist(operationForceMap);	
    }
}
