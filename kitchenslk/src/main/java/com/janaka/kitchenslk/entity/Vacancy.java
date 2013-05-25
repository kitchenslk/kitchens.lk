package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import javax.persistence.OneToOne;
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

import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 25, 2013 - 8:22:23 PM
 * Project	: kitchenslk
 */
@Entity(name = "vacancy")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "VACANCY")
public class Vacancy implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long vacancyId;
	private String employerName;
	private String jobTitle;
	private String jobDescription;
	private Date openingDate;
	private Date closingDate;
	private UploadedFile vacancyAdvertisement;
	private List<String> responsibilities;
	private List<String> requiredQualifications;	
	private Kitchen kitchen;
	private Status status;
	private int versionId;
	private CommonDomainProperty commanDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "vacancy_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "VACANCY_ID", length = 12)
	public long getVacancyId() {
		return vacancyId;
	}
	public void setVacancyId(long vacancyId) {
		this.vacancyId = vacancyId;
	}

	@Column(name="EMPLOYER_NAME")
	public String getEmployerName() {
		return employerName;
	}
	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	@Column(name="JOB_TITLE",length=5000)
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	@Column(name="JOB_DESCRIPTION",length=50000)
	public String getJobDescription() {
		return jobDescription;
	}
	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VACANCY_OPENING_DATE")
	public Date getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(Date openingDate) {
		this.openingDate = openingDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="VACANCY_CLOSING_DATE")
	public Date getClosingDate() {
		return closingDate;
	}
	public void setClosingDate(Date closingDate) {
		this.closingDate = closingDate;
	}

	@OneToOne
	@JoinColumn(name="VACANCY_ADVERTISEMENT_ID")
	public UploadedFile getVacancyAdvertisement() {
		return vacancyAdvertisement;
	}
	public void setVacancyAdvertisement(UploadedFile vacancyAdvertisement) {
		this.vacancyAdvertisement = vacancyAdvertisement;
	}

	@ElementCollection
	@CollectionTable(name="VACANCY_RESPONSIBILITIES", joinColumns=@JoinColumn(name="VACANCY_ID"))
	@Column(name="RESPONSIBILITY",length=5000)
	public List<String> getResponsibilities() {
		return responsibilities;
	}
	public void setResponsibilities(List<String> responsibilities) {
		this.responsibilities = responsibilities;
	}

	@ElementCollection
	@CollectionTable(name="VACANCY_REQUIRED_QUALIFICATIONS", joinColumns=@JoinColumn(name="VACANCY_ID"))
	@Column(name="REQUIRED_QUALIFICATION",length=5000)
	public List<String> getRequiredQualifications() {
		return requiredQualifications;
	}
	public void setRequiredQualifications(List<String> requiredQualifications) {
		this.requiredQualifications = requiredQualifications;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
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
		builder.append(this.vacancyId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Vacancy) {
			Vacancy other = (Vacancy) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.vacancyId, other.vacancyId);
			return builder.isEquals();
		}
		return false;
	}


}
