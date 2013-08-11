package com.janaka.kitchenslk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
		
	@RequestMapping(value="/superadmindashboard",method=RequestMethod.GET)
	public ModelAndView getSuperAdminDashboard(HttpServletRequest request){		
		return new ModelAndView("superAdminDashboard");
	}
	
	@RequestMapping(value="/merchantdashboard",method=RequestMethod.GET)
	public ModelAndView getAttributeList(HttpServletRequest request){		
		return new ModelAndView("merchantDashboard");
	}
	

}
