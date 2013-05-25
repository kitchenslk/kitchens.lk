package com.janaka.kitchenslk.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 25, 2013 - 6:37:32 PM
 * Project	: kitchenslk
 */
@Entity(name = "emailAddressSystemUser")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Table(name = "EMAIL_ADDRESS_SYSTEM_USER")
@PrimaryKeyJoinColumn(name="EMAIL_ADDRESS_ID")
public class EmailAddressSystemUser extends EmailAddress implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private SystemUserDetail systemUserDetail;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_DETAIL_ID", nullable = false)
    @Cascade(CascadeType.MERGE)
    public SystemUserDetail getUserDetail() {
	return systemUserDetail;
    }
    public void setUserDetail(SystemUserDetail systemUserDetail) {
	this.systemUserDetail = systemUserDetail;
    }
    
    @Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
		builder.append(this.getEmailAddressId());
		if(!(systemUserDetail==null))
		builder.append(systemUserDetail.getUserDetailId());
		return builder.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EmailAddressSystemUser) {
			EmailAddressSystemUser other = (EmailAddressSystemUser) obj;
			EqualsBuilder builder = new EqualsBuilder();
			builder.append(this.getEmailAddressId(), other.getEmailAddressId());
			if(!(systemUserDetail==null || other.systemUserDetail==null))
			builder.append(systemUserDetail.getUserDetailId(),other.systemUserDetail.getUserDetailId());
			return builder.isEquals();
		}
		return false;
	}

}
