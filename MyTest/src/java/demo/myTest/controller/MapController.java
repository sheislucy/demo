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

import demo.myTest.constants.AjaxResultCode;
import demo.myTest.vo.AjaxResult;
import demo.myTest.vo.MapImageBean;
import demo.myTest.vo.PointBean;
import demo.myTest.vo.PolygonBean;
import demo.myTest.vo.ZoneBean;

@Controller
public class MapController {

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String initMap() {
		return "map";
	}

	@RequestMapping(value = "/map/{mapId}", method = RequestMethod.GET)
	public @ResponseBody
	AjaxResult initMapWithId(@PathVariable("mapId") String mapId) {
		MapImageBean mim = null;
		if (null == mapId) {
			mim = prepareImage("map03");
		} else {
			mim = prepareImage(mapId);
		}

		Map<String, Object> jsonMap = new HashMap<String, Object>();

		jsonMap.put("mapMeta", mim);
		jsonMap.put("points", prepatePoints());
		jsonMap.put("polygons", prepareZones());
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
			mim.setMapImageUrl("/img/Map03.jpg");
			mim.setMapName("结构分析");
		} else {
			return null;
		}
		mim.setWidth(2480d);
		mim.setHeight(3508d);

		return mim;
	}

	private List<PointBean> prepatePoints() {
		List<PointBean> points = new ArrayList<PointBean>();
		PointBean pb = new PointBean(324.5d, 923.065d);
		PointBean pb2 = new PointBean(433.5625d, 673.875d);
		PointBean pb3 = new PointBean(553.25d, 422.96875d);
		points.add(pb);
		points.add(pb2);
		points.add(pb3);
		return points;
	}

	private List<PolygonBean> prepareZones() {
		List<PolygonBean> polys = new ArrayList<PolygonBean>();
		PointBean pb = new PointBean(393.5d, 531.125d);
		PointBean pb2 = new PointBean(426.25d, 465.125d);
		PointBean pb3 = new PointBean(436.875d, 463.5d);
		PointBean pb4 = new PointBean(579d, 511.875d);
		PointBean pb5 = new PointBean(567.75d, 569.625d);
		PointBean pb6 = new PointBean(561.5d, 575d);
		PointBean pb7 = new PointBean(541.5d, 580d);
		PointBean pb8 = new PointBean(516d, 605.375d);
		PointBean pb9 = new PointBean(396.125d, 535.625d);

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
		return polys;
	}
}
