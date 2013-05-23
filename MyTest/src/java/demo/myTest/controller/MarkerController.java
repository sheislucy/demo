package demo.myTest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.myTest.constants.AjaxResultCode;
import demo.myTest.domains.HouseHold;
import demo.myTest.domains.Person;
import demo.myTest.vo.AjaxResult;

@Controller
public class MarkerController {

	@RequestMapping(value = "/map/{mapId}/feature/{featureId}/marker", method = RequestMethod.GET)
	public @ResponseBody
	AjaxResult getMarkerInformation(@PathVariable("mapId") String mapId,
			@PathVariable("featureId") String featureId,
			HttpServletRequest request) {
		HouseHold house = new HouseHold();
		if ("init".equalsIgnoreCase(mapId) || "map03".equalsIgnoreCase(mapId)) {
			if ("feature01".equalsIgnoreCase(featureId)) {
				house.setHost("张三");
				house.setBusiness("养猪");
				house.setAddress("桃园市11号");
				Person p1 = new Person();
				p1.setJob("务农");
				p1.setName("张三Junior");
				p1.setRelation("儿子");

				house.getMembers().add(p1);
				house.getMembers().add(p1);
				house.getMembers().add(p1);
			} else if ("feature02".equalsIgnoreCase(featureId)) {
				house.setHost("张三");
				house.setBusiness("养猪");
				house.setAddress("桃园市11号");
				Person p1 = new Person();
				p1.setJob("务农");
				p1.setName("张三Junior");
				p1.setRelation("儿子");

				house.getMembers().add(p1);
				house.getMembers().add(p1);
				house.getMembers().add(p1);
			} else if ("feature03".equalsIgnoreCase(featureId)) {
				house.setHost("张三");
				house.setBusiness("养猪");
				house.setAddress("桃园市11号");
				Person p1 = new Person();
				p1.setJob("务农");
				p1.setName("张三Junior");
				p1.setRelation("儿子");

				house.getMembers().add(p1);
				house.getMembers().add(p1);
				house.getMembers().add(p1);
			} else if ("poly01".equalsIgnoreCase(featureId)) {
				house.setHost("张三");
				house.setBusiness("养猪");
				house.setAddress("桃园市11号");
				Person p1 = new Person();
				p1.setJob("务农");
				p1.setName("张三Junior");
				p1.setRelation("儿子");

				house.getMembers().add(p1);
				house.getMembers().add(p1);
				house.getMembers().add(p1);
			}
		} else if ("map03".equalsIgnoreCase(mapId)) {
		} else if ("map05".equalsIgnoreCase(mapId)) {
		} else if ("map06".equalsIgnoreCase(mapId)) {
		}
		AjaxResult ar = new AjaxResult();
		ar.setResultCode(AjaxResultCode.SUCCESS);
		ar.setResultData(house);
		return ar;
	}
}
