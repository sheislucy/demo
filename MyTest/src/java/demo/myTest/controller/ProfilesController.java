package demo.myTest.controller;

import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import demo.myTest.constants.Gender;
import demo.myTest.domains.User;
import demo.myTest.vo.FilterElementVo;

@Controller
public class ProfilesController {

	@RequestMapping(value = "/profiles", method = RequestMethod.GET)
	public ModelAndView getProfile() {
		return getProfileById(null);
	}

	@RequestMapping(value = "/profiles/{createdById}/namecard", method = RequestMethod.GET)
	public ModelAndView getProfileByNameCard(
			@PathVariable("createdById") String createdById,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		if (null == createdById) {
			user.setCity("hangzhou");
			user.setGender(Gender.FEMALE);
		} else if ("001".equalsIgnoreCase(createdById)) {
			user.setCity("hangzhou");
			user.setGender(Gender.FEMALE);
		} else if ("002".equalsIgnoreCase(createdById)) {
			user.setCity("shanghai");
			user.setGender(Gender.MALE);
		} else if ("003".equalsIgnoreCase(createdById)) {
			user.setCity("beijing");
			user.setGender(Gender.UNKNOWN);
		}
		user.setSummary("Util tax makes us apart.");
		mv.addObject("user", user);
		mv.setViewName("profiles/namecard");
		return mv;
	}

	@RequestMapping(value = "/profiles/{createdById}", method = RequestMethod.GET)
	public ModelAndView getProfileById(
			@PathVariable("createdById") String createdById) {
		ModelAndView mv = new ModelAndView();
		Collection<FilterElementVo> filters = new ArrayList<FilterElementVo>();
		StringBuilder sb = new StringBuilder();
		FilterElementVo filter = null;
		// add city filter
		filter = new FilterElementVo();
		filter.setType("city");
		filter.setTypeLabel("城市");
		filter.setValue("");
		filter.setLabel("全国");
		filter.setLabel("杭州");
		filter.setValue("hangzhou");

		filters.add(filter);
		sb.append(filter.getType()).append("=").append(filter.getValue())
				.append("&");

		// add gender filter
		filter = new FilterElementVo();
		filter.setType("gender");
		filter.setTypeLabel("性别");
		filter.setValue("");
		filter.setLabel("无所谓");
		filter.setValue("male");
		filters.add(filter);
		sb.append(filter.getType()).append("=").append(filter.getValue())
				.append("&");

		// add keyword filter
		filter = new FilterElementVo();
		filter.setType("keyword");
		filter.setTypeLabel("关键词");
		filter.setValue("");
		filter.setLabel("未选择");
		filter.setLabel("keyword");
		filter.setValue("keyword");
		filters.add(filter);
		sb.append(filter.getType()).append("=").append(filter.getValue())
				.append("&");
		mv.addObject("filters", filters);
		mv.addObject("qStr", sb.toString());
		mv.setViewName("profiles");
		return mv;
	}
}
