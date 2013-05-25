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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
 * Date/Time: May 25, 2013 - 1:52:58 PM
 * Project	: kitchenslk
 */
@Entity(name = "mealSize")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "MEAL_SIZE", uniqueConstraints = @UniqueConstraint(columnNames = "MEAL_SIZE_NAME"))
public class MealSize implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long mealSizeId;
	private String mealSizeName;
	private String description;	
	private List<MenuItemMealSize> menuItemMealSizes;
	private Status status;
	private int versionId;
	private CommonDomainProperty commanDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "meal_size_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "MEAL_SIZE_ID", length = 12)
	public long getMealSizeId() {
		return mealSizeId;
	}
	public void setMealSizeId(long mealSizeId) {
		this.mealSizeId = mealSizeId;
	}

	@Column(name="MEAL_SIZE_NAME")
	public String getMealSizeName() {
		return mealSizeName;
	}
	public void setMealSizeName(String mealSizeName) {
		this.mealSizeName = mealSizeName;
	}

	@Column(name="DESCRIPTION",length=5000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	@OneToMany(mappedBy="menuItemMealSizeId.mealSize",fetch=FetchType.LAZY)
	@Cascade(CascadeType.SAVE_UPDATE)
	public List<MenuItemMealSize> getMenuItemMealSizes() {
		return menuItemMealSizes;
	}
	public void setMenuItemMealSizes(List<MenuItemMealSize> menuItemMealSizes) {
		this.menuItemMealSizes = menuItemMealSizes;
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
		builder.append(this.mealSizeName);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MealSize) {
			MealSize other = (MealSize) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.mealSizeName, other.mealSizeName);
			return builder.isEquals();
		}
		return false;
	}

}
