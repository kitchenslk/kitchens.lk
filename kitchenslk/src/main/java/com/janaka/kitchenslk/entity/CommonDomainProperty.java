package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;

import com.janaka.kitchenslk.commons.CommonFunctions;
/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 11:11:02 AM
 * Project	: kitchenslk
 */
@Component
@Embeddable
public class CommonDomainProperty implements Serializable {

private static final long serialVersionUID = 1L;
	
	private Date creationDate;
	private Date lastModifiedDate;
	private SystemUser lastModifiedUser;
	private SystemUser createdUser;
	
	public CommonDomainProperty(Date now, SystemUser createdUser) {
		this.creationDate=now;
		this.createdUser=createdUser;
		this.lastModifiedDate=now;
		this.lastModifiedUser=createdUser;
	}
	
	public CommonDomainProperty() {
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}
	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}
	
	@OneToOne()
	@JoinColumn(name="LAST_MODIFIED_USER")
	public SystemUser getLastModifiedUser() {
		return lastModifiedUser;
	}
	public void setLastModifiedUser(SystemUser lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}
	
	@OneToOne()
	@JoinColumn(name="CREATED_USER")
	public SystemUser getCreatedUser() {
		return createdUser;
	}
	public void setCreatedUser(SystemUser createdUser) {
		this.createdUser = createdUser;
	}
	
	public Map<String,Object> toBasicMap() {
		Map<String,Object> map=new HashMap<String, Object>();
		SimpleDateFormat sdf=CommonFunctions.getGlobalSimpleDateFormat();
		map.put("creationDate", sdf.format(creationDate));
		map.put("lastModifiedDate", sdf.format(lastModifiedDate));
		if(!(createdUser==null)){
			map.put("createdUser", createdUser.getUserId());
		}
		if(!(createdUser==null)){
			map.put("lastModifiedUser", lastModifiedUser.getUserId());
		}
		return map;
	}

}
