package demo.myTest.controller;

import javax.servlet.http.HttpServletRequest;

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
	AjaxResult getMarkerInformation(@PathVariable("mapId") String mapId,
			@PathVariable("featureId") String featureId,
			HttpServletRequest request) {
		StringBuilder innerHtml = new StringBuilder(
				"<div style='font-size:.8em;'>");
		if ("init".equalsIgnoreCase(mapId) || "map03".equalsIgnoreCase(mapId)) {
			innerHtml.append("唱响改革开放大丰收: ").append("<img src='")
					.append(request.getContextPath());
			if ("feature01".equalsIgnoreCase(featureId)) {
				innerHtml
						.append("/img/demoScene03.jpg'")
						.append(" width='104px' height='75px'>")
						.append("<p style='width: 260px;'>摘要：建设社会主义新农村是我国现代化进程中的重大历史任务</p>");
			} else if ("feature02".equalsIgnoreCase(featureId)) {
				innerHtml
						.append("/img/demoScene02.jpg'")
						.append(" width='104px' height='75px'>")
						.append("<p style='width: 260px;'>摘要：对农村进行经济、政治、文化和社会等方面的建设，最终实现把农村建设成为经济繁荣、设施完善、环境优美、文明和谐的社会主义新农村的目标</p>");
			} else if ("feature03".equalsIgnoreCase(featureId)) {
				innerHtml
						.append("/img/demoScene01.jpg'")
						.append(" width='104px' height='75px'>")
						.append("<p style='width: 260px;'>摘要：只有农民收入上去了，衣食住行改善了，生活水平提高了，新农村建设才能取得实实在在的成果</p>");
			} else if ("poly01".equalsIgnoreCase(featureId)) {
				innerHtml
						.append("/img/demoScene04.jpg'")
						.append(" width='104px' height='75px'>")
						.append("<p style='width: 260px;'>摘要：生产发展，是新农村建设的中心环节，是实现其他目标的物质基础</p>");
			}
		} else if ("map03".equalsIgnoreCase(mapId)) {
		} else if ("map05".equalsIgnoreCase(mapId)) {
		} else if ("map06".equalsIgnoreCase(mapId)) {
		}
		innerHtml.append("</div>");
		AjaxResult ar = new AjaxResult();
		ar.setResultCode(AjaxResultCode.SUCCESS);
		ar.setResultData(innerHtml.toString());
		return ar;
	}
}
