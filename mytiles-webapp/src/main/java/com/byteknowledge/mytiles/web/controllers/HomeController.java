package com.byteknowledge.mytiles.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController extends AbstractController {

	private static final String MENU_ITEM = "home";
	
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET )
	public String home(Model model) {
		model.addAttribute(MENU_ITEM_PARAM, MENU_ITEM);
		return "home";
	}
}
