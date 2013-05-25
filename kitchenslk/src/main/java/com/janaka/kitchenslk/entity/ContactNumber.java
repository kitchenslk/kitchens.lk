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

import com.janaka.kitchenslk.enums.NotifyToContactStatus;
import com.janaka.kitchenslk.enums.PriorityStatus;

/**
 * @author nadeeshani
 *
 */
@Entity(name="contactNumber")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Table(name="CONTACT_NUMBER", uniqueConstraints=@UniqueConstraint(columnNames={"CONTACT_NUMBER_VALUE"}))
@Inheritance(strategy=InheritanceType.JOINED)
public class ContactNumber implements Serializable{

	private static final long serialVersionUID = 1L;

	private long contactNumberId;
	private String contactNumberValue;	
	private PriorityStatus contactNumberPriorityStatus;
	private NotifyToContactStatus notifyToContactStatus;
	private int versionId;
	private CommonDomainProperty commanDomainProperty;
	
	@Id   
    @SequenceGenerator(name = "idsequence", sequenceName = "contact_number_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
    @Column(name = "CONTACT_NUMBER_ID",length=12)
	public long getContactNumberId() {
		return contactNumberId;
	}
	public void setContactNumberId(long contactNumberId) {
		this.contactNumberId = contactNumberId;
	}
	
	@Column(name = "CONTACT_NUMBER_VALUE")
	public String getContactNumberValue() {
		return contactNumberValue;
	}
	public void setContactNumberValue(String contactNumberValue) {
		this.contactNumberValue = contactNumberValue;
	}
	
	@Enumerated(EnumType.STRING)
    @Column(name = "CONTACT_NUMBER_PRIORITY_STATUS")
	public PriorityStatus getContactNumberPriorityStatus() {
		return contactNumberPriorityStatus;
	}
	public void setContactNumberPriorityStatus(
			PriorityStatus contactNumberPriorityStatus) {
		this.contactNumberPriorityStatus = contactNumberPriorityStatus;
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
	@Column(name="VERSION_ID")
	public int getVersionId() {
		return versionId;
	}	
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	
	@Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "creationDate", column =
        @Column(name = "CREATION_DATE")),
        @AttributeOverride(name = "lastModifiedUser", column =
        @Column(name = "LAST_MODIFIED_USER")),
        @AttributeOverride(name = "lastModifiedDate", column =
        @Column(name = "LAST_MODIFIED_DATE"))
    })
    public CommonDomainProperty getCommanDomainProperty() {
        return commanDomainProperty;
    }   
	public void setCommanDomainProperty(CommonDomainProperty commanDomainProperty) {
        this.commanDomainProperty = commanDomainProperty;
    }  
	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.contactNumberId);
        builder.append(this.contactNumberValue);
        return builder.toHashCode();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ContactNumber) {
			ContactNumber other = (ContactNumber) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(this.contactNumberId, other.contactNumberId);
            builder.append(this.contactNumberValue, other.contactNumberValue);
            return builder.isEquals();
        }
        return false;
	}

	
}
