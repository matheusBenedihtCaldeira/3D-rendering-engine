import models.Triangle;
import models.Vertex;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Viewer {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Container pane = frame.getContentPane();
        pane.setLayout(new BorderLayout());

        // Slider para controlar rotação horizontal
        JSlider headingSlider = new JSlider(0, 360, 180);
        pane.add(headingSlider, BorderLayout.SOUTH);

        // Slider para controlar rotação vertical
        JSlider pitchSlider = new JSlider(SwingConstants.VERTICAL, -90, 90, 0);
        pane.add(pitchSlider, BorderLayout.EAST);
        //Criando tres triangulos para formar um tetraedo
        List<Triangle> tris = new ArrayList();
        tris.add(new Triangle(new Vertex(100, 100, 100),
                 new Vertex(-100, -100, 100),
                 new Vertex(-100, 100, -100),
                 Color.WHITE));
        tris.add(new Triangle(new Vertex(100, 100, 100),
                new Vertex(-100, -100, 100),
                new Vertex(100, -100, -100),
                Color.RED));
        tris.add(new Triangle(new Vertex(-100, 100, -100),
                new Vertex(100, -100, -100),
                new Vertex(100, 100, 100),
                Color.GREEN));
        tris.add(new Triangle(new Vertex(-100, 100, -100),
                new Vertex(100, -100, -100),
                new Vertex(-100, -100, 100),
                Color.BLUE));

        // Painel para exibir os resultados
        JPanel renderPanel = new JPanel(){
            public void paintComponent(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                g2.setColor(Color.BLACK);
                g2.fillRect(0,0, getWidth(), getHeight());
                g2.translate(getWidth() / 2, getHeight() /2);
                g2.setColor(Color.WHITE);
                for(Triangle t : tris){
                    Path2D path = new Path2D.Double();
                    path.moveTo(t.getV1().getX(), t.getV1().getY());
                    path.lineTo(t.getV2().getX(), t.getV2().getY());
                    path.lineTo(t.getV3().getX(), t.getV3().getY());
                    path.closePath();
                    g2.draw(path);
                }
                }
            };
        pane.add(renderPanel, BorderLayout.CENTER);

        frame.setSize(400, 400);
        frame.setVisible(true);
        }
    }