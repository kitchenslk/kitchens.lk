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
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 13, 2013 - 10:29:21 PM
 * Project	: kitchenslk
 */
@Entity(name = "attribute")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "ATTRIBUTE")
public class Attribute implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long attributeId;
	private String attributeName;
	private String attributeDescription;
	private List<ItemAttribute> itemAttributes;
	private Status status;
	private int versionId;
	private CommonDomainProperty commonDomainProperty;
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "attribute_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "ATTRIBUTE_ID", length = 12)	
	public long getAttributeId() {
		return attributeId;
	}
	public void setAttributeId(long attributeId) {
		this.attributeId = attributeId;
	}

	@Column(name="ATTRIBUTE_NAME")
	public String getAttributeName() {
		return attributeName;
	}
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	@Column(name="ATTRIBUTE_DESCRIPTION", length=5000)
	public String getAttributeDescription() {
		return attributeDescription;
	}
	public void setAttributeDescription(String attributeDescription) {
		this.attributeDescription = attributeDescription;
	}
	
	
	@OneToMany(mappedBy="attribute")
	@Cascade(CascadeType.SAVE_UPDATE)
	public List<ItemAttribute> getItemAttributes() {
		return itemAttributes;
	}
	public void setItemAttributes(List<ItemAttribute> itemAttributes) {
		this.itemAttributes = itemAttributes;
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
		builder.append(this.attributeId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			Attribute other = (Attribute) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.attributeId, other.attributeId);
			return builder.isEquals();
		}
		return false;
	}
	
	public Map<String, Object> toBasicMap() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("attributeId", attributeId);
		map.put("attributeName", attributeName);
		map.put("attributeDescription", attributeDescription);
		map.put("status", status);
		map.put("commonDomainProperty", commonDomainProperty.toBasicMap());
		return map;
	}
	
	public List<Map<String,Object>> constructitemAttributeList(){
		if(!(itemAttributes==null|| itemAttributes.isEmpty())){
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			for (ItemAttribute attribute : itemAttributes) {
				list.add(attribute.toBasicMap());
			}
			return list;
		}
		return null;
	}


}
