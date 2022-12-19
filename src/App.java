import gui.WindowMain;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.io.Serial;

public class App extends JFrame {
    @Serial
	private static final long serialVersionUID = 1L;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
                try {
                    final WindowMain wnd = new WindowMain();
                    wnd.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        );
    }
}
