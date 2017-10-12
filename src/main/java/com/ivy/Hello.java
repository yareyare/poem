package com.ivy;

import java.util.HashMap;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Hello {

	@RequestMapping(value = "/Hello")
	public String hello(Model model) {
		System.out.println("HELLO ");
		model.addAttribute("message", "hello world !!!");
		return "HelloWorld";
	}

	@RequestMapping(value = "/view")
	public String view(Map<String, String> map) {
		System.out.println("HELLO ");
		map.put("message", "hello world !!!");
		return "HelloWorld";
	}

	@RequestMapping(value = "test")
	public ModelAndView test() {
		Map<String,String> model = new HashMap<String,String>();
		model.put("message", "hello world !!!");
		ModelAndView mav = new ModelAndView("HelloWorld", model);
		return mav;
	}

}
