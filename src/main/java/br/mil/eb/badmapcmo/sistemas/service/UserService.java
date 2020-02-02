/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.mil.eb.badmapcmo.sistemas.service;

import br.mil.eb.badmapcmo.sistemas.dao.UserDao;
import br.mil.eb.badmapcmo.sistemas.domain.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author tenbenites
 * TODO terminar depois
 */
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService {
    
    
    @Autowired
    private UserDao dao;
    
    public User findUserByUsername(String username){
        return dao.getByUsername(username);
        
    }
    
    public void registerLastLogin(User user) {
		
		
		
		//dao.saveAndFlush(user);
	}
    
    public User findById(Long id) {

		return dao.getById(id);
	}

	public List<User> findUsers() {
		
		return dao.getAll();
	}

	public void createUser(User user) {
		
				
		String passwordHash = new BCryptPasswordEncoder().encode(user.getPassword());
		
		user.setPassword(passwordHash);
		
		dao.save(user);		
	}

	
}
