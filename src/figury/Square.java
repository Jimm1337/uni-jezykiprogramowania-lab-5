package figury;

import java.awt.*;
import java.awt.geom.*;

public class Square extends Figure {
    public Square(Graphics2D buffer, int delay, int width, int height) {
        super(buffer, delay, width, height);
        setShape(new Rectangle2D.Double(10,10, 30, 30));
        setArea(new Area(getShape()));
    }
}