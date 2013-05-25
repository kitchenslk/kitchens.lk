package com.janaka.kitchenslk.service;

import com.janaka.kitchenslk.entity.SystemUser;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 12:27:13 PM
 * Project	: kitchenslk
 */

public interface SystemUserService {

	/**
	 * @param username
	 * @return
	 */
	public SystemUser getSystemUserByUserName(String username)throws Exception;

	

}
