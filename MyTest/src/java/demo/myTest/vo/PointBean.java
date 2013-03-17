package demo.myTest.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PointBean implements Serializable {
	private double xAxis = 0.0d;

	private double yAxis = 0.0d;

	public PointBean() {
	}

	public PointBean(double x, double y) {
		this.xAxis = x;
		this.yAxis = y;
	}

	public double getxAxis() {
		return xAxis;
	}

	public void setxAxis(double xAxis) {
		this.xAxis = xAxis;
	}

	public double getyAxis() {
		return yAxis;
	}

	public void setyAxis(double yAxis) {
		this.yAxis = yAxis;
	}
}
