package com.manik.major.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.manik.major.global.GlobalData;
import com.manik.major.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.manik.major.model.Users;
import com.manik.major.repository.RoleRepository;
import com.manik.major.repository.UserRepository;

@Controller
public class LoginController {
	@Autowired
	private  BCryptPasswordEncoder bCryptePasswordEncoder;
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@GetMapping("/login")
	public String login()
	{
		GlobalData.cart.clear();
		return "login";
	}
     @GetMapping("/register")
     public String registerGet()
 	{
 		return "register";
 	}
     @PostMapping("/register")
      public String registerPost(@ModelAttribute("user")Users users,HttpServletRequest request) throws ServletException
      {
    	String password=users.getPassword();
    	users.setPassword( bCryptePasswordEncoder.encode(password));
    	List<Role>roles=new ArrayList<>();
	    roles.add(roleRepository.findById(2).get());
        users.setRoles(roles);
        userRepository.save(users);
    	request.login(users.getEmail(),password);
        return "redirect:/";
      }
     
}
