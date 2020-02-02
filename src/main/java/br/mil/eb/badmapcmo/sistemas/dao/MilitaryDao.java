package br.mil.eb.badmapcmo.sistemas.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.badmapcmo.sistemas.domain.Military;
import br.mil.eb.badmapcmo.sistemas.domain.User;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;

@Repository
@Transactional
public class MilitaryDao {
	@PersistenceContext
	private EntityManager entityManager;
		
        public void update(Military military) {
		entityManager.merge(military);		
	}
        
        
	@Transactional(readOnly = true)
	public Military getById(Long id) {
		String jpql = "from Military m where m.id = :id";
		TypedQuery<Military> query = entityManager.createQuery(jpql, Military.class);
		query.setParameter("id", id);
		try{
                    return  query.getSingleResult();
                }catch (NoResultException nre){
                    return null;
                }
	}
        
	@Transactional(readOnly = true)
	public Military getByIdt(Integer military_identitiy) {
		String jpql = "from Military m where m.military_identitiy = :military_identitiy";
		TypedQuery<Military> query = entityManager.createQuery(jpql, Military.class);
		query.setParameter("military_identitiy", military_identitiy);
		try{
                    return  query.getSingleResult();
                }catch (NoResultException nre){
                    return null;
                }
	}

        @Transactional(readOnly = true)
        public List<Military> getAll() {                                      
             
            String jpql = "from Military m";
            TypedQuery<Military> query = entityManager.createQuery(jpql, Military.class);
            if(query.getResultList().isEmpty())
                System.out.println("RETORNO VAZIO");
            System.out.println("PEGANDO TODOS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+query.getResultList().toString());
            return query.getResultList();
        }
        
        @Transactional(readOnly = true)
        public List<Military> getAllForMU(Long id) { 
            String jpql = "from Military m where m.militaryUnit.id = :id";
            TypedQuery<Military> query = entityManager.createQuery(jpql, Military.class);
            query.setParameter("id", id);
            System.out.println("PEGANDO TODOS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+query.getResultList().toString());
            return query.getResultList();
        }
        
                
                
	
		
	@Transactional(readOnly = true)
        public List<Military> searchByName(String name, Long id) { 
            String jpql = "from Military m where m.militaryUnit.id = :id";
            TypedQuery<Military> query = entityManager.createQuery(jpql, Military.class);
            query.setParameter("id", id);
            List<Military> ml = query.getResultList();
            int i=0;
            while(i<ml.size()){
                Military m = ml.get(i);
                if(!(m.getName().contains(name)||m.getSurname().contains(name)||m.getWar_name().contains(name))){
                    ml.remove(i);
                    i--;
                }
                i++;
            }
            return ml;
        }
        public void save(Military military) {
                System.out.println("entrou na persistencia");
		entityManager.persist(military);	
        }
        
        public void delete(Long id) {
		entityManager.remove(entityManager.getReference(Military.class, id));		

        }
        
        
        
}
