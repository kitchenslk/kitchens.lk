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
import javax.persistence.OneToMany;
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
 * Date/Time: May 25, 2013 - 7:31:39 PM
 * Project	: kitchenslk
 */
@Entity(name = "offer")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "OFFER")
public class Offer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long offerId;
	private String offerTitle;
	private String offerDescription;
	private UploadedFile offerImage;
	private List<String> offerTerms;
	private Date offerAddedDate;
	private Date offerActivationDate;
	private Date offerExpirationDate;
	private double actualPrice;
	private double reducedPrice;
	private double reducedPercentage;
	private List<OfferReview> offerReviews;
	private Kitchen kitchen;
	private Status status;
	private int versionId;
	private CommonDomainProperty commonDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "offer_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "OFFER_ID", length = 12)
	public long getOfferId() {
		return offerId;
	}
	public void setOfferId(long offerId) {
		this.offerId = offerId;
	}

	@Column(name="OFFER_TITLE", length=5000)
	public String getOfferTitle() {
		return offerTitle;
	}
	public void setOfferTitle(String offerTitle) {
		this.offerTitle = offerTitle;
	}

	@Column(name="OFFER_DESCRIPTION", length=10000)
	public String getOfferDescription() {
		return offerDescription;
	}
	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}

	@OneToOne()
	@JoinColumn(name="OFFER_IMAGE_ID")
	@Cascade(CascadeType.ALL)
	public UploadedFile getOfferImage() {
		return offerImage;
	}
	public void setOfferImage(UploadedFile offerImage) {
		this.offerImage = offerImage;
	}

	@ElementCollection
	@CollectionTable(name="OFFER_TERMS", joinColumns=@JoinColumn(name="OFFER_ID"))
	@Column(name="OFFER_TERM",length=5000)
	public List<String> getOfferTerms() {
		return offerTerms;
	}
	public void setOfferTerms(List<String> offerTerms) {
		this.offerTerms = offerTerms;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OFFER_ADDED_DATE")
	public Date getOfferAddedDate() {
		return offerAddedDate;
	}
	public void setOfferAddedDate(Date offerAddedDate) {
		this.offerAddedDate = offerAddedDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OFFER_ACTIVATION_DATE")
	public Date getOfferActivationDate() {
		return offerActivationDate;
	}
	public void setOfferActivationDate(Date offerActivationDate) {
		this.offerActivationDate = offerActivationDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="OFFER_EXPIRATION_DATE")
	public Date getOfferExpirationDate() {
		return offerExpirationDate;
	}
	public void setOfferExpirationDate(Date offerExpirationDate) {
		this.offerExpirationDate = offerExpirationDate;
	}
	
	
	@Column(name="ACTUAL_PRICE")
	public double getActualPrice() {
		return actualPrice;
	}
	public void setActualPrice(double actualPrice) {
		this.actualPrice = actualPrice;
	}

	@Column(name="REDUCED_PRICE")
	public double getReducedPrice() {
		return reducedPrice;
	}
	public void setReducedPrice(double reducedPrice) {
		this.reducedPrice = reducedPrice;
	}

	@Column(name="REDUCED_PERCENTAGE")
	public double getReducedPercentage() {
		return reducedPercentage;
	}
	public void setReducedPercentage(double reducedPercentage) {
		this.reducedPercentage = reducedPercentage;
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
	
	@OneToMany(mappedBy="offer",fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	public List<OfferReview> getOfferReviews() {
		return offerReviews;
	}
	public void setOfferReviews(List<OfferReview> offerReviews) {
		this.offerReviews = offerReviews;
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
		builder.append(this.offerId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Offer) {
			Offer other = (Offer) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.offerId, other.offerId);
			return builder.isEquals();
		}
		return false;
	}


}
