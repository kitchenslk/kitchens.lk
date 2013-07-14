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
	private ItemAttribute itemAttribute;
	private ItemAttributeValue parentItemAttributeValue;
	private List<ItemAttributeValue> itemAttributeValues;
	private String itemAttributeValueDescription;
	private Status status;
	private int versionId;
	private CommonDomainProperty commanDomainProperty;
	
	
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
	
	@Column(name="ITEM_ATTRIBUTE_VALUE_DESCRIPTION", length=5000)
	public String getItemAttributeValueDescription() {
		return itemAttributeValueDescription;
	}
	public void setItemAttributeValueDescription(
			String itemAttributeValueDescription) {
		this.itemAttributeValueDescription = itemAttributeValueDescription;
	}
	
	
	@Column(name="VALUE_TEXT")
	public String getValue() {
		return value;
	}	
	public void setValue(String value) {
		this.value = value;
	}
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="ITEM_ATTRIBUTE_ID")
	@Cascade(CascadeType.MERGE)	
	public ItemAttribute getItemAttribute() {
		return itemAttribute;
	}
	public void setItemAttribute(ItemAttribute itemAttribute) {
		this.itemAttribute = itemAttribute;
	}
	
	
	@ManyToOne(optional=true, fetch=FetchType.LAZY)
	@JoinColumn(name="PARENT_ITEM_ATTRIBUTE_VALUE_ID")
	@Cascade(CascadeType.MERGE)	
	public ItemAttributeValue getParentItemAttributeValue() {
		return parentItemAttributeValue;
	}
	public void setParentItemAttributeValue(ItemAttributeValue parentItemAttributeValue) {
		this.parentItemAttributeValue = parentItemAttributeValue;
	}
	
	
	
	@OneToMany(mappedBy="parentItemAttributeValue", fetch=FetchType.LAZY)
	@Cascade(CascadeType.SAVE_UPDATE)
	public List<ItemAttributeValue> getItemAttributeValues() {
		return itemAttributeValues;
	}
	public void setItemAttributeValues(List<ItemAttributeValue> itemAttributeValues) {
		this.itemAttributeValues = itemAttributeValues;
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

}
