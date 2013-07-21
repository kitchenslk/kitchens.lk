package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 6, 2013 - 10:51:00 PM
 * Project	: quizapp
 */
@Entity(name = "category")
@Table(name = "CATEGORY")
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	private String categoryId;
	private String categoryName;
	private String categoryDescription;
	private List<Menu> menus;
	private	Status status;	
	private int versionId;
	private	CommonDomainProperty commonDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "category_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "CATEGORY_ID", length = 12)
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	
	@Column(name = "CATEGORY_NAME")
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
	@Column(name = "CATEGORY_DESCRIPTION", length=5000)
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
	@OneToMany(mappedBy="category")	
	@Cascade(CascadeType.ALL)
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
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
		builder.append(this.categoryId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Complain) {
			Category other = (Category) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.categoryId, other.categoryId);
			return builder.isEquals();
		}
		return false;
	}
	
	public Map<String,Object> toBasicMap() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("categoryId", categoryId);
		map.put("categoryName", categoryName);
		map.put("categoryDescription", categoryDescription);
		map.put("status", status);
		if(!(commonDomainProperty==null)){
			map.put("commonDomainProperty", commonDomainProperty.toBasicMap());
		}		
		return map;
	}
	
	public Map<String,Object> toCompletecMap() {
		Map<String,Object> map=this.toBasicMap();
		map.put("menus", constructMenuList());		
		return map;
	}
	
	private List<Map<String,Object>> constructMenuList() {
		if(!(menus==null)){
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			for (Menu menu : menus) {
				list.add(menu.toBasicMap());
			}
			return list;
		}
		return null;
	}
	
	
	

}
