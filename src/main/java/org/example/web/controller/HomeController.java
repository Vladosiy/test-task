package org.example.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/")
public class HomeController {

	@RequestMapping(path="/signin", method = RequestMethod.GET)
	public ModelAndView signin() {
		return new ModelAndView("signin");
	}
}
