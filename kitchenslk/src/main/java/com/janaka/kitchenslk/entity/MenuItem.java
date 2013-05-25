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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
 * Date/Time: May 25, 2013 - 1:33:54 PM
 * Project	: kitchenslk
 */
@Entity(name = "menuItem")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "MENU_ITEM")
public class MenuItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long menuItemId;
	private String menuItemName;
	private String menuItemDescription;
	private List<MenuItemMealSize> menuItemMealSizes;
	private Menu menu;
	private Status status;
	private int versionId;
	private CommonDomainProperty commanDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "menu_item_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "MENU_ITEM_ID", length = 12)
	public long getMenuItemId() {
		return menuItemId;
	}
	public void setMenuItemId(long menuItemId) {
		this.menuItemId = menuItemId;
	}

	@Column(name="MENU_ITEM_NAME")
	public String getMenuItemName() {
		return menuItemName;
	}
	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	@Column(name="MENU_ITEM_DESCRIPTION",length=5000)
	public String getMenuItemDescription() {
		return menuItemDescription;
	}
	public void setMenuItemDescription(String menuItemDescription) {
		this.menuItemDescription = menuItemDescription;
	}

	
	@OneToMany(mappedBy="menuItemMealSizeId.menuItem",fetch=FetchType.LAZY,orphanRemoval=true)
	@Cascade(CascadeType.ALL)
	public List<MenuItemMealSize> getMenuItemMealSizes() {
		return menuItemMealSizes;
	}
	public void setMenuItemMealSizes(List<MenuItemMealSize> menuItemMealSizes) {
		this.menuItemMealSizes = menuItemMealSizes;
	}
	
	@ManyToOne()
	@Cascade(CascadeType.MERGE)
	@JoinColumn(name="MENU_ID", referencedColumnName="MENU_ID")
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
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
		builder.append(this.menuItemId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof MenuItem) {
			MenuItem other = (MenuItem) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.menuItemId, other.menuItemId);
			return builder.isEquals();
		}
		return false;
	}

}
