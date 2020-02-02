/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.domain.User;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.UserClassEnum;
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
public class UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void save(User user) {
        System.out.println("Usuario a ser persistido:"+user.toString());
    entityManager.persist(user);	
    }

    public void update(User user) {
        System.out.println("Usuario: "+user.toString());
        entityManager.merge(user);		
    }

    public void excluir(Long id) {
            entityManager.remove(entityManager.getReference(User.class, id));		
    }

    @Transactional(readOnly = true)
    public User getId(Long id) {
            String jpql = "from User u where u.id = :id";
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("id", id);
            return query.getSingleResult();
    }

    @Transactional(readOnly = true)
    public List<User> getAll() {                                      
             
        String jpql = "from User u";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        if(query.getResultList().isEmpty())
            System.out.println("RETORNO VAZIO");
        System.out.println("PEGANDO TODOS !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!"+query.getResultList().toString());
        return query.getResultList();
    }
    /**Retorna uma lista de militares, caso existam, para um id:id*/
    @Transactional(readOnly = true)
    public List<User> getByMilitaryId(Long id) {
            String jpql = "from User u where u.military.id = :id";
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("id", id);
            System.out.println("militares localizados:: "+query.toString());
            return query.getResultList();
    }
    
    @Transactional(readOnly = true)
    public List<User> getByClasse(UserClassEnum classe) {
        String jpql = "from User u where u.classe = :classe";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("classe", classe);
        return query.getResultList();
    }
    
    /**Retorna todos os usuários que pertencem a uma unidade militar específica*/
    @Transactional(readOnly = true)
    public List<User> getByMU(Long id) {
        String jpql = "from User u where u.military.militaryUnit.id = :id";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    /**Busca se um username já está registrado no banco*/
    @Transactional(readOnly = true)
    public User getByUsername(String username) {
        System.out.println("BUSCANDO POR:"+username);
        String jpql = "from User u where u.username like :username";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("username", "%"+username+"%");
        try{                     
            return  query.getSingleResult();
        }catch (NoResultException nre){
            return null;
        }

    }

    //Verifica se há usuário com login e senha
    @Transactional(readOnly = true)
    public List<User> getCheckUser(String username, String password) {
            String jpql = "from User u where u.username like :username and u.password like :password";
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("login", "%"+username+"%");
            return query.getResultList();

    }

    public User getById(Long id) {
            String jpql = "from User u where u.id = :id";
            TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
            query.setParameter("id", id);
            return query.getSingleResult();
    }
        
    
    public List<User> getByNG(String nomeG) {
        String jpql = "from User u where u.militar.nome_guerra = :nomeG";
        TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
        query.setParameter("nomeG", nomeG);
        return query.getResultList();
    }
	/*@Transactional(readOnly = true)
	@Override
	public List<User> getBySexo(SexoEnum sexo) {
		String jpql = "from User u where u.sexo = :sexo";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("sexo", sexo);
		return query.getResultList();
	}

	@Transactional(readOnly = true)
	@Override
	public List<User> getByNome(String nome) {
		String jpql = "from User u where u.nome like :nome or u.sobrenome like :sobrenome";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("nome", "%"+nome+"%");
		query.setParameter("sobrenome", "%"+nome+"%");
		return query.getResultList();
	}*/
}
