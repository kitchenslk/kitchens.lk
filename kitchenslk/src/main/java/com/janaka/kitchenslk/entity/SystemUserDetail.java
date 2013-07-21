package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.janaka.kitchenslk.commons.CommonFunctions;


/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 11:20:51 AM
 * Project	: kitchenslk
 */
@Entity(name = "systemUserDetail")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Table(name = "SYSTEM_USER_DETAIL")
public class SystemUserDetail implements Serializable {	

	private static final long serialVersionUID = 1L;
	private long userDetailId;
	private String firstName;
	private String lastName;		
	private List<EmailAddressSystemUser> emailAddresses;
	private List<ContactNumberSystemUser> contactNumbers;
	private SystemUser systemUser;
	private int versionId;
	private CommonDomainProperty commonDomainProperty;
	
	public SystemUserDetail() {
		// TODO Auto-generated constructor stub
	}
	
	public SystemUserDetail(TempSystemUser tempSystemUser,	SystemUser systemUser) {
		this.commonDomainProperty=CommonFunctions.getCommonDomainPropertyForSavingEntity(systemUser);
		this.systemUser=systemUser;
		if(StringUtils.isNotEmpty(tempSystemUser.getContactNumber())){
			this.contactNumbers=constructBasicContactNumbers(tempSystemUser.getContactNumber());
		}
		if(StringUtils.isNotEmpty(tempSystemUser.getEmailAddress())){
			this.emailAddresses=constructBasicEmailAddresses(tempSystemUser.getEmailAddress());
		}
		this.firstName=systemUser.getUsername();		
	}

	private List<EmailAddressSystemUser> constructBasicEmailAddresses(String emailAddress) {
		if(this.emailAddresses==null){
			this.emailAddresses=new ArrayList<EmailAddressSystemUser>();
		} 
		this.emailAddresses.add(new EmailAddressSystemUser(emailAddress,this));
		return this.emailAddresses;
	}

	private List<ContactNumberSystemUser> constructBasicContactNumbers(String contactNumber) {
		if(contactNumbers==null){
			contactNumbers=new ArrayList<ContactNumberSystemUser>();
		} 
		contactNumbers.add(new ContactNumberSystemUser(contactNumber,this));
		return contactNumbers;
	}

	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "system_user_detail_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "USER_DETAIL_ID", length = 12)
	public long getUserDetailId() {
		return userDetailId;
	}

	public void setUserDetailId(long userDetailId) {
		this.userDetailId = userDetailId;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

	@OneToMany(mappedBy = "userDetail", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<EmailAddressSystemUser> getEmailAddresses() {
		return emailAddresses;
	}
	public void setEmailAddresses(List<EmailAddressSystemUser> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	@OneToMany(mappedBy = "userDetail", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	public List<ContactNumberSystemUser> getContactNumbers() {
		return contactNumbers;
	}
	public void setContactNumbers(List<ContactNumberSystemUser> contactNumbers) {
		this.contactNumbers = contactNumbers;
	}
	
	
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SYSTEM_USER_ID")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	public SystemUser getSystemUser() {
		return systemUser;
	}	

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
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

	public Map<String,Object> toSystemUserDetailMap() {		
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userDetailId", userDetailId);	
		map.put("firstName", firstName);
		map.put("lastName", lastName);				
		return map;
	}

	public Map<String,Object> toReverseSystemUserDetailMap() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("userDetailId", userDetailId);
		map.put("firstName", firstName);
		map.put("lastName", lastName);		
		if(!(systemUser==null)){
			map.put("systemUser", systemUser.toReverseSystemUserMap());
		}	
		return map;
	}

	
	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(this.userDetailId);
        builder.append(this.systemUser);
        return builder.toHashCode();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SystemUserDetail) {
			SystemUserDetail other = (SystemUserDetail) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(this.userDetailId, other.userDetailId);
            builder.append(this.systemUser, other.systemUser);
            return builder.isEquals();
        }
        return false;
	}
}
