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
 * Date/Time: May 25, 2013 - 6:48:03 PM
 * Project	: kitchenslk
 */
@Entity(name = "emailAddressKitchen")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Table(name = "EMAIL_ADDRESS_KITCHEN")
@PrimaryKeyJoinColumn(name="EMAIL_ADDRESS_ID")
public class EmailAddressKitchen extends EmailAddress implements Serializable{


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
			builder.append(this.getEmailAddressId());
			if(!(kitchen==null))
			builder.append(kitchen.getKitchenId());
			return builder.toHashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof EmailAddressKitchen) {
				EmailAddressKitchen other = (EmailAddressKitchen) obj;
				EqualsBuilder builder = new EqualsBuilder();
				builder.append(this.getEmailAddressId(), other.getEmailAddressId());
				if(!(kitchen==null || other.kitchen==null))
				builder.append(kitchen.getKitchenId(),other.kitchen.getKitchenId());
				return builder.isEquals();
			}
			return false;
		}

	
	

}
