package com.janaka.kitchenslk.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 25, 2013 - 5:08:53 PM
 * Project	: kitchenslk
 */
@Entity(name = "offerReview")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "OFFER_REVIEW")
@PrimaryKeyJoinColumn(name="REVIEW_ID")
public class OfferReview extends Review implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Offer offer;
	
	
	@ManyToOne()
	@JoinColumn(name="OFFER_ID")
	@Cascade(CascadeType.MERGE)
	public Offer getOffer() {
		return offer;
	}
	public void setOffer(Offer offer) {
		this.offer = offer;
	}

	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.getReviewId());
		if(!(offer==null))
		builder.append(offer.getOfferId());
		return builder.toHashCode();
	}

	

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof OfferReview) {
			OfferReview other = (OfferReview) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.getReviewId(), other.getReviewId());
			if(!(offer==null || other.offer==null))
			builder.append(offer.getOfferId(),other.offer.getOfferId());
			return builder.isEquals();
		}
		return false;
	}

}
