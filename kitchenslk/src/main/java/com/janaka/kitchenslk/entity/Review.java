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

import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 25, 2013 - 3:31:33 PM
 * Project	: kitchenslk
 */
@Entity(name = "review")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "REVIEW")
@Inheritance(strategy=InheritanceType.JOINED)
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long reviewId;
    private String reviewTitle;
    private double ratingValue;
    private String reviewText;
    private String reviewerName;
    private SystemUser reviewByUser;
	private Status status;
	private int versionId;
	private CommonDomainProperty commonDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "review_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "REVIEW_ID", length = 12)
	public long getReviewId() {
		return reviewId;
	}
	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}
	
	@Column(name="REVIEW_TITLE")
	public String getReviewTitle() {
		return reviewTitle;
	}
	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}
	
	@Column(name="RATING_VALUE")
	public double getRatingValue() {
		return ratingValue;
	}
	public void setRatingValue(double ratingValue) {
		this.ratingValue = ratingValue;
	}
	
	@Column(name="REVIEW_TEXT", length=1000)
	public String getReviewText() {
		return reviewText;
	}
	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	@Column(name="REVIEWER_NAME")
	public String getReviewerName() {
		return reviewerName;
	}
	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}
	
	
	@ManyToOne()
	@JoinColumn(name="REVIEW_BY_USER")
	@Cascade(value=CascadeType.MERGE)
	public SystemUser getReviewByUser() {
		return reviewByUser;
	}
	public void setReviewByUser(SystemUser reviewByUser) {
		this.reviewByUser = reviewByUser;
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
		builder.append(this.reviewId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Review) {
			Review other = (Review) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.reviewId, other.reviewId);
			return builder.isEquals();
		}
		return false;
	}

	

}
