package org.upe;
import javax.swing.JFrame;


public class SimpleDraw
{
    public static void main( String[] args )
    {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
       //frame.setTitle("Simple Drawings");
        frame.setTitle("Planet Orbits");

        //MyPanel panel = new MyPanel();
        SolarSystem panel = new SolarSystem();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        panel.start();
    }
}
