package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.enums.NotificationMessageStatus;
import com.janaka.kitchenslk.enums.NotificationType;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 22, 2013 - 10:59:30 AM
 * Project	: kitchenslk
 */
@Entity(name = "notification")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "NOTIFICATION")
@Inheritance(strategy=InheritanceType.JOINED)
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long notificationId;   
    private Date sentDate;
    private String message;
	private String subject;
    private NotificationMessageStatus notificationMessageStatus;    
    private int noOfRetries;
    private String failedReason;
    private Date lastTriedDate;    
    private SystemUser sender;
	private NotificationType notificationType;
    private int versionId;
    private CommonDomainProperty commonDomainProperty;
    
    
    @Id
	@SequenceGenerator(name = "idsequence", sequenceName = "notification_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "NOTIFICATION_ID", length = 12)
    public long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="SENT_DATE")
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	
	@Column(name="MESSAGE", length=50000)
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Column(name="SUBJECT", length=5000)
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	@Column(name="NOTIFICATION_MESSAGE_STATUS")
	@Enumerated(EnumType.STRING)
	public NotificationMessageStatus getNotificationMessageStatus() {
		return notificationMessageStatus;
	}
	public void setNotificationMessageStatus(
			NotificationMessageStatus notificationMessageStatus) {
		this.notificationMessageStatus = notificationMessageStatus;
	}
	
	@Column(name="NO_OF_RETRIES")
	public int getNoOfRetries() {
		return noOfRetries;
	}
	public void setNoOfRetries(int noOfRetries) {
		this.noOfRetries = noOfRetries;
	}
	
	@Column(name="FAILED_REASON")
	public String getFailedReason() {
		return failedReason;
	}
	public void setFailedReason(String failedReason) {
		this.failedReason = failedReason;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LAST_TRIED_DATE")
	public Date getLastTriedDate() {
		return lastTriedDate;
	}
	public void setLastTriedDate(Date lastTriedDate) {
		this.lastTriedDate = lastTriedDate;
	}
	
		
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="SENDER")
	@Cascade(CascadeType.MERGE)
	public SystemUser getSender() {
		return sender;
	}
	public void setSender(SystemUser sender) {
		this.sender = sender;
	}
	
	@Column(name="NOTIFICATION_TYPE")
	@Enumerated(EnumType.STRING)
	public NotificationType getNotificationType() {
		return notificationType;
	}
	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}
	
	@Version
	@Column(name = "VERSION_ID")
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "creationDate", column = @Column(name = "CREATION_DATE")),
			@AttributeOverride(name = "createdUser", column = @Column()),
			@AttributeOverride(name = "lastModifiedUser", column = @Column()),
			@AttributeOverride(name = "lastModifiedDate", column = @Column(name = "LAST_MODIFIED_DATE")) })
	public CommonDomainProperty getCommonDomainProperty() {
		return commonDomainProperty;
	}
	public void setCommonDomainProperty(CommonDomainProperty commonDomainProperty) {
		this.commonDomainProperty = commonDomainProperty;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.notificationId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Review) {
			Notification other = (Notification) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.notificationId, other.notificationId);
			return builder.isEquals();
		}
		return false;
	}

}
