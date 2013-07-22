package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.commons.CommonFunctions;
import com.janaka.kitchenslk.dto.EmailTemplateDTO;
import com.janaka.kitchenslk.enums.EmailType;
import com.janaka.kitchenslk.enums.NotificationType;
import com.janaka.kitchenslk.util.ApplicationConstants;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 22, 2013 - 11:42:17 AM
 * Project	: kitchenslk
 */
@Entity(name = "tempNotification")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "TEMP_NOTIFICATION")
@PrimaryKeyJoinColumn(name="NOTIFICATION_ID")
public class TempNotification extends Notification implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private TempSystemUser receiver;
	private String sendTo;
	private EmailType emailType;
	
	public TempNotification() {
	}
	
	public TempNotification(TempSystemUser tempSystemUser) {
		this.receiver=tempSystemUser;
		if(StringUtils.isNotEmpty(tempSystemUser.getEmailAddress())){
			this.sendTo=tempSystemUser.getEmailAddress();
			this.setNotificationType(NotificationType.EMAIL);
		}else if(StringUtils.isNotEmpty(tempSystemUser.getContactNumber())){
			this.sendTo=tempSystemUser.getContactNumber();
			this.setNotificationType(NotificationType.MOBILE);
		}
		this.setCommonDomainProperty(CommonFunctions.getCommonDomainPropertyForSavingEntity(null));		
		this.setSender(null);
		this.setSentDate(Calendar.getInstance().getTime());		
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RECEIVER")
	@Cascade(CascadeType.MERGE)
	public TempSystemUser getReceiver() {
		return receiver;
	}
	public void setReceiver(TempSystemUser receiver) {
		this.receiver = receiver;
	}

	@Column(name="SEND_TO")
	public String getSendTo() {
		return sendTo;
	}
	public void setSendTo(String sendTo) {
		this.sendTo = sendTo;
	}

	@Column(name="EMAIL_TYPE")
	@Enumerated(EnumType.STRING)
	public EmailType getEmailType() {
		return emailType;
	}
	public void setEmailType(EmailType emailType) {
		this.emailType = emailType;
	}

	public EmailTemplateDTO createEmailTemplateDTO() {
		EmailTemplateDTO emailTemplateDTO=new EmailTemplateDTO();
		emailTemplateDTO.setDate(CommonFunctions.getGlobalSimpleDateFormat().format(this.getSentDate()));
		emailTemplateDTO.setEncryptedUserName(this.getReceiver().getEncryptedTempUserName());
		emailTemplateDTO.setFromEmailAddress("info@kitchens.lk");
		emailTemplateDTO.setMessage(this.getMessage());
		emailTemplateDTO.setTitle(this.getSubject());
		emailTemplateDTO.setToEmailAddressList(constructToEmailAddresses());
		emailTemplateDTO.setToName(this.getReceiver().getTempUserName());
		emailTemplateDTO.setUrl(ApplicationConstants.APP_URL);
		return emailTemplateDTO;
	}

	private String[] constructToEmailAddresses() {
		String[] array={this.getReceiver().getEmailAddress()};
		return array;
	}
	
	

}
