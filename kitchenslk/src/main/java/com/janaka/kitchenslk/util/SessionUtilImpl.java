package com.janaka.kitchenslk.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.janaka.kitchenslk.entity.SystemUser;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 21, 2013 - 8:14:31 PM
 * Project	: kitchenslk
 */
@Component(value="sessionUtil")
public class SessionUtilImpl implements SessionUtil{

	@Override
	public <Entity> Entity getObjectfromSession(HttpServletRequest request, String name) throws Exception {
		return (Entity) request.getSession().getAttribute(name);
	}

	@Override
	public <Entity> void addObjectToSession(HttpServletRequest request,	String name, Entity entity) throws Exception {
		request.getSession().setAttribute(name, entity);
		
	}

	@Override
	public void removeObjectFromSession(HttpServletRequest request, String name)throws Exception {
		request.getSession().removeAttribute(name);
	}
	
	@Override
	public SystemUser getCurentSystemUser() throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return (SystemUser) auth.getPrincipal();
		}
		return null;
	}
	
	

}
