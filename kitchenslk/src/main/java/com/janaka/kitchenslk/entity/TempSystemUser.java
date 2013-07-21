package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.commons.CommonFunctions;
import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 11:10:12 AM
 * Project	: kitchenslk
 */

@Entity(name = "tempSystemUser")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Table(name = "TEMP_SYSTEM_USER", uniqueConstraints =@UniqueConstraint(columnNames = "TEMP_USER_NAME"))
public class TempSystemUser implements Serializable {
	
    private static final long serialVersionUID = 1L;
    private long tempUserId;
    private String tempUserName;
    private String emailAddress;
    private String contactNumber;
    private String encryptedTempUserName;
    private String tempPassword;
    private Set<UserRole> userRoles;
    private int versionId;
    private Status status;
    private String recordId;
    private CommonDomainProperty commonDomainProperty;
    private SystemUser systemUser;
    
    public TempSystemUser() {}

    @Id
    @SequenceGenerator(name = "idsequence", sequenceName = "temp_system_user_id", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
    @Column(name = "TEMP_USER_ID", length = 12)
    public long getTempUserId() {
        return tempUserId;
    }

    public void setTempUserId(long tempUserId) {
        this.tempUserId = tempUserId;
    }

    @Column(name = "TEMP_USER_NAME")
    public String getTempUserName() {
        return tempUserName;
    }
    public void setTempUserName(String tempUserName) {
        this.tempUserName = tempUserName;
    }
    
    @Column(name = "EMAIL_ADDRESS")
    public String getEmailAddress() {
		return emailAddress;
	}
    public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
    
    @Column(name = "CONTACT_NUMBER")
    public String getContactNumber() {
		return contactNumber;
	}
    public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

    @Column(name = "TEMP_PASSWORD")
    public String getTempPassword() {
        return tempPassword;
    }
    public void setTempPassword(String tempPassword) {
        this.tempPassword = tempPassword;
    }

    @Column(name = "RECORD_ID")
    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinTable(name = "TEMP_SYSTEM_USER_ROLES", joinColumns =
    @JoinColumn(name = "TEMP_USER_ID"), inverseJoinColumns =
    @JoinColumn(name = "USER_ROLE_ID"))
    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Version
    @Column(name = "VERSION_ID")
    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")   
    public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

   
    @OneToOne(mappedBy = "tempSystemUser",fetch=FetchType.LAZY)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    public SystemUser getSystemUser() {
        return systemUser;
    }	

	public void setSystemUser(SystemUser systemUser) {
        this.systemUser = systemUser;
    }

       
    
    @Column(name = "ENCRYPTED_TEMP_USER_NAME", length=5000)
    public String getEncryptedTempUserName() {
		return encryptedTempUserName;
	}
	public void setEncryptedTempUserName(String encryptedTempUserName) {
		this.encryptedTempUserName = encryptedTempUserName;
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
    
    /**
     * Does a shallow copy of {@link TempSystemUser} to a newly created {@link SystemUser}.<br><br>
     * Sets the {@link UserAccountStatus} - UserAccountStatus.ACTIVE
     * 
     * @return {@link SystemUser} 
     */
    public SystemUser copyCredentialsToNewSystemUser(){
    	
    	SystemUser systemUser = new SystemUser();
		systemUser.setUserName(this.getTempUserName());
		systemUser.setPassword(this.getTempPassword());
		systemUser.setStatus(Status.ACTIVE);
		systemUser.setTempSystemUser(this);	
		
    	return systemUser;
    }
    
 

	public static TempSystemUser fromSystemUser(SystemUser systemUser) {		
		return new TempSystemUser(systemUser);
	}
	
	private TempSystemUser(SystemUser systemUser){
		this.tempUserName = systemUser.getUserName();
		this.tempPassword = SystemUser.DEFAULT_PASSWORD;
		this.recordId = CommonFunctions.generateTempUserRecordId(tempPassword);
		this.commonDomainProperty = systemUser.getCommonDomainProperty();
		this.status = Status.ACTIVE;
		this.systemUser = systemUser;
	}
	
	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(tempUserId);
        builder.append(tempUserName);
        return builder.toHashCode();
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof TempSystemUser) {
			TempSystemUser other = (TempSystemUser) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(this.tempUserId, other.tempUserId);
            builder.append(this.tempUserName, other.tempUserName);
            return builder.isEquals();
        }
        return false;
	}
}
