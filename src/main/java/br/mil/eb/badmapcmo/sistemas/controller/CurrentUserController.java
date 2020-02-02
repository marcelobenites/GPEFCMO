/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.controller;

import br.mil.eb.badmapcmo.sistemas.domain.CurrentUser;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author tenbenites
 */

@ControllerAdvice
public class CurrentUserController {
    //sempre testa se há um usuário autenticado do lado do cliente
    @ModelAttribute("currentUser")
    public CurrentUser getCurrentUserAdvice(Authentication authentication){
        //System.out.println("ESTABELECEU NOVO PRINCIPAL"+authentication.getPrincipal().toString());
        if(authentication==null)
            System.out.println("Authentication esta nula getCurrentUserAdvice");
        return (authentication == null)?null : (CurrentUser) authentication.getPrincipal();
    }
    
}
