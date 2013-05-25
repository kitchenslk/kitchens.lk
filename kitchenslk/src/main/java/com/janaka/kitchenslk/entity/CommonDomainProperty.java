package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.stereotype.Component;
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
	private long lastModifiedUser;
	
	public CommonDomainProperty() {}
	
	public CommonDomainProperty(Date creationDate) {
		this.creationDate = creationDate;
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

	public long getLastModifiedUser() {
		return lastModifiedUser;
	}

	public void setLastModifiedUser(long lastModifiedUser) {
		this.lastModifiedUser = lastModifiedUser;
	}

}
