/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por STI (Seção de Tecnologia da Informação)
 *  2019 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.interfaces.DaoInterface;
import br.mil.eb.badmapcmo.sistemas.dao.*;
import br.mil.eb.badmapcmo.sistemas.domain.PELDST.MUAdditionalFraction;
import br.mil.eb.badmapcmo.sistemas.domain.PELDST.MUAdditionalFraction;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repositório de dados de efetivos adicionais.
 * @see MUAdditionalFraction.java
 * @author ten-benites
 */
@Repository
@Transactional
public class MUAdditionalFractionsDao implements DaoInterface<MUAdditionalFraction>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(MUAdditionalFraction fraction) {
        System.out.println("\n\n\n\n\n\n\n\n\n ENTROU NO SALVAMENTO:"+fraction.toString());
        entityManager.persist(fraction);	
    }

    @Override
    public void update(MUAdditionalFraction fraction) {
        entityManager.merge(fraction);	
    }

    @Override
    public void remove(Long id) {
        entityManager.remove(entityManager.getReference(MUAdditionalFraction.class, id));
    }

    @Override
    public List<MUAdditionalFraction> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<MUAdditionalFraction> getAllByMUId(Long id) {
        String jpql = "from MUAdditionalFraction muas where muas.related_mu.id = :id";
        TypedQuery<MUAdditionalFraction> query = entityManager.createQuery(jpql, MUAdditionalFraction.class);
            query.setParameter("id", id);
            return query.getResultList();
    }

    @Override
    public MUAdditionalFraction getById(Long id) {
        String jpql = "from MUAdditionalFraction muas where muas.id = :id";
        TypedQuery<MUAdditionalFraction> query = entityManager.createQuery(jpql, MUAdditionalFraction.class);
            query.setParameter("id", id);
            return query.getSingleResult();
    }

    @Override
    public String verifyIntegrity(MUAdditionalFraction t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**Retorna a lista de efetivos adicionais de um determinado PDL/DST de id:id*/
    @Transactional(readOnly = true)
    public List<MUAdditionalFraction> getByMUId(Long id) {
        String jpql = "from MUAdditionalFraction muas where muas.related_mu.id = :id";
        TypedQuery<MUAdditionalFraction> query = entityManager.createQuery(jpql, MUAdditionalFraction.class);
        query.setParameter("id", id);
        return query.getResultList();
    }    
    
    
}
