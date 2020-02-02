/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.service;

import br.mil.eb.badmapcmo.sistemas.domain.CurrentUser;
import br.mil.eb.badmapcmo.sistemas.domain.User;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 *
 * @author tenbenites
 */
@Service
public class CurrentUserDetailsService implements UserDetailsService, ApplicationListener<AuthenticationSuccessEvent> {

	@Autowired
	private UserService userService;

        @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            System.out.println("RODANDO USUARIO, PROCURANDO POR..."+username);
            User user;		
            try {			
                user = userService.findUserByUsername(username);
            } catch (Exception ex) {
                System.out.println("Was not found user {" + username + "}");
                throw new UsernameNotFoundException("Was not found user {" + username + "}");
            }
            CurrentUser cusr = new CurrentUser(user);
            System.out.println("Usuario encontrado: "+cusr.getUsername());
            return cusr;
	}
	
        @Override
	public void onApplicationEvent(AuthenticationSuccessEvent event) {
            System.out.println("EVENTO DE AUTENTICAÇÃO, autenticando..."+((UserDetails) event.getAuthentication().getPrincipal()).getUsername());
            //TODO marcar para futura
            //String userName = ((UserDetails) event.getAuthentication().getPrincipal()).getUsername();      
            //User user = userService.findUserByUsername(userName);
            //user.setLastLogin(new Date());
            //userService.registerLastLogin(user);
     }
}
