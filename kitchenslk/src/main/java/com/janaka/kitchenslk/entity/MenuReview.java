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
@Entity(name = "menuReview")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "MENU_REVIEW")
@PrimaryKeyJoinColumn(name="REVIEW_ID")
public class MenuReview extends Review implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Menu menu;
	
	
	@ManyToOne()
	@JoinColumn(name="MENU_ID")
	@Cascade(CascadeType.MERGE)
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	
	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.getReviewId());
		if(!(menu==null))
		builder.append(menu.getMenuId());
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MenuReview) {
			MenuReview other = (MenuReview) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.getReviewId(), other.getReviewId());
			if(!(menu==null || other.menu==null))
			builder.append(menu.getMenuId(),other.menu.getMenuId());
			return builder.isEquals();
		}
		return false;
	}

}
