package com.janaka.kitchenslk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.enums.EmailType;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 22, 2013 - 10:58:16 AM
 * Project	: kitchenslk
 */
@Entity(name = "emailNotification")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "EMAIL_NOTIFICATION")
@PrimaryKeyJoinColumn(name="NOTIFICATION_ID")
public class EmailNotification extends Notification implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	private String senderEmail;
	private String receieverEmail;	
	private SystemUser receiver;
	private EmailType emailType;
	
	@Column(name="SENDER_EMAIL")
	public String getSenderEmail() {
		return senderEmail;
	}
	public void setSenderEmail(String senderEmail) {
		this.senderEmail = senderEmail;
	}
	
	@Column(name="RECEIEVER_EMAIL")
	public String getReceieverEmail() {
		return receieverEmail;
	}
	public void setReceieverEmail(String receieverEmail) {
		this.receieverEmail = receieverEmail;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RECEIVER")
	@Cascade(CascadeType.MERGE)
	public SystemUser getReceiver() {
		return receiver;
	}
	public void setReceiver(SystemUser receiver) {
		this.receiver = receiver;
	}
	
	@Column(name="EMAIL_TYPE")
	@Enumerated(EnumType.STRING)
	public EmailType getEmailType() {
		return emailType;
	}
	public void setEmailType(EmailType emailType) {
		this.emailType = emailType;
	}
	

}
