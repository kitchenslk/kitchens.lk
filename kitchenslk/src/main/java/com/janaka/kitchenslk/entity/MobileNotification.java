package com.janaka.kitchenslk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 22, 2013 - 11:35:37 AM
 * Project	: kitchenslk
 */
@Entity(name = "mobileNotification")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "MOBILE_NOTIFICATION")
@PrimaryKeyJoinColumn(name="NOTIFICATION_ID")
public class MobileNotification extends Notification implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String senderContactNumber;
	private String receieveContactNumber;
	private SystemUser receiver;
	
	@Column(name="SENDER_CONTACT_NUMBER")
	public String getSenderContactNumber() {
		return senderContactNumber;
	}
	public void setSenderContactNumber(String senderContactNumber) {
		this.senderContactNumber = senderContactNumber;
	}
	
	@Column(name="RECEIEVE_CONTACT_NUMBER")
	public String getReceieveContactNumber() {
		return receieveContactNumber;
	}
	public void setReceieveContactNumber(String receieveContactNumber) {
		this.receieveContactNumber = receieveContactNumber;
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
	
	

}
