package gui;

import figury.Figure;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.Serial;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class PanelAnimation extends JPanel implements ActionListener {
	@Serial
	private static final long serialVersionUID = 1L;

	private static final int FRAME_DELAY = 60;

	private Image backBuffer;
	private Graphics2D screen;
	private Graphics2D back;
	private final Timer timer;
	private final List<Figure> figures;

	public PanelAnimation(int initialWidth, int initialHeight) {
		super();
		setBackground(Color.WHITE);
		setOpaque(true);
		setPreferredSize(new Dimension(initialWidth, initialHeight));

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				createGraphicsContext(e.getComponent().getWidth(), e.getComponent().getHeight());
			}
		});

		figures = new ArrayList<>();
		timer = new Timer(FRAME_DELAY, this);
	}

	public void addFigure(Class<? extends Figure> figureClass) {
		try {
			Figure toDraw = figureClass.getDeclaredConstructor(Graphics2D.class, int.class, int.class, int.class)
					.newInstance(back, FRAME_DELAY, getWidth(), getHeight());

			figures.add(toDraw);
			toDraw.run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void toggleAnimation() {
		if (timer.isRunning()) {
			timer.stop();
		} else {
			timer.start();
		}
	}

	@Override
	public void setSize(int newWidth, int newHeight){
		super.setSize(newWidth, newHeight);
		createGraphicsContext(newWidth, newHeight);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (var figure : figures){
			figure.nextFrame(FRAME_DELAY * 0.001);
			back.setColor(Color.BLUE);
			back.fill(figure.getArea());
			back.draw(figure.getArea());
		}

		screen.drawImage(backBuffer, 0, 0, null);

		back.clearRect(0, 0,
				backBuffer.getWidth(null),
				backBuffer.getHeight(null));
	}

	private void applyRenderingHints() {
		back.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		screen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	}

	public void createGraphicsContext(int width, int height){
		backBuffer = createImage(width, height);

		back = (Graphics2D) backBuffer.getGraphics();
		screen = (Graphics2D) getGraphics();

		applyRenderingHints();
	}

}
