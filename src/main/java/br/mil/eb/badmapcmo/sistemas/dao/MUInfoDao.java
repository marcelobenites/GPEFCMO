/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import br.mil.eb.badmapcmo.sistemas.domain.MUInfo;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Classe de repositório que controla itens de informação para unidades militares.
 * @see MUInfo
 * @author tenbenites
 * @since beta 1.0
 * @version 1.0
 */
@Repository
@Transactional
public class MUInfoDao{
    @PersistenceContext
    private EntityManager entityManager;
    
    @Transactional(readOnly = true)
    public MUInfo getById(Long id) {
            String jpql = "from MUInfo mui where mui.id = :id";
            TypedQuery<MUInfo> query = entityManager.createQuery(jpql, MUInfo.class);
            query.setParameter("id", id);
            return query.getSingleResult();
    }
    
    public void save(MUInfo muInfo) {
		entityManager.persist(muInfo);	
    }
    
    public void delete(Long id) {
		entityManager.remove(entityManager.getReference(MUInfo.class, id));		

    }
    /**Retorna a lista de informações para uma unidade de id :id*/
    @Transactional(readOnly = true)
    public List<MUInfo> getByMUId(Long id) {
            //System.out.println("MUIDDDD ....procurando unidade de id"+id.toString());
            String jpql = "from MUInfo mi where mi.related_mu.id = :id";
            TypedQuery<MUInfo> query = entityManager.createQuery(jpql, MUInfo.class);
            query.setParameter("id", id);
            if(query.getResultList().isEmpty()){
                return null;
            }
            return query.getResultList();
    }
    
    @Transactional(readOnly = true)
    public List<MUInfo> getByTitle(String title, Long id) {
            List<MUInfo> auxList= getByMUId(id);
            System.out.println("\n\n\n Lista cheia:"+auxList.toString());
            List<MUInfo> infoList= new ArrayList<MUInfo>();
            for(int i=0;i<auxList.size();i++){
                if(auxList.get(i).getTitle().contains(title)){
                    infoList.add(auxList.get(i));
                }
            }
            return infoList;
    }
    public void update(MUInfo muInfo) {
        entityManager.merge(muInfo);		
    }
}
