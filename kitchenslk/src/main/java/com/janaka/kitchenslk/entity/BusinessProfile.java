package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.List;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 11:01:12 AM
 * Project	: kitchenslk
 */
@Entity(name = "businessProfile")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Table(name = "BUSINESS_PROFILE")
public class BusinessProfile implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long businessProfileId;
	private String businessRegsitrationNumber;
    private String businessProfileName;
    private String businessProfileTitle;
    private UploadedFile profileImage;
    private UploadedFile businessLogo;
    private SystemUser businessOwner;
    private SystemUser contactPerson;
    private List<Kitchen> kitchens;    
    private Status status;
    private int versionId;
    private CommonDomainProperty commonDomainProperty;
    
    @Id
	@SequenceGenerator(name = "idsequence", sequenceName = "business_profile_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "BUSINESS_PROFILE_ID", length = 12)
    public long getBusinessProfileId() {
		return businessProfileId;
	}
	public void setBusinessProfileId(long businessProfileId) {
		this.businessProfileId = businessProfileId;
	}

	@Column(name="BUSINESS_REGSITRATION_NUMBER")
	public String getBusinessRegsitrationNumber() {
		return businessRegsitrationNumber;
	}
	public void setBusinessRegsitrationNumber(String businessRegsitrationNumber) {
		this.businessRegsitrationNumber = businessRegsitrationNumber;
	}

	@Column(name="BUSINESS_PROFILE_NAME")
	public String getBusinessProfileName() {
		return businessProfileName;
	}
	public void setBusinessProfileName(String businessProfileName) {
		this.businessProfileName = businessProfileName;
	}

	@Column(name="BUSINESS_PROFILE_TITLE")
	public String getBusinessProfileTitle() {
		return businessProfileTitle;
	}
	public void setBusinessProfileTitle(String businessProfileTitle) {
		this.businessProfileTitle = businessProfileTitle;
	}
	
	
	@OneToOne()
	@JoinColumn(name="PROFILE_IMAGE")
	public UploadedFile getProfileImage() {
		return profileImage;
	}
	public void setProfileImage(UploadedFile profileImage) {
		this.profileImage = profileImage;
	}
	
	@OneToOne()
	@JoinColumn(name="BUSINESS_LOGO")
	public UploadedFile getBusinessLogo() {
		return businessLogo;
	}
	public void setBusinessLogo(UploadedFile businessLogo) {
		this.businessLogo = businessLogo;
	}
	
	@ManyToOne()
	@JoinColumn(name="CONTACT_PERSON_ID")
	public SystemUser getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(SystemUser contactPerson) {
		this.contactPerson = contactPerson;
	}
	
	@ManyToOne()
	@JoinColumn(name="BUSINESS_OWNER_ID")
	public SystemUser getBusinessOwner() {
		return businessOwner;
	}
	public void setBusinessOwner(SystemUser businessOwner) {
		this.businessOwner = businessOwner;
	}
	
	
	@OneToMany(mappedBy="businessProfile",fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	public List<Kitchen> getKitchens() {
		return kitchens;
	}
	public void setKitchens(List<Kitchen> kitchens) {
		this.kitchens = kitchens;
	}
	
	@Enumerated(EnumType.STRING)
	@Column(name="STATUS")
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
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
        builder.append(this.businessProfileId);       
        return builder.toHashCode();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof BusinessProfile) {
			BusinessProfile other = (BusinessProfile) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(this.businessProfileId, other.businessProfileId);
            return builder.isEquals();
        }
        return false;
	}

}
