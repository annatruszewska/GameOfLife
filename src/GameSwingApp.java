import javax.swing.border.LineBorder;
import javax.swing.*;
import java.awt.*;

public class GameSwingApp {

    public static final int W = 80;
    public static final int H = 60;
    private JPanel[][] panels;
    private final GameOfLifeImpl engine;

    public GameSwingApp() {
        engine = new GameOfLifeImpl();
        engine.init(W, H);
        int y = H / 2;
        int x = W / 2;
        for (int i = x - 5; i < x + 5; i++)
            engine.updateCell(i, y, true);
        showUi();
        startGame();
    }

    private void startGame() {
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                engine.next();
                for (int i = 0; i < H; i++)
                    for (int j = 0; j < W; j++) {
                        color(i, j);
                    }
            }
        }).start();
    }

    private void showUi() {
        JFrame f = new JFrame("Game of life");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(new GridLayout(H, W));
        panels = new JPanel[H][W];

        for (int i = 0; i < H; i++)
            for (int j = 0; j < W; j++) {
                panels[i][j] = new JPanel();
                color(i, j);
                panels[i][j].setBorder(LineBorder.createGrayLineBorder());
                f.add(panels[i][j]);
            }
        f.setPreferredSize(new Dimension(800, 600));
        f.setVisible(true);
        f.pack();
    }

    private void color(int i, int j) {
        panels[i][j].setBackground(engine.cellState(j, i) ? Color.YELLOW : null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GameSwingApp());
    }

}

