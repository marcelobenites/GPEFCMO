/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por STI (Seção de Tecnologia da Informação)
 *  2019 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.interfaces.DaoInterface;
import br.mil.eb.badmapcmo.sistemas.domain.PELDST.MUAdditionalStaff;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Repositório de dados de efetivos adicionais.
 * @see MUAdditionalStaff.java
 * @author ten-benites
 */
@Repository
@Transactional
public class MUAdditionalStaffDao implements DaoInterface<MUAdditionalStaff>{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(MUAdditionalStaff staff) {
        System.out.println("\n\n\n\n\n\n\n\n\n ENTROU NO SALVAMENTO:"+staff.toString());
        entityManager.persist(staff);	
    }

    @Override
    public void update(MUAdditionalStaff staff) {
        entityManager.merge(staff);	
    }

    @Override
    public void remove(Long id) {
        entityManager.remove(entityManager.getReference(MUAdditionalStaff.class, id));
    }

    @Override
    public List<MUAdditionalStaff> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MUAdditionalStaff getById(Long id) {
        String jpql = "from MUAdditionalStaff muas where muas.id = :id";
        TypedQuery<MUAdditionalStaff> query = entityManager.createQuery(jpql, MUAdditionalStaff.class);
            query.setParameter("id", id);
            return query.getSingleResult();
    }

    @Override
    public String verifyIntegrity(MUAdditionalStaff t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**Retorna a lista de efetivos adicionais de um determinado PDL/DST de id:id*/
    @Transactional(readOnly = true)
    public List<MUAdditionalStaff> getByMUId(Long id) {
        String jpql = "from MUAdditionalStaff muas where muas.related_mu.id = :id";
        TypedQuery<MUAdditionalStaff> query = entityManager.createQuery(jpql, MUAdditionalStaff.class);
        query.setParameter("id", id);
        return query.getResultList();
    }
    
    
    
}
