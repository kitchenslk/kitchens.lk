package com.janaka.kitchenslk.dao;

import com.janaka.kitchenslk.entity.SystemUser;
import com.janaka.kitchenslk.entity.TempSystemUser;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 12:30:44 PM
 * Project	: kitchenslk
 */

public interface SystemUserDAO {

	/**
	 * @param username
	 * @return
	 */
	public SystemUser getSystemUserByUserName(String username)throws Exception;

	
	/**
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public TempSystemUser getTempSystemUserByEncryptedUserName(String userName)throws Exception;

}
