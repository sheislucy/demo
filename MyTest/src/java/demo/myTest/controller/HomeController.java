package demo.myTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		// ModelAndView mv = new ModelAndView();
		// mv.addObject("Content-Type", "text/html; charset=UTF-8");
		// mv.setViewName("test");
		return "home";
	}
}
