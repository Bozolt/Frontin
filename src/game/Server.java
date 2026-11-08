package game;

import javax.swing.JPanel;

import game.logicstics.entities.Entity;
import game.utilities.elements.Element;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class Server extends JPanel {

    public Server() throws IOException {
        setFocusable(true);
        setPreferredSize(new Dimension(1280, 720));
        System.out.println(InetAddress.getLocalHost().getHostAddress());
    }

    //read and write
    //holds instances
    //0 key is designated as null and should be always ignored
    public static ConcurrentHashMap<Integer, Element> instance = new ConcurrentHashMap<>();
    public static HashMap<Integer, Entity> entity = new HashMap<>();
     
    public void run() {
        
    }

    public void setup() {

        

    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

}
