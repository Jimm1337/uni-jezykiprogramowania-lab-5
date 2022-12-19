/**
 * 
 */
package figury;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.util.Random;

/**
 * @author tb
 *
 */
public abstract class Figure {
	private final Graphics2D sharedBuffer;
	private Area area;
	private Shape shape;
	private AffineTransform affineTransform;

	// przesuniecie
	private int dx, dy;
	// rozciaganie
	private double sf;
	// kat obrotu
	private final double an;
	private final int width;
	private final int height;

	protected static final Random rand = new Random();

	public Figure(Graphics2D buf, int del, int w, int h) {
		sharedBuffer = buf;
		width = w;
		height = h;

		dx = 10;
		dy = 10;
		sf = 1 + 0.05 * rand.nextDouble();
		an = 1;

		affineTransform = sharedBuffer.getTransform();
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public void setAffineTransform(AffineTransform affineTransform) {
		this.affineTransform = affineTransform;
	}

	public Shape getShape() {
		return shape;
	}

	public Graphics2D getSharedBuffer() {
		return sharedBuffer;
	}

	public Area getArea() {
		return area;
	}

	public AffineTransform getAffineTransform() {
		return affineTransform;
	}

	public void run() {
		affineTransform.translate(20, 20);
		area.transform(affineTransform);
	}

	public void nextFrame(double delay) {
		Rectangle bounds = area.getBounds();
		int cx = bounds.x + bounds.width / 2;
		int cy = bounds.y + bounds.height / 2;
		// odbicie
		if (cx < 0 || cx > width)
			dx = -dx;
		if (cy < 0 || cy > height)
			dy = -dy;
		// zwiekszenie lub zmniejszenie
		if (bounds.height > height / 3 || bounds.height < 10)
			sf = 1 / sf;

		area.transform(AffineTransform.getTranslateInstance(dx * delay, dy * delay));
		area.transform(AffineTransform.getRotateInstance(an * delay, cx, cy));
	}

}
