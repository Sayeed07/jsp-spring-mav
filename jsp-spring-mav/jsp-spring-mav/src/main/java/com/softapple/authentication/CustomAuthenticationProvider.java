package com.softapple.authentication;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.softapple.model.entity.User;
import com.softapple.model.entity.UserRole;
import com.softapple.repository.UserRepository;
import com.softapple.repository.UserRoleRepository;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    public UserRepository userRepository;
    
    @Autowired
    public UserRoleRepository userRoleRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username=authentication.getPrincipal().toString();
        
//        Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();  
//    	String password = passwordEncoder.encodePassword(authentication.getCredentials().toString(), null);       
        
//        String password = DigestUtils.sha256Hex(authentication.getCredentials().toString());
        String password = authentication.getCredentials().toString();
    	   	
    	User user=userRepository.findByUsernameAndPasswordAndEnabled(username, password, true);
        
        if(user!=null){
           List<UserRole> userRoleList=userRoleRepository.findByUsername(username);
           List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
           for(UserRole ur:userRoleList){
             grantedAuthorities.add(new SimpleGrantedAuthority(ur.getRoleName()));
           }
           return new UsernamePasswordAuthenticationToken(username, password, grantedAuthorities);
        }else{
            throw new UsernameNotFoundException("Sorry! Username or Password is invalid"); 
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
