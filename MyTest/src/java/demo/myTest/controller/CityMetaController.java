package demo.myTest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import demo.myTest.constants.AjaxResultCode;
import demo.myTest.domains.CityMeta;
import demo.myTest.vo.AjaxResult;

@Controller
public class CityMetaController {

	@RequestMapping(value = "/citymeta", method = RequestMethod.GET)
	public @ResponseBody
	AjaxResult getCityMeta() {
		return getCityMetaWithName("hangzhou");
	}

	@RequestMapping(value = "/citymeta/{pinyin}", method = RequestMethod.GET)
	public @ResponseBody
	AjaxResult getCityMetaWithName(@PathVariable("pinyin") String pinyin) {
		CityMeta cm = new CityMeta();
		if ("hangzhou".equalsIgnoreCase(pinyin)) {
			cm.setZoom(12);
		} else if ("shanghai".equalsIgnoreCase(pinyin)) {
			cm.setZoom(8);
		} else if ("beijing".equalsIgnoreCase(pinyin)) {
			cm.setZoom(8);
		} else {
			cm.setZoom(12);
		}
		AjaxResult ar = new AjaxResult();
		ar.setResultCode(AjaxResultCode.SUCCESS);
		ar.setResultData(cm);
		return ar;
	}
}
