package com.janaka.kitchenslk.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.brickred.socialauth.AuthProvider;
import org.brickred.socialauth.Contact;
import org.brickred.socialauth.SocialAuthManager;
import org.brickred.socialauth.spring.bean.SocialAuthTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
	
	@RequestMapping(method=RequestMethod.GET)
	public String getLoginPage(HttpServletRequest request){		
		return "login";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String getSignUpPage(HttpServletRequest request){		
		return "signUp";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String signUpUser(HttpServletRequest request){		
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

	

}
