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
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.janaka.kitchenslk.enums.Status;

/**
 * @author : Nadeeshani Senevirathna 
 * Date/Time: May 25, 2013 - 12:17:00 PM
 * Project : kitchenslk
 */

@Entity(name = "kitchen")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "KITCHEN")
public class Kitchen implements Serializable {

	private static final long serialVersionUID = 1L;
		
	private long kitchenId;
	private String kitchenName;
	private Address address;
	private List<ContactNumberKitchen> contactNumbers;
	private List<EmailAddressKitchen> emailAddresses;
	private SystemUser kitchenManager;
	private List<Menu> menus;
	private List<Offer> offers;
	private List<Vacancy> vacancies;
	private List<Complain> complains;
	private BusinessProfile businessProfile;
	private Status status;
	private int versionId;
	private CommonDomainProperty commonDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "kitchen_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "KITCHEN_ID", length = 12)
	public long getKitchenId() {
		return kitchenId;
	}
	public void setKitchenId(long kitchenId) {
		this.kitchenId = kitchenId;
	}

	@Column(name="KITCHEN_NAME")
	public String getKitchenName() {
		return kitchenName;
	}
	public void setKitchenName(String kitchenName) {
		this.kitchenName = kitchenName;
	}

	@OneToOne()
	@JoinColumn(name="ADDRESS_ID")
	@Cascade(CascadeType.ALL)
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

	@OneToMany(mappedBy = "kitchen", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<ContactNumberKitchen> getContactNumbers() {
		return contactNumbers;
	}
	public void setContactNumbers(List<ContactNumberKitchen> contactNumbers) {
		this.contactNumbers = contactNumbers;
	}

	@OneToMany(mappedBy = "kitchen", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<EmailAddressKitchen> getEmailAddresses() {
		return emailAddresses;
	}	
	public void setEmailAddresses(List<EmailAddressKitchen> emailAddresses) {
		this.emailAddresses = emailAddresses;
	}

	@ManyToOne()
	@JoinColumn(name="KITCHEN_MANAGER_ID")
	@Cascade(CascadeType.MERGE)
	public SystemUser getKitchenManager() {
		return kitchenManager;
	}	
	public void setKitchenManager(SystemUser kitchenManager) {
		this.kitchenManager = kitchenManager;
	}

	@OneToMany(mappedBy = "kitchen", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	
	@OneToMany(mappedBy = "kitchen", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Offer> getOffers() {
		return offers;
	}
	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}
	
	
	@OneToMany(mappedBy = "kitchen", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Vacancy> getVacancies() {
		return vacancies;
	}
	public void setVacancies(List<Vacancy> vacancies) {
		this.vacancies = vacancies;
	}
	
	
	
	@OneToMany(mappedBy = "kitchen", fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.TRUE)
	public List<Complain> getComplains() {
		return complains;
	}
	public void setComplains(List<Complain> complains) {
		this.complains = complains;
	}
	
	
	@ManyToOne()
	@JoinColumn(name="BUSINESS_PROFILE_ID")
	@Cascade(CascadeType.MERGE)
	public BusinessProfile getBusinessProfile() {
		return businessProfile;
	}
	public void setBusinessProfile(BusinessProfile businessProfile) {
		this.businessProfile = businessProfile;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
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
		builder.append(this.kitchenId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Kitchen) {
			Kitchen other = (Kitchen) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.kitchenId, other.kitchenId);
			return builder.isEquals();
		}
		return false;
	}

}
