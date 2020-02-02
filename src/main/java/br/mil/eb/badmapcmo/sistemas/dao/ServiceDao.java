/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.domain.enumeration.ServiceCatalogEnum;
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.SVC2_WaterSupplySewageCollection;
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.SVC3_GarbageCollection;
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repositório de dados para serviços de PEF.
 * @author tenbenites
 */
@Repository
@Transactional
public class ServiceDao {
    //Métodos e declarações gerais
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<Service> getAllByMUId(Long id) {
        String jpql = "from Service svc where svc.mu_owner.id = :id";
	TypedQuery<Service> query = entityManager.createQuery(jpql, Service.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    
    public void saveSvc(Service svc) {
	entityManager.persist(svc);	
    }
    
    public void updateSvc(Service svc) {
	entityManager.merge(svc);		
    }

    public void deleteSvc(Long id) {
        Service svc = getSvc1ById(id);
        if(svc.getServiceType().equals(ServiceCatalogEnum.SVC1))
            entityManager.remove(entityManager.getReference(Service.class, id));		
        if(svc.getServiceType().equals(ServiceCatalogEnum.SVC2))
            entityManager.remove(entityManager.getReference(SVC2_WaterSupplySewageCollection.class, id));		
        if(svc.getServiceType().equals(ServiceCatalogEnum.SVC3))
            entityManager.remove(entityManager.getReference(Service.class, id));		
        if(svc.getServiceType().equals(ServiceCatalogEnum.SVC4))
            entityManager.remove(entityManager.getReference(Service.class, id));
       
    }
    
    //Métodos e declarações separados por TIPO de serviço
    
    //TIPO 1 - Outros
     /**Recupera "Outros serviços" para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<Service> getSvc1ByMUId(Long id) {
        String jpql = "from Service svc where svc.mu_owner.id = :id and svc.serviceType = 'SVC1'";
	TypedQuery<Service> query = entityManager.createQuery(jpql, Service.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
     /**Recupera "Outros serviços" de id:id*/
    @Transactional(readOnly = true)
    public Service getSvc1ById(Long id) {
        String jpql = "from Service svc where svc.id = :id";
	TypedQuery<Service> query = entityManager.createQuery(jpql, Service.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }         
    }
    
    public void saveSvc1(Service svc) {
	entityManager.persist(svc);	
    }

    public void updateSvc1(Service svc) {
	entityManager.merge(svc);		
    }

    public void deleteSvc1(Long id) {
	entityManager.remove(entityManager.getReference(Service.class, id));		
    }
    //TIPO 2 - Água/Esgoto
     /**Recupera "Outros serviços" para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SVC2_WaterSupplySewageCollection> getSvc2ByMUId(Long id) {
        String jpql = "from SVC2_WaterSupplySewageCollection svc where svc.mu_owner.id = :id and svc.serviceType = 'SVC2'";
	TypedQuery<SVC2_WaterSupplySewageCollection> query = entityManager.createQuery(jpql, SVC2_WaterSupplySewageCollection.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
     /**Recupera "Outros serviços" de id:id*/
    @Transactional(readOnly = true)
    public SVC2_WaterSupplySewageCollection getSvc2ById(Long id) {
        String jpql = "from SVC2_WaterSupplySewageCollection svc where svc.id = :id";
	TypedQuery<SVC2_WaterSupplySewageCollection> query = entityManager.createQuery(jpql, SVC2_WaterSupplySewageCollection.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }         
    }
    
    public void saveSvc2(SVC2_WaterSupplySewageCollection svc) {
	entityManager.persist(svc);	
    }

    public void updateSvc2(SVC2_WaterSupplySewageCollection svc) {
	entityManager.merge(svc);		
    }

    public void deleteSvc2(Long id) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\nENTROU remoção DAO");
	entityManager.remove(entityManager.getReference(SVC2_WaterSupplySewageCollection.class, id));		
    }
    
    //TIPO 3 - Coleta de Lixo
     /**Recupera "Outros serviços" para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SVC3_GarbageCollection> getSvc3ByMUId(Long id) {
        String jpql = "from SVC3_GarbageCollection svc where svc.mu_owner.id = :id and svc.serviceType = 'SVC3'";
	TypedQuery<SVC3_GarbageCollection> query = entityManager.createQuery(jpql, SVC3_GarbageCollection.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
     /**Recupera "Outros serviços" de id:id*/
    @Transactional(readOnly = true)
    public SVC3_GarbageCollection getSvc3ById(Long id) {
        String jpql = "from SVC3_GarbageCollection svc where svc.id = :id";
	TypedQuery<SVC3_GarbageCollection> query = entityManager.createQuery(jpql, SVC3_GarbageCollection.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }         
    }
    
    public void saveSvc3(SVC3_GarbageCollection svc) {
	entityManager.persist(svc);	
    }

    public void updateSvc3(SVC3_GarbageCollection svc) {
	entityManager.merge(svc);		
    }

    public void deleteSvc3(Long id) {
	entityManager.remove(entityManager.getReference(SVC3_GarbageCollection.class, id));		
    }
    //TIPO 4 - rede de energia de PEF
     /**Recupera "rede de energia de PEF para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SVC4_EnergyNet> getSvc4ByMUId(Long id) {
        String jpql = "from SVC4_EnergyNet svc where svc.mu_owner.id = :id and svc.serviceType = 'SVC4'";
	TypedQuery<SVC4_EnergyNet> query = entityManager.createQuery(jpql, SVC4_EnergyNet.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
     /**Recupera rede de energia de PEF de id:id*/
    @Transactional(readOnly = true)
    public SVC4_EnergyNet getSvc4ById(Long id) {
        String jpql = "from SVC4_EnergyNet svc where svc.id = :id";
	TypedQuery<SVC4_EnergyNet> query = entityManager.createQuery(jpql, SVC4_EnergyNet.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }         
    }
    
    public void saveSvc4(SVC4_EnergyNet svc) {
	entityManager.persist(svc);	
    }

    public void updateSvc4(SVC4_EnergyNet svc) {
	entityManager.merge(svc);		
    }

    public void deleteSvc4(Long id) {
	entityManager.remove(entityManager.getReference(SVC4_EnergyNet.class, id));		
    }
    //TIPO 5 - Convênios
     /**Recupera Convênios para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SVC5_Insurance> getSvc5ByMUId(Long id) {
        String jpql = "from SVC5_Insurance svc where svc.mu_owner.id = :id and svc.serviceType = 'SVC5'";
	TypedQuery<SVC5_Insurance> query = entityManager.createQuery(jpql, SVC5_Insurance.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
     /**Recupera Convênios de id:id*/
    @Transactional(readOnly = true)
    public SVC5_Insurance getSvc5ById(Long id) {
        String jpql = "from SVC5_Insurance svc where svc.id = :id";
	TypedQuery<SVC5_Insurance> query = entityManager.createQuery(jpql, SVC5_Insurance.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }         
    }
    
    public void saveSvc5(SVC5_Insurance svc) {
	entityManager.persist(svc);	
    }

    public void updateSvc5(SVC5_Insurance svc) {
	entityManager.merge(svc);		
    }

    public void deleteSvc5(Long id) {
	entityManager.remove(entityManager.getReference(SVC5_Insurance.class, id));		
    }
    
    
}
