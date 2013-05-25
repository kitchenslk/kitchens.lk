package com.janaka.kitchenslk.entity;

import java.io.Serializable;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.janaka.kitchenslk.enums.Status;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 25, 2013 - 12:35:02 PM
 * Project	: kitchenslk
 */
@Entity(name = "address")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "ADDRESS")
public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private long addressId;
	private String addressValue;
	private String streetNumber;
	private String streetName;
	private String townName;
	private String cityName;
	private String postcode;
	private double longitude;
	private double latitude;	
	private Status status;
	private int versionId;
	private CommonDomainProperty commanDomainProperty;
	
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "address_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "ADDRESS_ID", length = 12)
	public long getAddressId() {
		return addressId;
	}
	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	@Column(name="ADDRESS_VALUE")
	public String getAddressValue() {
		return addressValue;
	}
	public void setAddressValue(String addressValue) {
		this.addressValue = addressValue;
	}

	@Column(name="STREET_NUMBER")
	public String getStreetNumber() {
		return streetNumber;
	}
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	@Column(name="STREET_NAME")
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	@Column(name="TOWN_NAME")
	public String getTownName() {
		return townName;
	}
	public void setTownName(String townName) {
		this.townName = townName;
	}

	@Column(name="CITY_NAME")
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	@Column(name="POST_CODE")
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name="LONGITUDE")
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Column(name="LATITUDE")
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
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
		builder.append(this.addressId);
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Address) {
			Address other = (Address) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.addressId, other.addressId);
			return builder.isEquals();
		}
		return false;
	}


}
