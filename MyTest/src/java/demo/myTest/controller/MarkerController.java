package demo.myTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.myTest.constants.AjaxResultCode;
import demo.myTest.vo.AjaxResult;

@Controller
public class MarkerController {

	@RequestMapping(value = "/map/{mapId}/feature/{featureId}/marker", method = RequestMethod.GET)
	public @ResponseBody
	AjaxResult getMarkersInformation(@PathVariable("mapId") String mapId,
			@PathVariable("featureId") String featureId) {
		StringBuilder innerHtml = new StringBuilder(
				"<div style='font-size:.8em;'>");
		if ("init".equalsIgnoreCase(mapId) || "map03".equalsIgnoreCase(mapId)) {
			innerHtml.append("唱响社会主义大丰收: ").append("<img src='");
			if ("feature01".equalsIgnoreCase(featureId)) {
				innerHtml.append("/img/demoScene03.jpg'");
			} else if ("feature02".equalsIgnoreCase(featureId)) {
				innerHtml.append("/img/demoScene02.jpg'");
			} else if ("feature03".equalsIgnoreCase(featureId)) {
				innerHtml.append("/img/demoScene01.jpg'");
			} else if ("poly01".equalsIgnoreCase(featureId)) {
				innerHtml.append("/img/demoScene04.jpg'");
			}
			innerHtml.append(" width='104' height='75'>").append(
					"<p>摘要：规划中的梯田</p>");
		} else if ("map03".equalsIgnoreCase(mapId)) {
		} else if ("map05".equalsIgnoreCase(mapId)) {
		} else if ("map06".equalsIgnoreCase(mapId)) {
		}
		innerHtml.append("</div>");
		AjaxResult ar = new AjaxResult();
		ar.setResultCode(AjaxResultCode.SUCCESS);
		ar.setResultData(ar.toString());
		return ar;
	}
}
