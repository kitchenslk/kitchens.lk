package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.janaka.kitchenslk.commons.CommonFunctions;
import com.janaka.kitchenslk.enums.Status;
import com.janaka.kitchenslk.enums.UserRoleType;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 11:10:12 AM
 * Project	: kitchenslk
 */
@Entity(name = "systemUser")
@DynamicInsert(value=true)
@DynamicUpdate(value=true)
@Table(name = "SYSTEM_USER", uniqueConstraints = @UniqueConstraint(columnNames = "USER_NAME"))
public class SystemUser implements Serializable,UserDetails{

	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_PASSWORD = "123";
	
	private long userId;
	private String userName;
	private String password;
	private Set<UserRole> userRoles;
	private int versionId;
	private Status status; 
	private CommonDomainProperty commonDomainProperty;
	private TempSystemUser tempSystemUser;	
	private SystemUserDetail systemUserDetail;
	

	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "system_user_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "USER_ID", length = 12)
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "SYSTEM_USER_ROLES", joinColumns = @JoinColumn(name = "USER_ID"), inverseJoinColumns = @JoinColumn(name = "USER_ROLE_ID"))
	@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	@Version
	@Column(name = "VERSION_ID")
	public int getVersionId() {
		return versionId;
	}

	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "STATUS")
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	@JoinColumn(name = "TEMP_SYSTEM_USER")
	public TempSystemUser getTempSystemUser() {
		return tempSystemUser;
	}

	

	public void setTempSystemUser(TempSystemUser tempSystemUser) {
		this.tempSystemUser = tempSystemUser;
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

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "systemUser")
	@Cascade(org.hibernate.annotations.CascadeType.ALL)
	public SystemUserDetail getSystemUserDetail() {
		return systemUserDetail;
	}

	public void setSystemUserDetail(SystemUserDetail systemUserDetail) {
		this.systemUserDetail = systemUserDetail;
	}

	
	


	public Map<String, Object> toSystemUserMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("userName", userName);
		map.put("systemUserDetail", systemUserDetail.toSystemUserDetailMap());
		return map;
	}

	public Map<String, Object> toReverseSystemUserMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("userName", userName);
		return map;
	}



	public SystemUser() {}
	
	public SystemUser(TempSystemUser tempSystemUser) {
		this.commonDomainProperty=CommonFunctions.getCommonDomainPropertyForSavingEntity(this);
		this.password=tempSystemUser.getTempPassword();
		this.status=Status.ACTIVE;
		this.tempSystemUser=tempSystemUser;
		this.userName=tempSystemUser.getTempUserName();
		this.setUserRoles(constructBasicUserRoles());
	}

	private Set<UserRole> constructBasicUserRoles() {
		Set<UserRole> set=new HashSet<UserRole>();
		set.add(new UserRole(UserRoleType.ROLE_USER));
		return set;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(userId);
        builder.append(userName);
        return builder.toHashCode();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SystemUser) {
			SystemUser other = (SystemUser) obj;
            EqualsBuilder builder = new EqualsBuilder();
            builder.append(this.userId, other.userId);
            builder.append(this.userName, other.userName);
            return builder.isEquals();
        }
        return false;
	}

	@Transient
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return userRoles;
	}

	@Transient
	@Override
	public String getUsername() {
		return this.userName;
	}

	@Transient
	@Override
	public boolean isAccountNonExpired() {
		if(this.status==Status.ACTIVE){
			return true;
		}
		return false;
	}

	@Transient
	@Override
	public boolean isAccountNonLocked() {
		if(this.status==Status.ACTIVE){
			return true;
		}
		return false;
	}

	@Transient
	@Override
	public boolean isCredentialsNonExpired() {
		if(this.status==Status.ACTIVE){
			return true;
		}
		return false;
	}

	@Transient
	@Override
	public boolean isEnabled() {
		if(this.status==Status.ACTIVE){
			return true;
		}
		return false;
	}

	public boolean checkIfHasGivenUserRole(UserRoleType  userRoleType) {
		if(!(userRoles==null)){
			for (UserRole  userRole : userRoles) {
				if(userRole.getAuthority().equals(userRoleType.toString())){
					return true;
				}
			}
		}
		return false;
	}

	public List<UserRoleType> listAllUserRoleTypes() {
		if(!(userRoles==null)){
			List<UserRoleType> userRoleTypes=new ArrayList<UserRoleType>();
			for (UserRole  userRole : userRoles) {
				userRoleTypes.add(userRole.getUserRoleType());
			}
		}		
		return null;
	}
	
}
