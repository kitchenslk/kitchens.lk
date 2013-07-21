package com.janaka.kitchenslk.entity;

import java.io.Serializable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.commons.CommonFunctions;
import com.janaka.kitchenslk.enums.NotifyToContactStatus;
import com.janaka.kitchenslk.enums.PriorityStatus;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 11:20:51 AM
 * Project	: kitchenslk
 */
@Entity(name = "emailAddress")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Table(name = "EMAIL_ADDRESS", uniqueConstraints = @UniqueConstraint(columnNames = { "EMAIL_ADDRESS_VALUE" }))
@Inheritance(strategy=InheritanceType.JOINED)
public class EmailAddress implements Serializable {
	
	public EmailAddress() {
		// TODO Auto-generated constructor stub
	}
	
	public EmailAddress(String emailAddressValue) {
		this.emailAddressValue = emailAddressValue;
	}
	
    private static final long serialVersionUID = 1L;
    
    private long emailAddressId;
    private String emailAddressValue;    
    private PriorityStatus emailPriorityStatus;
    private NotifyToContactStatus notifyToContactStatus;
    private int versionId;
    private CommonDomainProperty commonDomainProperty;

    @Id
    @SequenceGenerator(name = "idsequence", sequenceName = "email_address_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
    @Column(name = "EMAIL_ADDRESS_ID", length = 12)
    public long getEmailAddressId() {
	return emailAddressId;
    }

    public void setEmailAddressId(long emailAddressId) {
	this.emailAddressId = emailAddressId;
    }

    @Column(name = "EMAIL_ADDRESS_VALUE")
    //@Pattern(regexp = ".+@.+\\.[a-z]+")
    public String getEmailAddressValue() {
	return emailAddressValue;
    }

    public void setEmailAddressValue(String emailAddressValue) {
	this.emailAddressValue = emailAddressValue;
    }

    

    @Enumerated(EnumType.STRING)
    @Column(name = "EMAIL_PRIORITY_STATUS")
    public PriorityStatus getEmailPriorityStatus() {
	return emailPriorityStatus;
    }

    public void setEmailPriorityStatus(PriorityStatus emailPriorityStatus) {
	this.emailPriorityStatus = emailPriorityStatus;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "NOTIFY_TO_CONTACT_STATUS")
    public NotifyToContactStatus getNotifyToContactStatus() {
	return notifyToContactStatus;
    }

    public void setNotifyToContactStatus(NotifyToContactStatus notifyToContactStatus) {
	this.notifyToContactStatus = notifyToContactStatus;
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

	public static EmailAddress fromSystemUserDetail(SystemUser user) {
		// TODO Auto-generated method stub
		return new EmailAddress(user);
	}
	
	private EmailAddress(SystemUser user){
		this.emailAddressValue = user.getUserName();
		this.emailPriorityStatus = PriorityStatus.PRIMARY;
		this.commonDomainProperty = CommonFunctions.getCommonDomainPropertyForSavingEntity(user);
		this.notifyToContactStatus = NotifyToContactStatus.YES;
	}
	
	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.emailAddressId);
        builder.append(this.emailAddressValue);
        return builder.toHashCode();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EmailAddress) {
			EmailAddress other = (EmailAddress) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(this.emailAddressId, other.emailAddressId);
            builder.append(this.emailAddressValue, other.emailAddressValue);
            return builder.isEquals();
        }
        return false;
	}
	
}
