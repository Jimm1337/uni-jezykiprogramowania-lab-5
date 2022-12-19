package figury;

import java.awt.*;
import java.awt.geom.Area;

public class Triangle extends Figure {
    public Triangle(Graphics2D buffer, int delay, int width, int height) {
        super(buffer, delay, width, height);
        setShape(new Polygon(new int[]{10, 20, 30}, new int[]{10, 30, 10}, 3));
        setArea(new Area(getShape()));
    }
}
