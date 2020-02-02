/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por DTI (Departamento de Tecnologia da Informação)
 *  2018 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.dao;
 
import br.mil.eb.badmapcmo.sistemas.domain.supplyAndServiceItems.*;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repositório de operações sobre itens de suprimento.
 * @author tenbenites
 */
@Repository
@Transactional
public class SupplyDao {
   
    //Métodos e declarações gerais
    
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public List<SupplyItem> getAllByMUId(Long id) {
        String jpql = "from SupplyItem si where si.mu_owner.id = :id";
	TypedQuery<SupplyItem> query = entityManager.createQuery(jpql, SupplyItem.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    
    //Métodos e declarações separados por classe 
    //CLASSE I
    public void saveSCI(SupplyItemCI supplyItemCI) {
	entityManager.persist(supplyItemCI);	
    }

    public void updateSCI(SupplyItemCI supplyItemCI) {
	entityManager.merge(supplyItemCI);		
    }

    public void deleteSCI(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCI.class, id));		
    }
    
    
    /**Recupera itens de suprimento da classe 1 para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SupplyItemCI> getSCIByMUId(Long id) {
        String jpql = "from SupplyItemCI si where si.mu_owner.id = :id";
	TypedQuery<SupplyItemCI> query = entityManager.createQuery(jpql, SupplyItemCI.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    /**Recupera o item de suprimento da classe 1 de id:id*/
    @Transactional(readOnly = true)
    public SupplyItemCI getSCIById(Long id) {
        String jpql = "from SupplyItemCI si where si.id = :id";
	TypedQuery<SupplyItemCI> query = entityManager.createQuery(jpql, SupplyItemCI.class);
	query.setParameter("id", id);
	return query.getSingleResult();        
    }
    
    //CLASSE II
    public void saveSCII(SupplyItemCII supplyItemCII) {
	entityManager.persist(supplyItemCII);	
    }

    public void updateSCII(SupplyItemCII supplyItemCII) {
	entityManager.merge(supplyItemCII);		
    }

    public void deleteSCII(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCII.class, id));		
    }
    
    /**Recupera itens de suprimento da classe 2 para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SupplyItemCII> getSCIIByMUId(Long id) {
        String jpql = "from SupplyItemCII si where si.mu_owner.id = :id";
	TypedQuery<SupplyItemCII> query = entityManager.createQuery(jpql, SupplyItemCII.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    /**Recupera o item de suprimento da classe 2 de id:id*/
    @Transactional(readOnly = true)
    public SupplyItemCII getSCIIById(Long id) {
        String jpql = "from SupplyItemCII si where si.id = :id";
	TypedQuery<SupplyItemCII> query = entityManager.createQuery(jpql, SupplyItemCII.class);
	query.setParameter("id", id);
	return query.getSingleResult();        
    }
    
    //CLASSE III
    /**Recupera itens de suprimento da classe 3.1 (gás de cozinha) para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SupplyItemCIII1_LPG> getSCIII1ByMUId(Long id) {
        String jpql = "from SupplyItemCIII1_LPG l where l.mu_owner.id = :id";
	TypedQuery<SupplyItemCIII1_LPG> query = entityManager.createQuery(jpql, SupplyItemCIII1_LPG.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    
    //CLASSE III.1
    /**Recupera o item de suprimento da classe 3.1 (gás GLP) de id:id*/
    @Transactional(readOnly = true)
    public SupplyItemCIII1_LPG getSCIII1ById(Long id) {
        String jpql = "from SupplyItemCIII1_LPG l where l.id = :id";
	TypedQuery<SupplyItemCIII1_LPG> query = entityManager.createQuery(jpql, SupplyItemCIII1_LPG.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    
    public void saveSCIII1(SupplyItemCIII1_LPG lpg) {
        entityManager.persist(lpg);	
    }
    public void deleteSCIII1(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCIII1_LPG.class, id));		
    }
    //update de cadastro
    public void updateSCIII1(SupplyItemCIII1_LPG lpg) {
	entityManager.merge(lpg);		
    } 
    
    //Classe III.2
    /**Recupera itens de suprimento da classe 3.2 (gasolina) para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SupplyItemCIII2_Gasoline> getSCIII2ByMUId(Long id) {
        String jpql = "from SupplyItemCIII2_Gasoline g where g.mu_owner.id = :id";
	TypedQuery<SupplyItemCIII2_Gasoline> query = entityManager.createQuery(jpql, SupplyItemCIII2_Gasoline.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    /**Recupera o item de suprimento da classe 2 de id:id*/
    @Transactional(readOnly = true)
    public SupplyItemCIII2_Gasoline getSCIII2ById(Long id) {
        String jpql = "from SupplyItemCIII2_Gasoline g where g.id = :id";
	TypedQuery<SupplyItemCIII2_Gasoline> query = entityManager.createQuery(jpql, SupplyItemCIII2_Gasoline.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    public void saveSCIII2(SupplyItemCIII2_Gasoline supplyItemCIII2_Gasoline) {
        entityManager.persist(supplyItemCIII2_Gasoline);	
    }
    public void deleteSCIII2(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCIII2_Gasoline.class, id));		
    }
    //update de cadastro
    public void updateSCIII2(SupplyItemCIII2_Gasoline supplyItemCIII2_Gasoline) {
	entityManager.merge(supplyItemCIII2_Gasoline);		
    } 
    
    
    
    //CLASSE III.3
    /**Recupera itens de suprimento da classe 3.3 (diesel) para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SupplyItemCIII3_Diesel> getSCIII3ByMUId(Long id) {
        String jpql = "from SupplyItemCIII3_Diesel d where d.mu_owner.id = :id";
	TypedQuery<SupplyItemCIII3_Diesel> query = entityManager.createQuery(jpql, SupplyItemCIII3_Diesel.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    /**Recupera o item de suprimento da classe 3.3 (diesel) de id:id*/
    @Transactional(readOnly = true)
    public SupplyItemCIII3_Diesel getSCIII3ById(Long id) {
        String jpql = "from SupplyItemCIII3_Diesel d where d.id = :id";
	TypedQuery<SupplyItemCIII3_Diesel> query = entityManager.createQuery(jpql, SupplyItemCIII3_Diesel.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    public void saveSCIII3(SupplyItemCIII3_Diesel SupplyItemCIII3) {
        entityManager.persist(SupplyItemCIII3);	
    }
    public void deleteSCIII3(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCIII3_Diesel.class, id));		
    }
    //update de cadastro
    public void updateSCIII3(SupplyItemCIII3_Diesel SupplyItemCIII3) {
	entityManager.merge(SupplyItemCIII3);		
    }
    
    
    //CLASSE IV
    /**Recupera o item de suprimento da classe 4 de id:id*/
    @Transactional(readOnly = true)
    public SupplyItemCIV getSCIVById(Long id) {
        String jpql = "from SupplyItemIV siiv where siiv.id = :id";
	TypedQuery<SupplyItemCIV> query = entityManager.createQuery(jpql, SupplyItemCIV.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    
    
    public void saveSCIV(SupplyItemCIV supplyItemCIV) {
        entityManager.persist(supplyItemCIV);	
    }
    
    public void deleteSCIV(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCIV.class, id));		
    }
    //update de cadastro
    public void updateSCIV(SupplyItemCIV supplyItemCIV) {
	entityManager.merge(supplyItemCIV);		
    } 
    
    //CLASSE V
     /**Recupera o item de suprimento da classe 4 de id:id*/
    @Transactional(readOnly = true)
    public SupplyItemCV getSCVById(Long id) {
        String jpql = "from SupplyItemV siv where siv.id = :id";
	TypedQuery<SupplyItemCV> query = entityManager.createQuery(jpql, SupplyItemCV.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    
    public void saveSCV(SupplyItemCV supplyItemCV) {
        entityManager.persist(supplyItemCV);	
    }
    
    public void deleteSCV(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCV.class, id));		
    }
    //update de cadastro
    public void updateSCV(SupplyItemCV supplyItemCV) {
	entityManager.merge(supplyItemCV);		
    } 
    //CV1 munição
    /**Recupera itens de suprimento da classe V.1 (munição) para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SupplyItemCV1_munition> getSCV1ByMUId(Long id) {
        String jpql = "from SupplyItemCV1_munition m where m.mu_owner.id = :id";
	TypedQuery<SupplyItemCV1_munition> query = entityManager.createQuery(jpql, SupplyItemCV1_munition.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    
   
    
    
    
    @Transactional(readOnly = true)
    public SupplyItemCV1_munition getSCV1ById(Long id) {
        String jpql = "from SupplyItemCV1_munition sv1 where sv1.id = :id";
	TypedQuery<SupplyItemCV1_munition> query = entityManager.createQuery(jpql, SupplyItemCV1_munition.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    
    public void saveSCV1(SupplyItemCV1_munition supplyItemCV1) {
        entityManager.persist(supplyItemCV1);	
    }
    
    public void deleteSCV1(Long id) {
        System.out.println("\n\n\n\n\n\n\n\nENTROU NO DELETE do banco"); 
	entityManager.remove(entityManager.getReference(SupplyItemCV1_munition.class, id));		
    }
    //update de cadastro
    public void updateSCV1(SupplyItemCV1_munition supplyItemCV1) {
	entityManager.merge(supplyItemCV1);		
    } 
    //CV2 armamento
    
    /**Recupera itens de suprimento da classe V.2 (armamento) para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SupplyItemCV2_weaponry> getSCV2ByMUId(Long id) {
        String jpql = "from SupplyItemCV2_weaponry wp where wp.mu_owner.id = :id";
	TypedQuery<SupplyItemCV2_weaponry> query = entityManager.createQuery(jpql, SupplyItemCV2_weaponry.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    
    
    @Transactional(readOnly = true)
    public SupplyItemCV2_weaponry getSCV2ById(Long id) {
        String jpql = "from SupplyItemCV2_weaponry sv2 where sv2.id = :id";
	TypedQuery<SupplyItemCV2_weaponry> query = entityManager.createQuery(jpql, SupplyItemCV2_weaponry.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    
    public void saveSCV2(SupplyItemCV2_weaponry supplyItemCV2) {
        entityManager.persist(supplyItemCV2);	
    }
    
    public void deleteSCV2(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCV2_weaponry.class, id));		
    }
    //update de cadastro
    public void updateSCV2(SupplyItemCV2_weaponry supplyItemCV2) {
	entityManager.merge(supplyItemCV2);		
    } 
    //CLASSE VI
    /**Recupera itens de suprimento da classe VI (Material de Engenharia e de Cartografia) para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SupplyItemCVI> getSCVIByMUId(Long id) {
        String jpql = "from SupplyItemCVI c where c.mu_owner.id = :id";
	TypedQuery<SupplyItemCVI> query = entityManager.createQuery(jpql, SupplyItemCVI.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    
    /**Recupera o item de suprimento da classe 6 de id:id*/
    @Transactional(readOnly = true)
    public SupplyItemCVI getSCVIById(Long id) {
        String jpql = "from SupplyItemCVI svi where svi.id = :id";
	TypedQuery<SupplyItemCVI> query = entityManager.createQuery(jpql, SupplyItemCVI.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    
    public void saveSCVI(SupplyItemCVI supplyItemCVI) {
        entityManager.persist(supplyItemCVI);	
    }
    
    public void deleteSCVI(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCVI.class, id));		
    }
    //update de cadastro
    public void updateSCVI(SupplyItemCVI supplyItemCVI) {
	entityManager.merge(supplyItemCVI);		
    } 
    
    //CVI.1 Geradores
    /**Recupera itens de suprimento da classe VII.1 (geradores) para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SupplyItemCVI1_generator> getSCVI1ByMUId(Long id) {
        String jpql = "from SupplyItemCVI1_generator g where g.mu_owner.id = :id";
	TypedQuery<SupplyItemCVI1_generator> query = entityManager.createQuery(jpql, SupplyItemCVI1_generator.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    
    
    @Transactional(readOnly = true)
    public SupplyItemCVI1_generator getSCVI1ById(Long id) {
        String jpql = "from SupplyItemCVI1_generator g where g.id = :id";
	TypedQuery<SupplyItemCVI1_generator> query = entityManager.createQuery(jpql, SupplyItemCVI1_generator.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    
    public void saveSCVI1(SupplyItemCVI1_generator supplyItemCVII1_generator) {
        entityManager.persist(supplyItemCVII1_generator);	
    }
    
    public void deleteSCVI1(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCVI1_generator.class, id));		
    }
    //update de cadastro
    public void updateSCVI1(SupplyItemCVI1_generator supplyItemCVII1_generator) {
	entityManager.merge(supplyItemCVII1_generator);		
    } 
    
    //CVI.1 GPS
    /**Recupera itens de suprimento da classe VII.1 (geradores) para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<SupplyItemCVI2_GPS> getSCVI2ByMUId(Long id) {
        String jpql = "from SupplyItemCVI2_GPS g where g.mu_owner.id = :id";
	TypedQuery<SupplyItemCVI2_GPS> query = entityManager.createQuery(jpql, SupplyItemCVI2_GPS.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    
    
    @Transactional(readOnly = true)
    public SupplyItemCVI2_GPS getSCVI2ById(Long id) {
        String jpql = "from SupplyItemCVI2_GPS g where g.id = :id";
	TypedQuery<SupplyItemCVI2_GPS> query = entityManager.createQuery(jpql, SupplyItemCVI2_GPS.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    
    public void saveSCVI2(SupplyItemCVI2_GPS supplyItemCVII2_GPS) {
        entityManager.persist(supplyItemCVII2_GPS);	
    }
    
    public void deleteSCVI2(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCVI2_GPS.class, id));		
    }
    //update de cadastro
    public void updateSCVI2(SupplyItemCVI2_GPS supplyItemCVII2_GPS) {
	entityManager.merge(supplyItemCVII2_GPS);		
    } 
    
    //CLASSE VII
     /**Recupera o item de suprimento da classe 4 de id:id*/
    @Transactional(readOnly = true)
    public SupplyItemCVII getSCVIIById(Long id) {
        String jpql = "from SupplyItemCVI svi where svi.id = :id";
	TypedQuery<SupplyItemCVII> query = entityManager.createQuery(jpql, SupplyItemCVII.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    
    public void saveSCVII(SupplyItemCVII supplyItemCVII) {
        entityManager.persist(supplyItemCVII);	
    }
    
    public void deleteSCVII(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCVII.class, id));		
    }
    //update de cadastro
    public void updateSCVII(SupplyItemCVII supplyItemCVII) {
	entityManager.merge(supplyItemCVII);		
    } 
    
    
    
    
    //CLASSE VIII
 
    
    
     /**Recupera o item de suprimento da classe VIII - Material de saúde de id:id*/
    @Transactional(readOnly = true)
    public SupplyItemCVIII getSCVIIIById(Long id) {
        String jpql = "from SupplyItemVIII siviii where siviii.id = :id";
	TypedQuery<SupplyItemCVIII> query = entityManager.createQuery(jpql, SupplyItemCVIII.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    
    public void saveSCVIII(SupplyItemCVIII supplyItemCVIII) {
        entityManager.persist(supplyItemCVIII);	
    }
    
    public void deleteSCVIII(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCVIII.class, id));		
    }
    //update de cadastro
    public void updateSCVIII(SupplyItemCVIII supplyItemCVIII) {
	entityManager.merge(supplyItemCVIII);		
    } 
    //CLASSE VIX
    /**Recupera itens de suprimento da classe IX.1 (viaturas) para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<Vehicle> getSCIX1ByMUId(Long id) {
        String jpql = "from Vehicle v where v.mu_owner.id = :id";
	TypedQuery<Vehicle> query = entityManager.createQuery(jpql, Vehicle.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    /**Recupera itens de suprimento da classe IX.2 (embarcações) para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<Watercraft> getSCIX2ByMUId(Long id) {
        String jpql = "from Watercraft wc where wc.mu_owner.id = :id";
	TypedQuery<Watercraft> query = entityManager.createQuery(jpql, Watercraft.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    /**Recupera itens de suprimento da classe IX.3 (motor de popa) para uma unidade de id:id*/
    @Transactional(readOnly = true)
    public List<OutboardEngine> getSCIX3ByMUId(Long id) {
        String jpql = "from OutboardEngine oe where oe.mu_owner.id = :id";
	TypedQuery<OutboardEngine> query = entityManager.createQuery(jpql, OutboardEngine.class);
	query.setParameter("id", id);
	return query.getResultList();        
    }
    /**Deleta um item de suprimento da classe IX.1 (vituras)*/
    public void saveSCIX1(Vehicle vehicle) {
        entityManager.persist(vehicle);
        System.out.println("\n\n\n\n\n\n\n\n\n\n REPO: SALVAMENTO DE VIATURA"+vehicle.toString()+" \n\n\n\n\n\n\n");
        
    }
    /**Deleta um item de suprimento da classe IX.2 (embarcações)*/
    public void saveSCIX2(Watercraft wc) {
        entityManager.persist(wc);
    }
    /**Deleta um item de suprimento da classe IX.2 (embarcações)*/
    public void saveSCIX3(OutboardEngine oe) {
        entityManager.persist(oe);
    }
    
    /**Recupera o item de suprimento da classe IX.1 (vituras) de id:id*/
    @Transactional(readOnly = true)
    public Vehicle getSCIX1ById(Long id) {
        String jpql = "from Vehicle v where v.id = :id";
	TypedQuery<Vehicle> query = entityManager.createQuery(jpql, Vehicle.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    /**Recupera o item de suprimento da classe IX.2 (embarcações) de id:id*/
    @Transactional(readOnly = true)
    public Watercraft getSCIX2ById(Long id) {
        String jpql = "from Watercraft wc where wc.id = :id";
	TypedQuery<Watercraft> query = entityManager.createQuery(jpql, Watercraft.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    /**Recupera o item de suprimento da classe IX.3 (motor de popa) de id:id*/
    @Transactional(readOnly = true)
    public OutboardEngine getSCIX3ById(Long id) {
        String jpql = "from OutboardEngine oe where oe.id = :id";
	TypedQuery<OutboardEngine> query = entityManager.createQuery(jpql, OutboardEngine.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    /**Remoção de cadastro de viatura*/
    public void deleteSCIX1(Long id) {
	entityManager.remove(entityManager.getReference(Vehicle.class, id));		
    }
    /**Remoção de cadastro de embarcação*/
    public void deleteSCIX2(Long id) {
	entityManager.remove(entityManager.getReference(Watercraft.class, id));		
    }
    /**Remoção de cadastro de motor de popa*/
    public void deleteSCIX3(Long id) {
	entityManager.remove(entityManager.getReference(OutboardEngine.class, id));		
    }
    /**Update de cadastro de viatura*/
    public void updateSIX1(Vehicle v) {
	entityManager.merge(v);		
    }        
    /**Update de cadastro de embarcação*/
    public void updateSIX2(Watercraft wc) {
	entityManager.merge(wc);		
    }        
    /**Update de cadastro de mnotor de popa*/
    public void updateSIX3(OutboardEngine oe) {
	entityManager.merge(oe);		
    }   
    
    
    //CLASSE X
     /**Recupera o item de suprimento da classe 4 de id:id*/
    @Transactional(readOnly = true)
    public SupplyItemCX getSCXById(Long id) {
        String jpql = "from SupplyItemX six where six.id = :id";
	TypedQuery<SupplyItemCX> query = entityManager.createQuery(jpql, SupplyItemCX.class);
	query.setParameter("id", id);
	try{
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }  
        
    }
    
    public void saveSCX(SupplyItemCX supplyItemCX) {
        entityManager.persist(supplyItemCX);	
    }
    
    public void deleteSCX(Long id) {
	entityManager.remove(entityManager.getReference(SupplyItemCX.class, id));		
    }
    //update de cadastro
    public void updateSCX(SupplyItemCX supplyItemCX) {
	entityManager.merge(supplyItemCX);		
    } 
            
}
