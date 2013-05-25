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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 25, 2013 - 1:37:01 PM
 * Project	: kitchenslk
 */
@Entity(name = "menuCategory")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "MENU_CATEGORY")
public class MenuCategory implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private long menuCategoryId;
	private String menuCategoryName;
	private String description;
	private Status status;
	private int versionId;
	private CommonDomainProperty commanDomainProperty;
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "menu_category_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "MENU_CATEGORY_ID", length = 12)
	public long getMenuCategoryId() {
		return menuCategoryId;
	}
	public void setMenuCategoryId(long menuCategoryId) {
		this.menuCategoryId = menuCategoryId;
	}

	@Column(name="MENU_CATEGORY_NAME")
	public String getMenuCategoryName() {
		return menuCategoryName;
	}
	public void setMenuCategoryName(String menuCategoryName) {
		this.menuCategoryName = menuCategoryName;
	}

	@Column(name="DESCRIPTION",length=5000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
		builder.append(this.menuCategoryId);
		builder.append(this.menuCategoryName);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MenuCategory) {
			MenuCategory other = (MenuCategory) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.menuCategoryId, other.menuCategoryId);
			builder.append(this.menuCategoryName, other.menuCategoryName);
			return builder.isEquals();
		}
		return false;
	}


}
