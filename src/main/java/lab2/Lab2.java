package lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.GeneralPath;

public class Lab2 extends JPanel implements ActionListener {
    private static int maxWidth;
    private static int maxHeight;
    private double angle = 0;
    private double scale = 1;
    private int scaleDirection = 1;
    private double scaleStep = 0.01;
    private Timer timer;
    private int[][] bigPolygonCoords = {{-80, 80}, {-20, 20}, {-75, 20}, {-20, -40},{-70,-40},{0,-120},{70,-40},{20,-40},{75,20},{20,20},{80,80}};
    private int[][] smallPolygonCoords = {{150, -50}, {50, 0}, {150, 50}, {120, 0}};
    private int[] circleCenter = {-90, 0};
    private int[][] stringCenters = {{-90, 0}, {100, 0}};
    private int circleRadius = 60;
    private int stringsCount = 7;
    private double stringSpace = 0.01;

    public Lab2() {
        timer = new Timer(10, this);
        timer.start();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello, lab2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.add(new Lab2());
        frame.setVisible(true);
        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }

    private GeneralPath createPolygon(int[][] points,Graphics2D g2d) {
        GeneralPath poly = new GeneralPath();
        poly.moveTo(points[0][0], points[0][1]);
        for (int[] point : points) {
            poly.lineTo(point[0], point[1]);
        }
        poly.closePath();
        g2d.rotate(angle, poly.getBounds2D().getCenterX(),
                poly.getBounds2D().getCenterY());
        return poly;
    }

    private void drawFigure(Graphics2D g2d) {
        GradientPaint gp = new GradientPaint(
                0, 0, new Color(255, 50, 0),
                50, 50, new Color(0, 0, 255),
                true);
        g2d.setPaint(gp);
        // big polygon
        g2d.translate(maxWidth / 2, maxHeight / 2);
        g2d.scale(scale, scale);
        g2d.fill(createPolygon(bigPolygonCoords,g2d));
        // small polygon
//        g2d.setColor(new Color(0, 0, 255));
//        g2d.fill(createPolygon(smallPolygonCoords));
        // circle
        g2d.setColor(new Color(0.54509807F, 0.27058825F, 0.07450981F));
        g2d.fillRect(-20,80,40,80);
        g2d.rotate(angle);
        // lines
//        g2d.setColor(new Color(0, 0, 0));
//        int stringsStartX = stringCenters[0][0];
//        int stringsStartY = (int) (this.stringCenters[0][1] - maxWidth * stringSpace * stringsCount / 2);
//        int stringsEndX = this.stringCenters[1][0];
//        int stringsEndY = (int) (this.stringCenters[1][1] - maxWidth * stringSpace * stringsCount / 2);
//        BasicStroke basicStroke = new BasicStroke(1, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
//        g2d.setStroke(basicStroke);
//        for (int i = 0; i < this.stringsCount; i++) {
//            g2d.drawLine(stringsStartX, (int) (stringsStartY + maxHeight * stringSpace * i), stringsEndX, (int) (stringsEndY + maxHeight * stringSpace * i));
//        }


    }

    private void drawBorder(Graphics2D g2d) {
        BasicStroke basicStroke = new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_MITER);
        g2d.setStroke(basicStroke);
        g2d.drawRect(5, 5, maxWidth - 10, maxHeight - 10);
    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);
        g2d.setBackground(Color.GRAY);
        g2d.clearRect(0, 0, maxWidth, maxHeight);
        this.drawBorder(g2d);

        this.drawFigure(g2d);
    }

    public void actionPerformed(ActionEvent e) {
        angle -=0.01;

        this.scale += this.scaleStep * this.scaleDirection;
        if (this.scale > 1) {
            this.scale = 1;
            this.scaleDirection = -1;
        } else if (this.scale < 0.2) {
            this.scale = 0.2;
            this.scaleDirection = 1;
        }

        repaint();
    }
}
