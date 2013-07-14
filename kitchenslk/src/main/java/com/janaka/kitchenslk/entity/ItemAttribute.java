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
 * Date/Time: Jul 13, 2013 - 11:00:01 PM
 * Project	: kitchenslk
 */
@Entity(name = "itemAttribute")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "ITEM_ATTRIBUTE")
public class ItemAttribute implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long itemAttributeId;
	private Attribute attribute;
	private Item item;
	private String itemAttributeDescription;
	private List<ItemAttributeValue> itemAttributeValues;	
	private Status status;
	private int versionId;
	private CommonDomainProperty commanDomainProperty;
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "item_attribute_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "ITEM_ATTRIBUTE_ID", length = 12)		
	public long getItemAttributeId() {
		return itemAttributeId;
	}
	public void setItemAttributeId(long itemAttributeId) {
		this.itemAttributeId = itemAttributeId;
	}
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Cascade(CascadeType.SAVE_UPDATE)
	@JoinColumn(name="ATTRIBUTE_ID")
	public Attribute getAttribute() {
		return attribute;
	}
	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@Cascade(CascadeType.MERGE)
	@JoinColumn(name="ITEM_ID")
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	@Column(name="ITEM_ATTRIBUTE_DESCRIPTION", length=5000)
	public String getItemAttributeDescription() {
		return itemAttributeDescription;
	}
	public void setItemAttributeDescription(String itemAttributeDescription) {
		this.itemAttributeDescription = itemAttributeDescription;
	}
	
	@OneToMany(mappedBy="itemAttribute")
	@Cascade(value=CascadeType.SAVE_UPDATE)
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
		builder.append(this.itemAttributeId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			ItemAttribute other = (ItemAttribute) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.itemAttributeId, other.itemAttributeId);
			return builder.isEquals();
		}
		return false;
	}
	

}
