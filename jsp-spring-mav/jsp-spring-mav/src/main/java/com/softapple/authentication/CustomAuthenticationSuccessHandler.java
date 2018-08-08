package com.softapple.authentication;


import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.softapple.repository.UserRepository;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    
    @Autowired
    public UserRepository userRepository;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
     /*       
        HttpSession httpSession=httpServletRequest.getSession();
        httpSession.setAttribute("username", authentication.getName());     
*/
    	if(authentication.getAuthorities().toString().contains("ROLE_ADMIN")) {	
    		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/admin");
    	}else {
    		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/registration/student/find-all");
    	}
    	
    	
    }
}
