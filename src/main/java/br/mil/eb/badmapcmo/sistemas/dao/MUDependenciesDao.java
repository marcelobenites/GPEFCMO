/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.domain.MUDependencies;
import br.mil.eb.badmapcmo.sistemas.domain.MilitaryUnit;
import br.mil.eb.badmapcmo.sistemas.domain.enumeration.DependencyTypeEnum;
import br.mil.eb.badmapcmo.sistemas.interfaces.DaoInterface;
import java.util.ArrayList;
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
public class MUDependenciesDao implements DaoInterface<MUDependencies>{

    MilitaryUnitDao dao;
    @PersistenceContext
    private EntityManager entityManager;

    public void save(MUDependencies muDependencies) {
        entityManager.persist(muDependencies);
    }

    public void update(MUDependencies muDependencies) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        System.out.println(">>>>FAZENDO UPDATE\n SUBORDINADA:" + muDependencies.getSubordinatedUnit() + "  DEPENDENTE:" + muDependencies.getSuperiorUnit());
        entityManager.merge(muDependencies);
    }

    public void deleteMUDep(Long mu_id, Long rmu_id) {
        String jpql = "from MUDependencies md where (md.superiorUnit.id = :mu_id and md.subordinatedUnit.id = :rmu_id)";
        TypedQuery<MUDependencies> query = entityManager.createQuery(jpql, MUDependencies.class);
        query.setParameter("mu_id", mu_id);
        query.setParameter("rmu_id", rmu_id);
        MUDependencies mud;
        try {
            mud = query.getSingleResult();
        } catch (NoResultException nre) {
            return;
        }

        entityManager.remove(mud);
    }

    public void remove(Long id) {
        entityManager.remove(entityManager.getReference(MUDependencies.class, id));
    }

    /**
     * Remove todas as dependências de uma unidade de id:id
     */
    public void deleteMU(Long id) {
        List<MUDependencies> mdl = getAllById(id);
        for (int i = 0; i < mdl.size(); i++) {
            remove(mdl.get(i).getId());
        }

    }

    /**
     * Recupera todas as dependencias para uma unidade de id:id
     */
    @Transactional(readOnly = true)
    public List<MUDependencies> getAllById(Long id) {
        String jpql = "from MUDependencies md where md.subordinatedUnit.id = :id or md.superiorUnit.id = :id";
        TypedQuery<MUDependencies> query = entityManager.createQuery(jpql, MUDependencies.class);
        query.setParameter("id", id);
        //System.out.println("\n\n\n\nPassou na seleção de todas as ocorrencias: " + query.getResultList().toString());
        return query.getResultList();
    }

    /**
     * Recupera o tipo de dependência dados os ids de duas unidades
     *
     * @see DependencyTypeEnum.java
     */
    @Transactional(readOnly = true)
    public DependencyTypeEnum getDependencyType(Long mu_id, Long rmu_id) {
        String jpql = "from MUDependencies md where ((md.subordinatedUnit.id = :mu_id and md.superiorUnit.id = :rmu_id) or (md.subordinatedUnit.id = :rmu_id and md.superiorUnit.id = :mu_id))";
        TypedQuery<MUDependencies> query = entityManager.createQuery(jpql, MUDependencies.class);
        query.setParameter("mu_id", mu_id);
        query.setParameter("rmu_id", rmu_id);
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n Localizando dependências entre os ids [" + mu_id + "][" + rmu_id + "]");
        System.out.println(">>>Pesquisa no banco retornou nro de resultados====" + query.getResultList().size());
        MUDependencies mud;
        if (query.getResultList().isEmpty()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n Dependência NR");
            return DependencyTypeEnum.NR;
        }

        try {
            mud = query.getSingleResult();
        } catch (NoResultException nre) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(">>>>Pesquisa retornou mais de um resultado!!!\n" + query.toString());
            return DependencyTypeEnum.NR;
        }
        if (mud.getSubordinatedUnit().getId() == mu_id) {

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n Dependência SUB");
            return DependencyTypeEnum.SUB;
        } else if (mud.getSuperiorUnit().getId() == mu_id) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n Dependência SUP");
            return DependencyTypeEnum.SUP;
        }
        return DependencyTypeEnum.NR;
    }

    /**
     * Recupera todas as unidades superiores a uma unidade de id:id
     */
    @Transactional(readOnly = true)
    public MUDependencies getDependency(Long sup_id, Long sub_id) {
        String jpql = "from MUDependencies md where md.superiorUnit.id = :sup_id and md.subordinatedUnit.id = :sub_id";
        TypedQuery<MUDependencies> query = entityManager.createQuery(jpql, MUDependencies.class);
        query.setParameter("sup_id", sup_id);
        query.setParameter("sub_id", sub_id);
        MUDependencies mud;
        if (query.getResultList().isEmpty()) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Pesquisa no banco retornou SEM RELAÇÃO cadastrada!!!\n" + query.toString());
            return null;
        }
        try {
            mud = query.getSingleResult();
            return mud;
        } catch (NoResultException nre) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Pesquisa retornou mais de um resultado!!!\n" + query.toString());
            return null;
        }
    }

    /**
     * Recupera todas as unidades superiores a uma unidade de id:id
     */
    @Transactional(readOnly = true)
    public List<MilitaryUnit> getSuperiorsById(Long id) {
        String jpql = "select md.superiorUnit from MUDependencies md where md.subordinatedUnit.id = :id";
        TypedQuery<MilitaryUnit> query = entityManager.createQuery(jpql, MilitaryUnit.class);
        query.setParameter("id", id);
        System.out.println("passou na seleção de superiores: " + query.getResultList().toString());
        return query.getResultList();
    }

    /**
     * Recupera todas as unidades subordinadas a uma unidade de id:id
     */
    @Transactional(readOnly = true)
    public List<MilitaryUnit> getSubordinatedById(Long id) {
        System.out.println("buscando subordinados para unidade de ID:: " + id);
        String jpql = "select md.subordinatedUnit from MUDependencies md where md.superiorUnit.id = :id";
        TypedQuery<MilitaryUnit> query = entityManager.createQuery(jpql, MilitaryUnit.class);
        query.setParameter("id", id);
        List<MilitaryUnit> list = query.getResultList();
        for(MilitaryUnit m : list){
            System.out.println("\n\n Selecionou de subordinada: " + m.getMU_name());
        }
        return query.getResultList();
    }

    @Override
    public List<MUDependencies> getAll() {
        String jpql = "from MUDependencies md";
            TypedQuery<MUDependencies> query = entityManager.createQuery(jpql, MUDependencies.class);
            if(query.getResultList().isEmpty()){
                System.out.println("RETORNO VAZIO");
            }
            return query.getResultList();
    }

    @Override
    public MUDependencies getById(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String verifyIntegrity(MUDependencies t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
