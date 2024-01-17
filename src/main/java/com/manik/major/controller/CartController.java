package com.manik.major.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.manik.major.global.GlobalData;
import com.manik.major.model.Product;
import com.manik.major.service.Productservice;

@Controller
public class CartController {

	@Autowired
	Productservice productservice;
	  
	@GetMapping("/addToCart/{id}")
	public String addToCart(@PathVariable int id)
	{
		GlobalData.cart.add(productservice.getProductById(id).get());
		return "redirect:/shop";
	}
	
	@GetMapping("/cart")
	 public String cartget(Model model) {
		model.addAttribute("cartCount",GlobalData.cart.size());
		model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
		model.addAttribute("cart",GlobalData.cart);
		return "cart";
	}
	 @GetMapping("/cart/removeItem/{index}")
	 public String cartItemRemove(@PathVariable int index)
	 {
		 GlobalData.cart.remove(index);
		 return "redirect:/cart";
	 }
	 @GetMapping("/checkout")
	 public String checkout(Model model)
	 {
		 model.addAttribute("total",GlobalData.cart.stream().mapToDouble(Product::getPrice).sum());
        return "checkout";
	 }
	

	     @PostMapping("/payNow")
	     public String processPayment() {
	         
	    	 
	         return "orderPlaced";
	     }
	 }

	 

