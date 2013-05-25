package com.janaka.kitchenslk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.kitchenslk.dao.SystemUserDAO;
import com.janaka.kitchenslk.entity.SystemUser;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 12:29:27 PM
 * Project	: kitchenslk
 */

@Service(value="systemUserService")
public class SystemUserServiceImpl implements SystemUserService {
	
	@Autowired
	private SystemUserDAO systemUserDAO;

	/* (non-Javadoc)
	 * @see com.janaka.kitchenslk.service.SystemUserService#getSystemUserByUserName(java.lang.String)
	 */
	@Override
	public SystemUser getSystemUserByUserName(String username) throws Exception {
		return systemUserDAO.getSystemUserByUserName(username);
	}

}
