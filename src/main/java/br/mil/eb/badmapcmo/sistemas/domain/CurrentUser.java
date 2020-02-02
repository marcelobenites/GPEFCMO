/*
 *  B ADM CMO - Base Administrativa do Comando Militar do Oeste
 *  Desenvolvido por STI (Seção de Tecnologia da Informação)
 *  2019 - Todos os Direitos Reservados
 */
package br.mil.eb.badmapcmo.sistemas.domain;

/**
 *
 * @author ten-benites
 */


import br.mil.eb.badmapcmo.sistemas.domain.enumeration.UserClassEnum;
import org.springframework.security.core.authority.AuthorityUtils;

public class CurrentUser extends org.springframework.security.core.userdetails.User {
	
    private User user;
    

    public CurrentUser(User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getClasse().toString()));
        this.user = user;
    }
    
    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public UserClassEnum getClasse() {
        return user.getClasse();
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CurrentUser{" + "user=" + user + '}';
    }

    
    
    
}
