package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.HashMap;
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
 * Date/Time: Jul 13, 2013 - 10:37:23 PM
 * Project	: kitchenslk
 */
@Entity(name = "itemAttributeValue")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "ITEM_ATTRIBUTE_VALUE")
public class ItemAttributeValue implements Serializable{
	

	private static final long serialVersionUID = 1L;
	
	private int itemAttributeValueId;
	private String value;
	private Attribute attribute;
	private ItemType itemType;
	//private ItemAttributeValue parentItemAttributeValue;
	//private List<ItemAttributeValue> itemAttributeValues;
	private String description;
	private Status status;
	private int versionId;
	private CommonDomainProperty commonDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "item_attribute_value_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "ITEM_ATTRIBUTE_VALUE_ID", length = 12)	
	public int getItemAttributeValueId() {
		return itemAttributeValueId;
	}
	public void setItemAttributeValueId(int itemAttributeValueId) {
		this.itemAttributeValueId = itemAttributeValueId;
	}
	
	@Column(name="DESCRIPTION", length=5000)
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_TYPE_ID")
	@Cascade(CascadeType.MERGE)	
	public ItemType getItemType() {
		return itemType;
	}	
	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}
	
	
	@Column(name="VALUE_TEXT")
	public String getValue() {
		return value;
	}	
	public void setValue(String value) {
		this.value = value;
	}
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="ATTRIBUTE_ID")
	@Cascade(CascadeType.MERGE)	
	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	
//	@ManyToOne(optional=true, fetch=FetchType.LAZY)
//	@JoinColumn(name="PARENT_ITEM_ATTRIBUTE_VALUE_ID")
//	@Cascade(CascadeType.MERGE)	
//	public ItemAttributeValue getParentItemAttributeValue() {
//		return parentItemAttributeValue;
//	}	
//	public void setParentItemAttributeValue(ItemAttributeValue parentItemAttributeValue) {
//		this.parentItemAttributeValue = parentItemAttributeValue;
//	}
//	
//	
//	
//	@OneToMany(mappedBy="parentItemAttributeValue", fetch=FetchType.LAZY)
//	@Cascade(CascadeType.SAVE_UPDATE)
//	public List<ItemAttributeValue> getItemAttributeValues() {
//		return itemAttributeValues;
//	}
//	public void setItemAttributeValues(List<ItemAttributeValue> itemAttributeValues) {
//		this.itemAttributeValues = itemAttributeValues;
//	}
	
	
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
		builder.append(this.itemAttributeValueId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			ItemAttributeValue other = (ItemAttributeValue) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.itemAttributeValueId, other.itemAttributeValueId);
			return builder.isEquals();
		}
		return false;
	}
	
	public Map<String, Object> toBasicMap() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("itemAttributeValueId", itemAttributeValueId);
		map.put("value", value);
		map.put("description", description);
		map.put("status", status);
		if(!(commonDomainProperty==null)){
			map.put("commonDomainProperty", commonDomainProperty.toBasicMap());
		}				
		return map;
	}
	
	public Map<String, Object> toCompleteMapWithoutParents() {
		Map<String, Object> map=this.toBasicMap();
//		if(!(parentItemAttributeValue==null)){
//			map.put("parentItemAttributeValue", parentItemAttributeValue.toBasicMap());
//		}
		//map.put("itemAttributeValues", constructChildItemAttributeValueList());		
		return map;
	}
	
	public Map<String, Object> toCompleteMap() {
		Map<String, Object> map=this.toCompleteMapWithoutParents();		
		if(!(attribute==null)){
			map.put("itemAttribute", attribute.toBasicMap());
		}
//		if(!(parentItemAttributeValue==null)){
//			map.put("parentItemAttributeValue", parentItemAttributeValue.toBasicMap());
//		}
		return map;
	}
	
//	public List<Map<String,Object>> constructChildItemAttributeValueList(){
//		if(!(itemAttributeValues==null|| itemAttributeValues.isEmpty())){
//			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
//			for (ItemAttributeValue attributeValue : itemAttributeValues) {
//				list.add(attributeValue.toBasicMap());
//			}
//			return list;
//		}
//		return null;
//	}

}
