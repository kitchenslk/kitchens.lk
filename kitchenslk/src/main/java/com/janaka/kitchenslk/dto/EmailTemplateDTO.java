package com.janaka.kitchenslk.dto;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 22, 2013 - 10:48:29 AM
 * Project	: kitchenslk
 */
public class EmailTemplateDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private String title;
	private String date;
	private String toName;
	private String message;
	private String url;
	private String encryptedUserName;
	private String[] toEmailAddressList;
	private String fromEmailAddress;
	private String velocityTemplateLocation;
	
		
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getToName() {
		return toName;
	}
	public void setToName(String toName) {
		this.toName = toName;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getEncryptedUserName() {
		return encryptedUserName;
	}
	public void setEncryptedUserName(String encryptedUserName) {
		this.encryptedUserName = encryptedUserName;
	}
	
		
	public String[] getToEmailAddressList() {
		return toEmailAddressList;
	}
	public void setToEmailAddressList(String[] toEmailAddressList) {
		this.toEmailAddressList = toEmailAddressList;
	}
	public String getFromEmailAddress() {
		return fromEmailAddress;
	}
	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}
	public String getVelocityTemplateLocation() {
		return velocityTemplateLocation;
	}
	public void setVelocityTemplateLocation(String velocityTemplateLocation) {
		this.velocityTemplateLocation = velocityTemplateLocation;
	}
	
	public Map<String, Object> toBasicMap() {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("title", title);
		map.put("date", date);
		map.put("toName", toName);
		map.put("message", message);
		map.put("url", url);
		map.put("encryptedUserName", encryptedUserName);
		return map;
	}
	
	

}
