package br.mil.eb.badmapcmo.sistemas.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.MilitaryUnitTypeEnum;
import javax.persistence.NoResultException;
/**
 * Repositório de unidades militares
 * Convenção de nomenclatura de métodos nos repositórios 
 * Métodos "get" - busca no banco por todas as ocorrências dado um critério de filtragem, retornando uma lista
 * Métodos "find" - localiza no banco uma ocorrência específica de um objeto, retornando um objeto
 * @see MilitaryUnit.java
 * @author tenbenites
 */
@Repository
@Transactional
public class MilitaryUnitDao {
	@PersistenceContext
	private EntityManager entityManager;
	public void save(MilitaryUnit militaryUnit) {
                System.out.println("entrou na persistencia");
		entityManager.persist(militaryUnit);	
	}

	public void update(MilitaryUnit militaryUnit) {
                System.out.println("\n\n\n\n\n\n Entrou na atualização de MU");
            
		entityManager.merge(militaryUnit);		
	}

	public void delete(Long id) {
		entityManager.remove(entityManager.getReference(MilitaryUnit.class, id));		
	}
        /**Método tipo "find"(busca retornando um objeto) busca uma unidade específica de id = id*/
	@Transactional(readOnly = true)
	public MilitaryUnit findById(Long id) {
		String jpql = "from MilitaryUnit u where u.id = :id";
		TypedQuery<MilitaryUnit> query = entityManager.createQuery(jpql, MilitaryUnit.class);
		query.setParameter("id", id);
		try{
                    return  query.getSingleResult();
                }catch (NoResultException nre){
                    return null;
                }
	}

	/**Método tipo "find"(busca retornando um objeto) recupera uma unidade militar para um dado nome.*/
	@Transactional(readOnly = true)
	public MilitaryUnit findByInitials(String MU_initials) {
            System.out.println("\n\n\n\n\n\n\n\n\nBuscando por :" + MU_initials);
		String jpql = "from MilitaryUnit mu where mu.MU_initials like :MU_initials";
		TypedQuery<MilitaryUnit> query = entityManager.createQuery(jpql, MilitaryUnit.class);
		query.setParameter("MU_initials", MU_initials);
		try{
                    return  query.getSingleResult();
                }catch (NoResultException nre){
                    return null;
                }
	}
        
        
        @Transactional(readOnly = true)
	public List<MilitaryUnit> getAll() {
		String jpql = "from MilitaryUnit mu";
		TypedQuery<MilitaryUnit> query = entityManager.createQuery(jpql, MilitaryUnit.class);
		return query.getResultList();
	}
        
        
        
        
        /*TODO: refactoring - organizar métodos search, get e find*/
        
	/**Método tipo "get"(busca retornando uma lista) recupera uma lista de unidades militares de um tipo específico.*/	
	@Transactional(readOnly = true)
	public List<MilitaryUnit> getByType(MilitaryUnitTypeEnum MU_type) {
		String jpql = "from MilitaryUnit mu where mu.MU_type = :MU_type";
		TypedQuery<MilitaryUnit> query = entityManager.createQuery(jpql, MilitaryUnit.class);
		query.setParameter("MU_type", MU_type);
		return query.getResultList();
	}
        /**Método tipo "get"(busca retornando uma lista) recupera uma lista de unidades militares para um dado nome.*/
	@Transactional(readOnly = true)
	public List<MilitaryUnit> getByName(String MU_name) {
		String jpql = "from MilitaryUnit mu where mu.MU_name like :MU_name";
		TypedQuery<MilitaryUnit> query = entityManager.createQuery(jpql, MilitaryUnit.class);
		query.setParameter("MU_name", "%"+MU_name+"%");
		return query.getResultList();
	}

}
