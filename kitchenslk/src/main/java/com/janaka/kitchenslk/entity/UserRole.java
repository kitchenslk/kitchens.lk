package com.janaka.kitchenslk.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import com.janaka.kitchenslk.enums.UserRoleType;


/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 11:25:10 AM
 * Project	: kitchenslk
 */
@Entity(name = "userRole")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Table(name = "USER_ROLE")
public class UserRole implements GrantedAuthority, Serializable {

	private static final long serialVersionUID = 1L;
	private int userRoleId;
	private UserRoleType userRoleType;
	private int versionId;
	
	public UserRole() {}
	
	public UserRole(UserRoleType userRoleType ) {
		this.userRoleType = userRoleType;
	}
	
	@Id
	@Column(name = "USER_ROLE_ID")
	@SequenceGenerator(name = "idsequence", sequenceName = "user_role_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	public int getUserRoleId() {
		return userRoleId;
	}
	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "USER_ROLE_TYPE")
	public UserRoleType getUserRoleType() {
		return userRoleType;
	}
	public void setUserRoleType(UserRoleType userRoleType) {
		this.userRoleType = userRoleType;
	}

	@Version
	@Column(name = "VERSION_ID")
	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userRoleType == null) ? 0 : userRoleType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		UserRole other = (UserRole) obj;
		if (userRoleType != other.userRoleType) {
			return false;
		}
		return true;
	}

	@Transient
	@Override
	public String getAuthority() {
		return userRoleType.toString();
	}
	
}
