/*
 *  B Adm Ap CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por STI (Seção de Tecnologia da Informação)
 *  2019 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.dao;

import java.util.List;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interface para as classes de repositório do sistema.
 * @author TEN BENITES
 */
@Repository
@Transactional
public interface DaoInterface<T> {
    /**
     * Salva novo registro de pessoa no banco de dados
     */
    public void save(T t);

    /**
     * Atualiza um registro no banco de dados passando o objeto da entidade
     */
    public void update(T t);		
   
    /**
     * Remove um registro do banco de dados pelo Id
     */
    public void remove(Long id);

    /**
     * Retorna todos os registros do banco de dados
     */
    @Transactional(readOnly = true)
    public List<T> getAll();      
    
    /**
     * Recebe um Id como parâmetro e retorna o registro correspondente
     */
    @Transactional(readOnly = true)
    public T getById(Long id);       
    
    /**
     * Recebe um objeto da classe como parâmetro e verifica a integridade dos 
     * dados para um novo registro e retorna a mensagem de erro mais adequada
     * para cada caso em que os dados não estejam íntegros, ou null caso esteja
     * tudo certo.
     */
    @Transactional(readOnly = true)
    public String verifyIntegrity(T t);       
    
}
