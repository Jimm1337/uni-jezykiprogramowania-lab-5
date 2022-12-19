package gui;

import figury.Figure;

import javax.swing.*;
import java.awt.*;
import java.io.Serial;
import java.util.List;

public class WindowMain extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;

    private static final Dimension MIN_SIZE = new Dimension(800, 600);

    private final PanelAnimation panelAnimation;
    private final JPanel panelButtons;
    private final JButton btnAdd;
    private final JButton btnToggleAnimation;
    private final List<JRadioButton> radioButtonsFig;
    private final ButtonGroup radioGroupFig;
    private Class<? extends Figure> figureSelected;

    public WindowMain() {
        super("Animation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setMinimumSize(MIN_SIZE);
        setLayout(new BorderLayout());

        panelAnimation = new PanelAnimation(MIN_SIZE.width, MIN_SIZE.height);

        panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelButtons.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

        btnAdd = new JButton("Add figure");
        btnToggleAnimation = new JButton("Toggle animation");

        radioButtonsFig = List.of(
                new JRadioButton("Square"),
                new JRadioButton("Ellipse"),
                new JRadioButton("Triangle")
        );

        radioGroupFig = new ButtonGroup();

        addHandlers();
        addComponenets();

        radioButtonsFig.get(0).doClick();
    }

    private void addHandlers() {
        btnAdd.addActionListener(e -> panelAnimation.addFigure(figureSelected));
        btnToggleAnimation.addActionListener(e -> panelAnimation.toggleAnimation());

        for (JRadioButton radioButton : radioButtonsFig) {
            radioButton.addActionListener(e -> {
                if (radioButton.isSelected()) {
                    switch (radioButton.getText()) {
                        case "Square" -> figureSelected = figury.Square.class;
                        case "Ellipse" -> figureSelected = figury.Ellipse.class;
                        case "Triangle" -> figureSelected = figury.Triangle.class;
                    }
                }
            });
            radioGroupFig.add(radioButton);
        }
    }

    private void addComponenets() {
        panelButtons.add(btnAdd);
        panelButtons.add(btnToggleAnimation);
        for (JRadioButton radioButton : radioButtonsFig) {
            panelButtons.add(radioButton);
        }

        add(panelAnimation, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);
        pack();
    }
}
