package demo.myTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResidentsInfoController {

	@RequestMapping(value = "/residents", method = RequestMethod.GET)
	public ModelAndView initResidentList() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("residents");
		return mv;
	}
}
