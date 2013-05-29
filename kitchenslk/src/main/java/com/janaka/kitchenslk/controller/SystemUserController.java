package com.janaka.kitchenslk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.janaka.kitchenslk.service.SystemUserService;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: May 23, 2013 - 11:05:27 PM
 * Project	: kitchenslk
 */
@Controller(value="systemUserController")
public class SystemUserController {
	
	@Autowired
	private SystemUserService systemUserService;
	
	@RequestMapping(value="/imageUpload", method=RequestMethod.GET)
	public String testPage(HttpServletRequest request){
		try {
			systemUserService.getSystemUserByUserName("test");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "imageUpload";
	}
	
	

}
