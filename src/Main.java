import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class Main extends JPanel{

    Mandelbrot test;

    public Main(int width, int height) {
        setSize(width, height);
        test = new Mandelbrot(getWidth(), getHeight());
        setupMouseListener();
    }

    public void setupMouseListener() {
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                test.zoom(e.getX(), e.getY());
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        test.draw(g2);
 
 
    }
 
    public static void main(String[] args) {
        JFrame window = new JFrame("");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setBounds(0, 0, 933, 800 + 22); //(x, y, w, h) 22 due to title bar.
 
        Main panel = new Main(1200, 800);
 
        panel.setFocusable(true);
        panel.grabFocus();
 
        window.add(panel);
        window.setVisible(true);
        window.setResizable(false);
    }
 
}