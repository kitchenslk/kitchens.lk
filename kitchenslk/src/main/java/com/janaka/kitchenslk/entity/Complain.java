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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.enums.ComplainType;
import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 25, 2013 - 8:50:18 PM
 * Project	: kitchenslk
 */
@Entity(name = "complain")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "COMPLAIN")
public class Complain implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	private long complainId;
	private String complainerName;
	private String title;
	private String description;
	private ComplainType complainType;
	private ContactNumber complainerContactNumber;
	private EmailAddress complainerEmailAddress;
    private SystemUser complainer;    
	private Kitchen kitchen;
	private Status status;
	private int versionId;
	private CommonDomainProperty commanDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "complain_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "COMPLAIN_ID", length = 12)
	public long getComplainId() {
		return complainId;
	}
	public void setComplainId(long complainId) {
		this.complainId = complainId;
	}

	@Column(name="COMPLAINER_NAME")
	public String getComplainerName() {
		return complainerName;
	}
	public void setComplainerName(String complainerName) {
		this.complainerName = complainerName;
	}

	@Column(name="COMPLAIN_TITLE", length=5000)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name="COMPLAIN_DESCRIPTION", length=50000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	@Enumerated(EnumType.STRING)
	@Column(name="COMPLAIN_TYPE")
	public ComplainType getComplainType() {
		return complainType;
	}
	public void setComplainType(ComplainType complainType) {
		this.complainType = complainType;
	}

	@ManyToOne()
	@JoinColumn(name="COMPLAINER_CONTACT_NUMBER_ID")
	@Cascade(CascadeType.ALL)
	public ContactNumber getComplainerContactNumber() {
		return complainerContactNumber;
	}
	public void setComplainerContactNumber(ContactNumber complainerContactNumber) {
		this.complainerContactNumber = complainerContactNumber;
	}

	@ManyToOne()
	@JoinColumn(name="COMPLAINER_EMAIL_ADDRESS_ID")
	@Cascade(CascadeType.ALL)
	public EmailAddress getComplainerEmailAddress() {
		return complainerEmailAddress;
	}
	public void setComplainerEmailAddress(EmailAddress complainerEmailAddress) {
		this.complainerEmailAddress = complainerEmailAddress;
	}

	@ManyToOne()
	@JoinColumn(name="COMPLAINER_ID")
	@Cascade(CascadeType.ALL)
	public SystemUser getComplainer() {
		return complainer;
	}
	public void setComplainer(SystemUser complainer) {
		this.complainer = complainer;
	}

	@ManyToOne()
	@JoinColumn(name="KITCHEN_ID")
	@Cascade(CascadeType.MERGE)
	public Kitchen getKitchen() {
		return kitchen;
	}
	public void setKitchen(Kitchen kitchen) {
		this.kitchen = kitchen;
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
			@AttributeOverride(name = "lastModifiedUser", column = @Column(name = "LAST_MODIFIED_USER")),
			@AttributeOverride(name = "lastModifiedDate", column = @Column(name = "LAST_MODIFIED_DATE")) })
	public CommonDomainProperty getCommanDomainProperty() {
		return commanDomainProperty;
	}

	public void setCommanDomainProperty(
			CommonDomainProperty commanDomainProperty) {
		this.commanDomainProperty = commanDomainProperty;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.complainId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Complain) {
			Complain other = (Complain) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.complainId, other.complainId);
			return builder.isEquals();
		}
		return false;
	}


}
