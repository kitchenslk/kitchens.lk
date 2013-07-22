package com.janaka.kitchenslk.util;


import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.CharEncoding;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.janaka.kitchenslk.dto.EmailTemplateDTO;
import com.janaka.kitchenslk.entity.TempNotification;
import com.janaka.kitchenslk.entity.TempSystemUser;
import com.janaka.kitchenslk.enums.EmailType;


/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 22, 2013 - 12:28:20 PM
 * Project	: kitchenslk
 */
@Component(value="emailMessageUtil")
public class EmailMessageUtilImpl implements EmailMessageUtil {
	
	@Autowired
    private VelocityEngine velocityEngine;
	
	@Autowired
	private Sender sender;
	
	@Override
	public <Entity> String getEmailMessageBodyByEmailType(EmailType emailType, Entity entity) throws Exception {
		Resource resource = new ClassPathResource("velocity_template.properties");
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		System.out.println("props.getProperty(emailType.toString()) " + props.getProperty(emailType.toString()));
		String velocityTemplateName=props.getProperty(emailType.toString());
		Map<String,Object> dataMap=getDataMapByEmailType(emailType,entity);
		return getEmailBodyMessageFromVelocityTemplate(dataMap, velocityTemplateName);
	}

	@Override
	public <Entity> String getEmailSubjectByEmailType(EmailType emailType) throws Exception {
		String emailSubject=null; 
		Resource resource = new ClassPathResource("velocity_template.properties");		
		Properties props = PropertiesLoaderUtils.loadProperties(resource);
		System.out.println("props.getProperty(emailType.toString()  + \"_TITLE\") " + props.getProperty(emailType.toString()  + "_TITLE"));
		emailSubject=props.getProperty(emailType.toString()  + "_TITLE");	
		System.out.println("emailSubject :" + emailSubject);
		return emailSubject;
	}
	
	@Override
	public String getEmailBodyMessageFromVelocityTemplate(Map<String, Object> dataMap, String velocityTemplateName) {
		return VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, velocityTemplateName,CharEncoding.UTF_8 , dataMap);
	}

	@Override
	public <Entity> Map<String, Object> getDataMapByEmailType(EmailType emailType, Entity entity) throws Exception {
		Map<String, Object> map=new HashMap<String, Object>();
		switch (emailType) {
		case SIGN_UP_CONFIRMATION:
			TempSystemUser tempSystemUser=(TempSystemUser) entity;
			map.put(ApplicationConstants.URL, ApplicationConstants.APP_URL);
			map.put(ApplicationConstants.ENCRYPTED_NOTIFIED_MAIL_ADDRES, tempSystemUser.getEncryptedTempUserName());
			map.put(ApplicationConstants.SENT_DATE, tempSystemUser.getCommonDomainProperty().getCreationDate());
			break;
		case SIGN_UP_SUCCESS:
			
			break;
		default:
			break;
		}
		return map;
	}
	
	@Override
	@Async
	public void prepareAndSendTempNotificationEmail(TempNotification tempNotification) throws Exception {
		EmailTemplateDTO emailTemplateDTO=tempNotification.createEmailTemplateDTO();
		emailTemplateDTO.setVelocityTemplateLocation(ApplicationConstants.MAIN_EMAIL_TEMPLATE_LOCATION);
		sender.sendEmailMessage(emailTemplateDTO);		
	}


}
