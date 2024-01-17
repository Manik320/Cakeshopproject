package com.manik.major.configuration;
import com.manik.major.model.Role;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.web.*;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.manik.major.model.Users;
import com.manik.major.repository.RoleRepository;
import com.manik.major.repository.UserRepository;

@Component
public class GoogleoAuth2SuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	RoleRepository roleRepository;
	
	@Autowired 
	  UserRepository userRepository;

	   private RedirectStrategy redirectStrategy;  
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Authentication authentication) throws IOException, ServletException {
	  OAuth2AuthenticationToken token=(OAuth2AuthenticationToken)authentication;
	  String email=token.getPrincipal().getAttributes().get("email").toString();
	  if(userRepository.findUserByEmail(email).isPresent()) {
		  
	  }
	  else {
		    Users users= new Users();
		    users.setFirstName(token.getPrincipal().getAttributes().get("given_name").toString());
		    users.setLastName(token.getPrincipal().getAttributes().get("family_name").toString());
		    users.setEmail(email);
		    List<Role>roles=new ArrayList<>();
		    roles.add(roleRepository.findById(2).get());
	        users.setRoles(roles);
	        userRepository.save(users);
	  }
	    redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, "/");
	   
	}
	
	}	

