package lab2;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;

@SuppressWarnings("serial")
public class Skeleton extends JPanel {

    private static int maxWidth;
    private static int maxHeight;

    // Всі дії, пов'язані з малюванням, виконуються в цьому методі
    public void paint(Graphics g) {
// Оскільки Java2D є надбудовою над старішою бібліотекою, необхідно робити це приведення
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHints(rh);

        g2d.setBackground(Color.black);
        g2d.clearRect(0, 0, maxWidth, maxHeight);
        GradientPaint gp = new GradientPaint(5, 25,
                Color.YELLOW, 20, 2, Color.BLUE, true);
        g2d.setPaint(gp);
        double points[][] = {
                { 0, 85 }, { 75, 75 }, { 100, 10 }, { 125, 75 },
                { 200, 85 }, { 150, 125 }, { 160, 190 }, { 100, 150 },
                { 40, 190 }, { 50, 125 }, { 0, 85 }
        };
        GeneralPath star = new GeneralPath();
        g2d.translate(70, 12);
        star.moveTo(points[0][0], points[0][1]);
        for (int k = 1; k < points.length; k++)
            star.lineTo(points[k][0], points[k][1]);
        star.closePath();
        g2d.fill(star);
        //g2d.draw(star);
    }
    public static void main(String[] args) {
        // Створюємо нове графічне вікно (формочка). Параметр конструктора - заголово вікна.
                JFrame frame = new JFrame("Привіт, Java 2D!");
        // Визначаємо поведінку програми при закритті вікна (ЛКМ на "хрестик")
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Визначаємо розмір вікна
        frame.setSize(500, 500);
        // Якщо позиція прив'язана до null, вікно з'явиться в центрі екрану
        frame.setLocationRelativeTo(null);
        // Забороняємо змінювати розміри вікна
        frame.setResizable(false);
        // Додаємо до вікна панель, що і описується даним класом
        // Зауважте, що точка входу в програму - метод main, може бути й в іншому класі
        frame.add(new Skeleton());
        // Показуємо форму. Важливо додати всі компоненти на форму до того, як зробити її видимою.
        frame.setVisible(true);

        Dimension size = frame.getSize();
        Insets insets = frame.getInsets();
        maxWidth = size.width - insets.left - insets.right - 1;
        maxHeight = size.height - insets.top - insets.bottom - 1;
    }
}
