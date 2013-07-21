package com.janaka.kitchenslk.service;

import com.janaka.kitchenslk.entity.SystemUser;
import com.janaka.kitchenslk.entity.TempSystemUser;

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

	/**
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public boolean checkIfUserNameExists(String userName)throws Exception;
	
	
	/**
	 * @return
	 * @throws Exception
	 */
	public TempSystemUser getTempSystemUserByEncryptedUserName(String userName)throws Exception;

	/**
	 * @param tempSystemUser
	 * @throws Exception
	 */
	public void registerUser(TempSystemUser tempSystemUser)throws Exception;

	/**
	 * @param encryptedUserName
	 * @return
	 * @throws Exception
	 */
	public SystemUser confirmTempUser(String encryptedUserName)throws Exception;

	

}
