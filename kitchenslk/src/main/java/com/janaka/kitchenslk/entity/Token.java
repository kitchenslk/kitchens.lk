package com.janaka.kitchenslk.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 27, 2013 - 10:53:06 PM
 * Project	: kitchenslk
 */
@Entity(name = "token")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@Table(name = "TOKEN")
public class Token implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private long tokenId;
	private String username;
	private String series;
	private String tokenValue;
	private Date date;
	private int versionId;
	
	public Token() {
    }
 
    public Token(PersistentRememberMeToken persistentRememberMeToken) {
        this.username = persistentRememberMeToken.getUsername();
        this.series = persistentRememberMeToken.getSeries();
        this.date = persistentRememberMeToken.getDate();
        this.tokenValue = persistentRememberMeToken.getTokenValue();
    }
	
	@Id
	@SequenceGenerator(name = "idsequence", sequenceName = "token_id", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idsequence")
	@Column(name = "TOKEN_ID", length = 12)
	public long getTokenId() {
		return tokenId;
	}
	public void setTokenId(long tokenId) {
		this.tokenId = tokenId;
	}
	
	@Column(name="USERNAME")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column(name="SERIES")
	public String getSeries() {
		return series;
	}
	public void setSeries(String series) {
		this.series = series;
	}
	
	@Column(name="TOKEN_VALUE")
	public String getTokenValue() {
		return tokenValue;
	}
	public void setTokenValue(String tokenValue) {
		this.tokenValue = tokenValue;
	}
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="DATE")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	@Version
	@Column(name="VERSION_ID")
	public int getVersionId() {
		return versionId;
	}
	public void setVersionId(int versionId) {
		this.versionId = versionId;
	}
	
	

}
