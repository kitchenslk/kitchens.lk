package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import javax.persistence.OneToOne;
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
 * Date/Time: May 25, 2013 - 1:10:00 PM
 * Project	: kitchenslk
 */
@Entity(name = "menu")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "MENU")
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long menuId;
	private String menuName;
	private UploadedFile menuImage;
	private String description;
	private double price;
	private Category category;
	private List<Item> items;
	private List<MenuReview> menuReviews;
	private Kitchen kitchen;
	private Status status;
	private int versionId;
	private CommonDomainProperty commonDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "menu_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "MENU_ID", length = 12)
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}

	@Column(name="MENU_NAME")
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	@OneToOne()
	@JoinColumn(name="MENU_IMAGE_ID")
	public UploadedFile getMenuImage() {
		return menuImage;
	}
	public void setMenuImage(UploadedFile menuImage) {
		this.menuImage = menuImage;
	}
	
	@Column(name="DESCRIPTION",length=5000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name="PRICE")
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	@ManyToOne()
	@JoinColumn(name="CATEGORY_ID")
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@OneToMany(mappedBy="menu",fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	
	@OneToMany(mappedBy="menu",fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	public List<MenuReview> getMenuReviews() {
		return menuReviews;
	}	
	public void setMenuReviews(List<MenuReview> menuReviews) {
		this.menuReviews = menuReviews;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="KITCHEN_ID")
	@Cascade(CascadeType.MERGE)
	public Kitchen getKitchen() {
		return kitchen;
	}	
	public void setKitchen(Kitchen kitchen) {
		this.kitchen = kitchen;
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
		builder.append(this.menuId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Menu) {
			Menu other = (Menu) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.menuId, other.menuId);
			return builder.isEquals();
		}
		return false;
	}
	
	public Map<String,Object> toBasicMap() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("menuId", menuId);
		map.put("menuName", menuName);
		if(!(menuImage==null)){
			map.put("menuImage", menuImage.toBasicMap());
		}		
		map.put("description", description);
		map.put("price", price);
		if(!(category==null)){
			map.put("category;", category.toBasicMap());
		}		
		map.put("status", status);	
		if(!(commonDomainProperty==null)){
			map.put("commonDomainProperty", commonDomainProperty.toBasicMap());	
		}
		return map;
	}


}
