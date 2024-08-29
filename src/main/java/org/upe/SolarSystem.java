package org.upe;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SolarSystem extends JPanel implements Runnable {

    private static final long serialVersionUID = -6923126786235441890L;

    private final int fps = 60; // frames per second
    private int panelWidth;
    private int panelHeight;
    private int middleWidth;
    private int middleHeight;
    private List<Planet> planetList;
    private Thread panelThread;

    public SolarSystem() {
        this(1920,1080);
    }

    public SolarSystem(int width, int height) {
        this.panelWidth = width;
        this.panelHeight = height;
        this.middleWidth = panelWidth / 2;
        this.middleHeight = panelHeight / 2;
        this.panelThread = Thread.ofVirtual().name("solarSystemThread").unstarted(this);
        this.setPreferredSize(new Dimension(panelWidth, panelHeight));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.planetList = new ArrayList<>();
        planetList.add(new Planet(Color.DARK_GRAY,20,1,5)); //mercury
        planetList.add(new Planet(Color.GRAY,48,2,4)); //venus
        planetList.add(new Planet(Color.blue,50,3,3)); //earth
        planetList.add(new Planet(Color.red,25,4,2)); //mars
    }

    @Override
    public void run() {
        while (panelThread.isAlive()) {
            update();
            repaint();
            try {
                Thread.sleep(1000/fps);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        for (Planet planet : planetList) {
            planet.update();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        // draw sun
        int diameter = 48;
        int radius = diameter / 2;
        g2.setColor(Color.YELLOW);
        g2.fillOval(middleWidth - radius, middleHeight - radius, diameter, diameter);
        // draw planets
        for (Planet planet : planetList) {
            g2.setColor(planet.getColor());
            g2.fillOval(middleWidth + planet.getCoordX(), middleHeight + planet.getCoordY(), planet.getDiameter(), planet.getDiameter());
        }
        g2.dispose();
    }

    public void start() {
        panelThread.start();
    }
}
