package com.janaka.kitchenslk.util;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.janaka.kitchenslk.commons.CommonFunctions;
import com.janaka.kitchenslk.entity.SystemUser;
import com.janaka.kitchenslk.service.SystemUserService;

@Component(value = "loginSuccessHandler")
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
   @Autowired
   private CommonFunctions commonFunctions;
   

   @Autowired
   private SystemUserService systemUserService;
    
    private AuthenticationSuccessHandler target = new SavedRequestAwareAuthenticationSuccessHandler();
    
    public LoginSuccessHandler(){}

   
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException,
            ServletException {
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            UserDetails userDetails = (UserDetails) (principal instanceof UserDetails ? principal : null);
            //If the user has logged in
            if (userDetails != null) {
                try {
                    HttpSession session = request.getSession();      
                                       
                                     
                    //Construct the name and store it in the session to be used in the front-end
                    String displayName = commonFunctions.constructSystemUserNameForDisplay((SystemUser) principal);
                   
                    System.out.println("displayName :" + displayName);
                    
                    session.setAttribute("displayName", displayName);
                    
                    
                    //otherwise send to the default location from front end       
                    target.onAuthenticationSuccess(request, response, authentication);        

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }



    }
}
