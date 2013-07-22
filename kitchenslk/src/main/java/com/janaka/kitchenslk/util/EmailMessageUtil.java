package com.janaka.kitchenslk.util;

import java.util.Map;

import com.janaka.kitchenslk.entity.TempNotification;
import com.janaka.kitchenslk.enums.EmailType;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 22, 2013 - 12:25:27 PM
 * Project	: kitchenslk
 */
public interface EmailMessageUtil {
	
	/**
	 * @param emailType
	 * @return
	 * @throws Exception
	 */
	public <Entity> String getEmailMessageBodyByEmailType(EmailType emailType, Entity entity)throws Exception;
	
	/**
	 * @param emailType
	 * @return
	 * @throws Exception
	 */
	public <Entity> String getEmailSubjectByEmailType(EmailType emailType)throws Exception;
	
	/**
	 * @param dataMap
	 * @param velocityTemplateName
	 * @return
	 */
	public String getEmailBodyMessageFromVelocityTemplate(Map<String,Object> dataMap, String velocityTemplateName);
	
	/**
	 * @param emailType
	 * @param entity
	 * @return
	 * @throws Exception
	 */
	public <Entity> Map<String,Object> getDataMapByEmailType(EmailType emailType, Entity entity)throws Exception;

	/**
	 * @param tempNotification TODO
	 * @throws Exception
	 */
	public void prepareAndSendTempNotificationEmail(TempNotification tempNotification)throws Exception;

}
