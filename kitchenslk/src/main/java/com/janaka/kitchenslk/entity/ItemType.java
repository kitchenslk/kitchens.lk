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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 28, 2013 - 1:08:30 PM
 * Project	: kitchenslk
 */
@Entity(name = "itemType")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "ITEM_TYPE")
public class ItemType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long itemTypeId;
	private Item item;
	private String itemTypeName;
	private String itemTypeDescription;
	private UploadedFile itemTypeImage;	
	private List<ItemAttributeValue> itemAttributeValues;
	private Status status;
	private int versionId;
	private CommonDomainProperty commonDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "item_type_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "IITEM_TYPE_ID", length = 12)
	public long getItemTypeId() {
		return itemTypeId;
	}
	public void setItemTypeId(long itemTypeId) {
		this.itemTypeId = itemTypeId;
	}
	
	
	@ManyToOne()
	@Cascade(CascadeType.MERGE)
	@JoinColumn(name="ITEM_ID")
	public Item getItem() {
		return item;
	}
	public void setItem(Item item) {
		this.item = item;
	}
	
	@OneToMany(mappedBy="itemType")
	@Cascade(CascadeType.SAVE_UPDATE)
	public List<ItemAttributeValue> getItemAttributeValues() {
		return itemAttributeValues;
	}
	public void setItemAttributeValues(List<ItemAttributeValue> itemAttributeValues) {
		this.itemAttributeValues = itemAttributeValues;
	}
	
	@Column(name="ITEM_TYPE_NAME")	
	public String getItemTypeName() {
		return itemTypeName;
	}	
	public void setItemTypeName(String itemTypeName) {
		this.itemTypeName = itemTypeName;
	}
	
	@Column(name="ITEM_TYPE_DESCRIPTION", length=5000)	
	public String getItemTypeDescription() {
		return itemTypeDescription;
	}
	public void setItemTypeDescription(String itemTypeDescription) {
		this.itemTypeDescription = itemTypeDescription;
	}
	
	@ManyToOne()
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="ITEM_TYPE_IMAGE_ID")
	public UploadedFile getItemTypeImage() {
		return itemTypeImage;
	}
	public void setItemTypeImage(UploadedFile itemTypeImage) {
		this.itemTypeImage = itemTypeImage;
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
	
	public Map<String,Object> toBasicMap() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("itemTypeId", itemTypeId);
		map.put("item", item.toBasicMap());
		return map;
	}
	
	public Map<String, Object> toCompleteMapWithoutParents() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("itemTypeId", itemTypeId);
		map.put("itemAttributes", constructItemAttributes());
		return map;
	}
	
	private List<Map<String,Object>> constructItemAttributes() {
		if(!(itemAttributeValues==null || itemAttributeValues.isEmpty())){
			List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
			for (ItemAttributeValue itemAttributeValue : itemAttributeValues) {
				list.add(itemAttributeValue.toCompleteMapWithoutParents());
			}
			return list;
		}
		return null;
	}

}
