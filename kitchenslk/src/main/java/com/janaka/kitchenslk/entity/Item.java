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
@Entity(name = "Item")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "ITEM")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long itemId;
	private String itemName;
	private String itemDescription;
	private UploadedFile itemImage;
	private Menu menu;
	private List<ItemType> itemTypes;
	private Status status;
	private int versionId;
	private CommonDomainProperty commonDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "item_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "IITEM_ID", length = 12)
	public long getItemId() {
		return itemId;
	}
	public void setItemId(long itemId) {
		this.itemId = itemId;
	}

	@Column(name="ITEM_NAME")
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	
	
	@Column(name="ITEM_DESCRIPTION",length=5000)
	public String getItemDescription() {
		return itemDescription;
	}
	public void setItemDescription(String itemDescription) {
		this.itemDescription = itemDescription;
	}
	
	
	@ManyToOne()
	@Cascade(CascadeType.ALL)
	@JoinColumn(name="ITEM_IMAGE_ID")
	public UploadedFile getItemImage() {
		return itemImage;
	}
	public void setItemImage(UploadedFile itemImage) {
		this.itemImage = itemImage;
	}
	
	@OneToMany(mappedBy="item")
	@Cascade(CascadeType.ALL)
	public List<ItemType> getItemTypes() {
		return itemTypes;
	}
	public void setItemTypes(List<ItemType> itemTypes) {
		this.itemTypes = itemTypes;
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
		builder.append(this.itemId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			Item other = (Item) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.itemId, other.itemId);
			return builder.isEquals();
		}
		return false;
	}
	
	public Map<String,Object> toBasicMap() {
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("itemId", itemId);
		map.put("itemName", itemName);
		map.put("itemDescription", itemDescription);
		map.put("status", status);
		if(!(commonDomainProperty==null)){
			map.put("commonDomainProperty", commonDomainProperty.toBasicMap());
		}
		return map;
	}
	
	public Map<String,Object> toCompleteMapWithoutParents() {
		Map<String,Object> map=this.toBasicMap();		
		map.put("itemAttributes", constructItemAttributes());	
		return map;
	}
	
	private List<Map<String,Object>> constructItemAttributes() {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		if(!(itemTypes==null || itemTypes.isEmpty())){
			for (ItemType itemType : itemTypes) {
				list.add(itemType.toCompleteMapWithoutParents());
			}	
		}		
		return list;
	}
	public Map<String,Object> toCompleteMap() {
		Map<String,Object> map=this.toCompleteMapWithoutParents();		
		if(!(menu==null)){
			map.put("menu", menu.toBasicMap());
		}
		return map;
	}

}
