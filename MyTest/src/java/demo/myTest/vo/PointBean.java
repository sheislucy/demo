package demo.myTest.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class PointBean implements Serializable {
	private double x = 0.0d;

	private double y = 0.0d;

	public PointBean() {
	}

	public PointBean(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

}
