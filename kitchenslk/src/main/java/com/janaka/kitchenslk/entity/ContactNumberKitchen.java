package com.janaka.kitchenslk.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * Date/Time: May 25, 2013 - 7:09:39 PM
 * Project	: kitchenslk
 */
@Entity(name = "contactNumberKitchen")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Table(name = "CONTACT_NUMBER_KITCHEN")
@PrimaryKeyJoinColumn(name="CONTACT_NUMBER_ID")
public class ContactNumberKitchen extends ContactNumber implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Kitchen kitchen;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "KITCHEN_ID", nullable = false)
    @Cascade(CascadeType.MERGE)
	public Kitchen getKitchen() {
		return kitchen;
	}
	public void setKitchen(Kitchen kitchen) {
		this.kitchen = kitchen;
	}
	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.getContactNumberId());
		if(!(kitchen==null))
		builder.append(kitchen.getKitchenId());
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ContactNumberKitchen) {
			ContactNumberKitchen other = (ContactNumberKitchen) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.getContactNumberId(), other.getContactNumberId());
			if(!(kitchen==null || other.kitchen==null))
			builder.append(kitchen.getKitchenId(),other.kitchen.getKitchenId());
			return builder.isEquals();
		}
		return false;
	}

}
