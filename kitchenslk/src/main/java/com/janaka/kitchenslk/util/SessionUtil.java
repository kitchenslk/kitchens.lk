package com.janaka.kitchenslk.util;

import javax.servlet.http.HttpServletRequest;

import com.janaka.kitchenslk.entity.SystemUser;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 21, 2013 - 8:15:38 PM
 * Project	: kitchenslk
 */
public interface SessionUtil {
	
	/**
	 * @param classz
	 * @param request
	 * @param name
	 * @return
	 */
	public <Entity> Entity getObjectfromSession(HttpServletRequest request, String name)throws Exception;
	
	/**
	 * @param request
	 * @param name
	 * @param entity
	 */
	public <Entity>void addObjectToSession(HttpServletRequest request, String name,Entity entity)throws Exception;
	
	/**
	 * @param request
	 * @param name
	 */
	public void removeObjectFromSession(HttpServletRequest request, String name)throws Exception;
	
	
	/**
	 * @return
	 * @throws Exception
	 */
	public SystemUser getCurentSystemUser()throws Exception;

}
