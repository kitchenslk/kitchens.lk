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

import com.janaka.kitchenslk.commons.CommonFunctions;
import com.janaka.kitchenslk.enums.NotifyToContactStatus;
import com.janaka.kitchenslk.enums.PriorityStatus;

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
	
	public EmailAddressSystemUser() {
		// TODO Auto-generated constructor stub
	}
	
	public EmailAddressSystemUser(String emailAddress,SystemUserDetail systemUserDetail) {
		this.setCommonDomainProperty(CommonFunctions.getCommonDomainPropertyForSavingEntity(systemUserDetail.getSystemUser()));
		this.setEmailPriorityStatus(PriorityStatus.PRIMARY);
		this.setEmailAddressValue(emailAddress);
		this.setNotifyToContactStatus(NotifyToContactStatus.YES);
		this.setUserDetail(systemUserDetail);
	}
	
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
