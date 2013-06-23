package com.janaka.kitchenslk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	
	
	
	

}
