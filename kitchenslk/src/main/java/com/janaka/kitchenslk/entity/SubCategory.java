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
import org.hibernate.annotations.GenericGenerator;

import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 7, 2013 - 10:26:02 AM
 * Project	: quizapp
 */
@Entity(name = "subCategory")
@Table(name = "SUB_CATEGORY")
public class SubCategory implements Serializable {

	private static final long serialVersionUID = 1L;
	private long subCategoryId;
	private String subCategoryName;
	private String subCategoryDescription;
	private Category category;
	private List<Menu> menus;
	private	Status status;	
	private int versionId;
	private	CommonDomainProperty commonDomainProperty;
	
		
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "sub_category_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "SUB_CATEGORY_ID", length = 12)
	public long getSubCategoryId() {
		return subCategoryId;
	}
	public void setSubCategoryId(long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}
	
	@Column(name="SUB_CATEGORY_NAME")
	public String getSubCategoryName() {
		return subCategoryName;
	}	
	public void setSubCategoryName(String subCategoryName) {
		this.subCategoryName = subCategoryName;
	}
	
	
	@Column(name="SUB_CATEGORY_DESCRIPTION", length=5000)
	public String getSubCategoryDescription() {
		return subCategoryDescription;
	}
	public void setSubCategoryDescription(String subCategoryDescription) {
		this.subCategoryDescription = subCategoryDescription;
	}

	
	@OneToMany(mappedBy="subCategory", fetch=FetchType.LAZY)
	@Cascade(CascadeType.ALL)
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
	@ManyToOne()
	@JoinColumn(name="CATEGORY_ID")
	@Cascade(CascadeType.MERGE)
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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
		builder.append(this.subCategoryId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SubCategory) {
			SubCategory other = (SubCategory) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.subCategoryId, other.subCategoryId);
			return builder.isEquals();
		}
		return false;
	}
	
	public Map<String,Object> toBasicMap() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("subCategoryId",subCategoryId);
		map.put("subCategoryName",subCategoryName);
		map.put("subCategoryDescription",subCategoryDescription);		
		return map;
	}
	
	public Map<String,Object> toCompleteMap() {
		Map<String,Object> map=this.toBasicMap();
		map.put("menus", constructMenuList());
		if(!(category==null)){
			map.put("category",category.toBasicMap());
		}
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
