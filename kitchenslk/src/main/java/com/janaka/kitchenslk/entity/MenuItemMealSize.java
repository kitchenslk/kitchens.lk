package com.janaka.kitchenslk.entity;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 25, 2013 - 2:02:52 PM
 * Project	: kitchenslk
 */
@Entity(name = "menuItemMealSize")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "MENU_ITEM_MEAL_SIZE")
@AssociationOverrides({
	@AssociationOverride(name = "menuItemMealSizeId.menuItem", joinColumns = @JoinColumn(name = "MENU_ITEM_ID")),
	@AssociationOverride(name = "menuItemMealSizeId.mealSize",	joinColumns = @JoinColumn(name = "MEAL_SIZE_ID")) 
})
public class MenuItemMealSize implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private MenuItemMealSizeId menuItemMealSizeId=new MenuItemMealSizeId();	
	private double price;
	private Status status;
	private int versionId;
	private CommonDomainProperty commanDomainProperty;
	
	
	@EmbeddedId
	public MenuItemMealSizeId getMenuItemMealSizeId() {
		return menuItemMealSizeId;
	}
	public void setMenuItemMealSizeId(MenuItemMealSizeId menuItemMealSizeId) {
		this.menuItemMealSizeId = menuItemMealSizeId;
	}
	
	
	@Transient	
	public MenuItem getMenuItem() {
		if(!(menuItemMealSizeId==null)){
			return menuItemMealSizeId.getMenuItem();
		}
		return null;
	}
	public void setMenuItem(MenuItem menuItem) {
		if(!(menuItemMealSizeId==null)){
			menuItemMealSizeId.setMenuItem(menuItem);			
		}
	}
	
	@Transient	
	public MealSize getMealSize() {
		if(!(menuItemMealSizeId==null)){
			return menuItemMealSizeId.getMealSize();
		}
		return null;
	}
	public void setMealSize(MealSize mealSize) {
		if(!(menuItemMealSizeId==null)){
			menuItemMealSizeId.setMealSize(mealSize);
		}
	}
	
	@Column(name="PRICE")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
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
		builder.append(this.menuItemMealSizeId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MenuItemMealSize) {
			MenuItemMealSize other = (MenuItemMealSize) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.menuItemMealSizeId, other.menuItemMealSizeId);
			return builder.isEquals();
		}
		return false;
	}

}
