package demo.myTest.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import demo.myTest.utils.DateUtil;
import demo.myTest.vo.PinVo;

@Controller
public class ListController {

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView mockList(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		ModelAndView mv = new ModelAndView();
		mockPictures(mv, contextPath);
		return mv;
	}

	private void mockPictures(ModelAndView mv, String contextPath) {
		List<PinVo> pins = new ArrayList<PinVo>();

		PinVo vo1 = new PinVo();
		vo1.setImageHeight(120);
		vo1.setName("test1");
		vo1.setPlaceId("01");
		vo1.setCity("hangzhou");
		vo1.setLngLat(new Double[] { 120.17189024999996d, 30.261621415769714d });
		vo1.setSummary("xxxxxxxx");
		vo1.setCreatedById("001");
		Calendar c = Calendar.getInstance();
		vo1.setCreatedAt(c.getTime());
		vo1.setCategory("规划1");
		vo1.setCreatedByAvatarUrl(contextPath + "/img/avatar-female.jpg");// TODO
		vo1.setPlaceAddr("杭州");
		vo1.setImageUrl(contextPath + "/img/Zhuoku107.jpg");

		PinVo vo2 = new PinVo();
		vo2.setImageHeight(120);
		vo2.setName("test2");
		vo2.setPlaceId("02");
		vo2.setCity("shanghai");
		vo2.setLngLat(new Double[] { 121.469160, 31.232310 });
		vo2.setSummary("yyyyyyyy");
		vo2.setCreatedById("002");
		try {
			vo2.setCreatedAt(DateUtil.parseDateStrictly("02/14/2013",
					new String[] { DateUtil.DEFAULT_DATE_PATTERN }));
		} catch (ParseException e) {
		}
		vo2.setCategory("街景2");
		vo2.setCreatedByAvatarUrl(contextPath + "/img/avatar-male.jpg");// TODO
		vo2.setPlaceAddr("上海");
		vo2.setImageUrl(contextPath + "/img/Zhuoku054.jpg");

		PinVo vo3 = new PinVo();
		vo3.setImageHeight(120);
		vo3.setName("test3");
		vo3.setPlaceId("03");
		vo3.setCity("beijing");
		vo3.setLngLat(new Double[] { 116.401174, 39.902811 });
		vo3.setSummary("zzzzzzzz");
		vo3.setCreatedById("003");
		try {
			vo3.setCreatedAt(DateUtil.parseDateStrictly("12/25/2012",
					new String[] { DateUtil.DEFAULT_DATE_PATTERN }));
		} catch (ParseException e) {
		}
		vo3.setCategory("风景3");
		vo3.setCreatedByAvatarUrl(contextPath + "/img/avatar-unknown.jpg");// TODO
		vo3.setPlaceAddr("北京");
		vo3.setImageUrl(contextPath + "/img/Zhuoku065.jpg");

		pins.add(vo1);
		pins.add(vo2);
		pins.add(vo3);
		mv.addObject("pins", pins);
		mv.setViewName("spot/list");
	}
}
