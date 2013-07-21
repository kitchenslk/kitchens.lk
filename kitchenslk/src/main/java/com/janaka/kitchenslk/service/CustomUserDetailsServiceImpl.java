package com.janaka.kitchenslk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.janaka.kitchenslk.dao.SystemUserDAO;
import com.janaka.kitchenslk.entity.SystemUser;

@Service(value = "customUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private SystemUserDAO systemUserDAO;
	
	public CustomUserDetailsServiceImpl(){}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException, DataAccessException {		
		SystemUser systemUser = null;
		try {
			systemUser = systemUserDAO.getSystemUserByUserName(userName);
		} catch (Exception e) {
			// e.printStackTrace();
			throw new UsernameNotFoundException(userName);
		}
		return systemUser;
	}


}
