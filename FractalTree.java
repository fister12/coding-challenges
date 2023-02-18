import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class FractalTreeWithMouse extends JPanel implements MouseMotionListener {

    // set initial values for tree parameters
    private int depth = 8; // depth of tree
    private double length = 140; // length of branches
    private double angle = Math.PI / 4; // angle of branches

    public FractalTreeWithMouse() {
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, depth, getWidth() / 2, getHeight() - 20, length, -Math.PI / 2);
    }

    // recursive function to draw the tree
    private void drawTree(Graphics g, int depth, double x1, double y1, double length, double angle) {
        if (depth == 0) {
            return;
        }
        double x2 = x1 + Math.cos(angle) * length;
        double y2 = y1 + Math.sin(angle) * length;

        g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

        drawTree(g, depth - 1, x2, y2, length * 0.7, angle + this.angle);
        drawTree(g, depth - 1, x2, y2, length * 0.7, angle - this.angle);
    }

    // implement MouseMotionListener methods
    @Override
    public void mouseMoved(java.awt.event.MouseEvent evt) {
        angle = (evt.getX() / (double) getWidth() - 0.5) * Math.PI / 2;
        repaint();
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent evt) {
        mouseMoved(evt);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Fractal Tree With Mouse");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        FractalTreeWithMouse tree = new FractalTreeWithMouse();
        frame.add(tree);
        frame.setVisible(true);
    }
  //This program uses the initial parameters provided for length of tree and angle to produce a tree using a recursive technique till the length becomes zero
  //as the mouse is moved the angle is changed using a appropiate arithematic expression
