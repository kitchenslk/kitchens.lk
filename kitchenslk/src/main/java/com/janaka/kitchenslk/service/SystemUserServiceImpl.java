package com.janaka.kitchenslk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.janaka.kitchenslk.commons.CommonFunctions;
import com.janaka.kitchenslk.dao.SystemUserDAO;
import com.janaka.kitchenslk.dto.EmailTemplateDTO;
import com.janaka.kitchenslk.entity.SystemUser;
import com.janaka.kitchenslk.entity.SystemUserDetail;
import com.janaka.kitchenslk.entity.TempNotification;
import com.janaka.kitchenslk.entity.TempSystemUser;
import com.janaka.kitchenslk.enums.NotificationMessageStatus;
import com.janaka.kitchenslk.enums.Status;
import com.janaka.kitchenslk.util.ApplicationConstants;
import com.janaka.kitchenslk.util.EmailMessageUtil;
import com.janaka.kitchenslk.util.EncryptionUtil;
import com.janaka.kitchenslk.util.Sender;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 12:29:27 PM
 * Project	: kitchenslk
 */

@Service(value="systemUserService")
public class SystemUserServiceImpl implements SystemUserService {
	
	@Autowired
	private SystemUserDAO systemUserDAO;
	
	@Autowired
	private EncryptionUtil encryptionUtil;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private EmailMessageUtil emailMessageUtil;
	
	@Autowired
	private Sender sender;

	/* (non-Javadoc)
	 * @see com.janaka.kitchenslk.service.SystemUserService#getSystemUserByUserName(java.lang.String)
	 */
	@Override
	public SystemUser getSystemUserByUserName(String username) throws Exception {
		return systemUserDAO.getSystemUserByUserName(username);
	}

	@Override
	public boolean checkIfUserNameExists(String userName) throws Exception {
		userName=encryptionUtil.encrypt(userName);
		TempSystemUser tempSystemUser=getTempSystemUserByEncryptedUserName(userName);
		if(!(tempSystemUser==null)){
			return true;
		}
		return false;
	}
	
	@Override
	public TempSystemUser getTempSystemUserByEncryptedUserName(String userName)throws Exception{
		return systemUserDAO.getTempSystemUserByEncryptedUserName(userName);
	}
	
	@Override
	public TempSystemUser registerUser(TempSystemUser tempSystemUser) throws Exception {
		tempSystemUser.setRecordId(CommonFunctions.generateTempUserRecordId(tempSystemUser.getTempPassword()));
		tempSystemUser.setTempPassword(encryptionUtil.encrypt(tempSystemUser.getTempPassword()));
		tempSystemUser.setEncryptedTempUserName(encryptionUtil.encrypt(tempSystemUser.getTempUserName()));
		tempSystemUser.setStatus(Status.PENDING);		
		tempSystemUser.setCommonDomainProperty(CommonFunctions.getCommonDomainPropertyForSavingEntity(null));
		TempNotification tempNotification=tempSystemUser.createSignUpNotification();		
		tempNotification.setMessage(emailMessageUtil.getEmailMessageBodyByEmailType(tempNotification.getEmailType(), tempSystemUser));
		tempNotification.setSubject(emailMessageUtil.getEmailSubjectByEmailType(tempNotification.getEmailType()));
		long id=commonService.createEntity(tempSystemUser);
		if(!(id==0)){
			emailMessageUtil.prepareAndSendTempNotificationEmail(tempNotification);
		}
		return tempSystemUser;
	}
	
	@Override
	public SystemUser confirmTempUser(String encryptedUserName)	throws Exception {
		TempSystemUser tempSystemUser=getTempSystemUserByEncryptedUserName(encryptedUserName);
		if(tempSystemUser.getStatus().equals(Status.PENDING)&& tempSystemUser.getSystemUser()==null){
			SystemUser systemUser=new SystemUser(tempSystemUser);
			tempSystemUser.setSystemUser(systemUser);
			tempSystemUser.setStatus(Status.ACTIVE);
			SystemUserDetail systemUserDetail=new SystemUserDetail(tempSystemUser, systemUser);
			systemUser.setSystemUserDetail(systemUserDetail);
			commonService.createEntity(systemUser);
			return systemUser;
		}else{
			return tempSystemUser.getSystemUser();
		}	
	}

}
