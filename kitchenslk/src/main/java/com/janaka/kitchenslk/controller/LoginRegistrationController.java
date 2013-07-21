package com.janaka.kitchenslk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.janaka.kitchenslk.entity.SystemUser;
import com.janaka.kitchenslk.entity.TempSystemUser;
import com.janaka.kitchenslk.service.SystemUserService;

/**
 * @author	: Nadeeshani Senevirathna
 * Date/Time: Jun 22, 2013 - 3:45:47 PM
 * Project	: kitchenslk
 */
@Controller(value="loginRegistrationController")
@RequestMapping("/login")
public class LoginRegistrationController {
	
	@Autowired
    private SocialAuthTemplate socialAuthTemplate;
	
	@Autowired
	private SystemUserService systemUserService;
	
	@RequestMapping(method=RequestMethod.GET)
	public String getLoginPage(HttpServletRequest request){		
		return "login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public ModelAndView getSignUpPage(HttpServletRequest request){		
		return new ModelAndView("signUp","tempSystemUser", new TempSystemUser());
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String signUpUser(HttpServletRequest request,@ModelAttribute("tempSystemUser") TempSystemUser tempSystemUser){
		try {
			systemUserService.registerUser(tempSystemUser);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "signUp";
	}
	
	@RequestMapping(value="/confirmtempuser", method=RequestMethod.GET)
	public String confirmTempUser(HttpServletRequest request,@RequestParam("eun") String encryptedUserName){
		try {
			systemUserService.confirmTempUser(encryptedUserName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "signUp";
	}
	
	@RequestMapping(value="/accessdenied", method=RequestMethod.GET)
	public String getAccessDeniedPage(HttpServletRequest request){		
		return "accessDenied";
	}
	
	

    @RequestMapping(value = "/authsuccess")
    public ModelAndView getRedirectURL(final HttpServletRequest request)
                    throws Exception {
            ModelAndView mv = new ModelAndView();
            List<Contact> contactsList = new ArrayList<Contact>();
            SocialAuthManager manager = socialAuthTemplate.getSocialAuthManager();
            AuthProvider provider = manager.getCurrentAuthProvider();
            contactsList = provider.getContactList();
            if (contactsList != null && contactsList.size() > 0) {
                    for (Contact p : contactsList) {
                            if (StringUtils.isEmpty(p.getFirstName()) && StringUtils.isEmpty(p.getLastName())) {
                                    p.setFirstName(p.getDisplayName());
                            }
                    }
            }
            mv.addObject("profile", provider.getUserProfile());
            mv.addObject("contacts", contactsList);
            mv.setViewName("authSuccess");
            return mv;
    }
    
    @RequestMapping(value="/checkifusernameexist", method=RequestMethod.GET)
	public @ResponseBody Map<String,Object> checkIfUserNameExists(
			@RequestParam("val") String userName,HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		try {
			map.put("status", systemUserService.checkIfUserNameExists(userName));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	

}
