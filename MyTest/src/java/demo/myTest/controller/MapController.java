package demo.myTest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import demo.myTest.vo.MapImageBean;
import demo.myTest.vo.PointBean;
import demo.myTest.vo.PolygonBean;
import demo.myTest.vo.ZoneBean;

@Controller
public class MapController {

	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public ModelAndView initMap() {
		return initMapWithId(null);
	}

	@RequestMapping(value = "/map/{mapId}", method = RequestMethod.GET)
	public ModelAndView initMapWithId(@PathVariable("mapId") String mapId) {
		MapImageBean mim = null;
		if (null == mapId) {
			mim = prepareImage("map03");
		} else {
			mim = prepareImage("mapId");
		}

		ModelAndView mv = new ModelAndView();
		mv.addObject("mapMeta", mim);
		mv.addObject("points", prepatePoints());
		mv.addObject("polygons", prepareZones());
		mv.setViewName("map");
		return mv;
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
		mim.setOriginImageSize(new double[] { 2480, 3508 });

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
		PointBean pb = new PointBean(200d, -200d);
		PointBean pb2 = new PointBean(1100d, -200d);
		PointBean pb3 = new PointBean(1100d, -800d);
		PointBean pb4 = new PointBean(200d, -800d);
		PointBean pb5 = new PointBean(200d, -200d);

		ZoneBean zone = new ZoneBean();
		zone.getZone().add(pb5);
		zone.getZone().add(pb4);
		zone.getZone().add(pb3);
		zone.getZone().add(pb2);
		zone.getZone().add(pb);

		PolygonBean poly = new PolygonBean();
		poly.getPolygon().add(zone);
		polys.add(poly);
		return polys;
	}
}
