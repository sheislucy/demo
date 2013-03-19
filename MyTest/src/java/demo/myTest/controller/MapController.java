package demo.myTest.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import demo.myTest.constants.AjaxResultCode;
import demo.myTest.vo.AjaxResult;
import demo.myTest.vo.DropdownItem;
import demo.myTest.vo.FilterElementVo;
import demo.myTest.vo.MapImageBean;
import demo.myTest.vo.PointBean;
import demo.myTest.vo.PolygonBean;
import demo.myTest.vo.ZoneBean;

@Controller
public class MapController {

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ModelAndView initMapPage() {
		ModelAndView mv = new ModelAndView();
		List<FilterElementVo> filters = new ArrayList<FilterElementVo>();
		FilterElementVo f = new FilterElementVo();
		f.setType("category");
		f.setLabel("全部");
		f.setTypeLabel("分类");
		f.getItems().add(new DropdownItem("map03", "土地利用总图"));
		f.getItems().add(new DropdownItem("map05", "规划平面图"));
		f.getItems().add(new DropdownItem("map06", "结构分析"));
		filters.add(f);
		mv.addObject("filters", filters);
		mv.setViewName("map");
		return mv;
	}

	@RequestMapping(value = "/map/{mapId}", method = RequestMethod.GET)
	public @ResponseBody
	AjaxResult initMapWithHotSpot(@PathVariable("mapId") String mapId) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if ("init".equalsIgnoreCase(mapId)) {
			mapId = "map03";
		}

		jsonMap.put("mapMeta", prepareImage(mapId));
		jsonMap.put("points", prepatePoints(mapId));
		jsonMap.put("polygons", prepareZones(mapId));
		AjaxResult ar = new AjaxResult();
		ar.setResultCode(AjaxResultCode.SUCCESS);
		ar.setResultData(jsonMap);
		return ar;
	}

	private MapImageBean prepareImage(String mapId) {
		MapImageBean mim = new MapImageBean();

		if ("map03".equalsIgnoreCase(mapId)) {
			mim.setMapImageUrl("/img/Map03.jpg");
			mim.setMapName("土地利用总图");
		} else if ("map05".equalsIgnoreCase(mapId)) {
			mim.setMapImageUrl("/img/Map05.jpg");
			mim.setMapName("规划平面图");
		} else if ("map06".equalsIgnoreCase(mapId)) {
			mim.setMapImageUrl("/img/Map06.jpg");
			mim.setMapName("结构分析");
		} else {
			return null;
		}
		mim.setWidth(2480d);
		mim.setHeight(3508d);

		return mim;
	}

	private List<PointBean> prepatePoints(String mapId) {
		List<PointBean> points = new ArrayList<PointBean>();
		if ("map03".equalsIgnoreCase(mapId)) {
			PointBean pb = new PointBean(324.5d, 923.065d);
			PointBean pb2 = new PointBean(433.5625d, 673.875d);
			PointBean pb3 = new PointBean(553.25d, 422.96875d);
			points.add(pb);
			points.add(pb2);
			points.add(pb3);
		}
		return points;
	}

	private List<PolygonBean> prepareZones(String mapId) {
		List<PolygonBean> polys = new ArrayList<PolygonBean>();
		if ("map03".equalsIgnoreCase(mapId)) {
			PointBean pb = new PointBean(311.84375d, 420.40625d);
			PointBean pb2 = new PointBean(337.46875d, 368.28125d);
			PointBean pb3 = new PointBean(345.71875d, 367.15625d);
			PointBean pb4 = new PointBean(458.59375d, 405.90625d);
			PointBean pb5 = new PointBean(449.34375d, 451.40625d);
			PointBean pb6 = new PointBean(443.96875d, 455.53125d);
			PointBean pb7 = new PointBean(428.84375d, 459.88125d);
			PointBean pb8 = new PointBean(408.96875d, 479.53125d);
			PointBean pb9 = new PointBean(313.34375d, 423.78125d);

			ZoneBean zone = new ZoneBean();
			zone.getZones().add(pb);
			zone.getZones().add(pb2);
			zone.getZones().add(pb3);
			zone.getZones().add(pb4);
			zone.getZones().add(pb5);
			zone.getZones().add(pb6);
			zone.getZones().add(pb7);
			zone.getZones().add(pb8);
			zone.getZones().add(pb9);

			PolygonBean poly = new PolygonBean();
			poly.getPolygon().add(zone);
			polys.add(poly);
		}
		return polys;
	}
}
