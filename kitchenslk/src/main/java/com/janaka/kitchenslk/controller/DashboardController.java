package com.janaka.kitchenslk.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.janaka.kitchenslk.entity.SystemUser;
import com.janaka.kitchenslk.enums.UserRoleType;
import com.janaka.kitchenslk.util.SessionUtil;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jul 27, 2013 - 12:51:51 PM
 * Project	: kitchenslk
 */
@Controller(value="dashboardController")
public class DashboardController {
	
	@Autowired
	private SessionUtil sessionUtil;
	
	@RequestMapping(value="/dashboardredirect", method=RequestMethod.GET)
	public String getSignUpPage(HttpServletRequest request){	
		SystemUser currentUser=null;
		try {
			currentUser = sessionUtil.getCurentSystemUser();
			if(!(currentUser==null)){
				List<UserRoleType> roleTypes=currentUser.listAllUserRoleTypes();
				if(!(roleTypes==null)){
					for (UserRoleType userRoleType : roleTypes) {
						switch (userRoleType) {
						case ROLE_SUPERADMIN:
							return "redirect:superadmindashboard.htm";
						case ROLE_MERCHANT:
							return "redirect:merchantdashboard.htm";
						default:
							break;
						}
					}					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "redirect:login.htm";
	}
	
	@RequestMapping(value="/superadmindashboard",method=RequestMethod.GET)
	public ModelAndView getSuperAdminDashboard(HttpServletRequest request){		
		return new ModelAndView("superAdminDashboard");
	}
	
	@RequestMapping(value="/merchantdashboard",method=RequestMethod.GET)
	public ModelAndView getAttributeList(HttpServletRequest request){		
		return new ModelAndView("merchantDashboard");
	}
	

}
