package game;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;

public class Server extends JPanel implements WindowListener {
    
    ServerSocket serverSocket = new ServerSocket(port, 255, InetAddress.getLocalHost());
    HashMap<String, Handler> handlers = new HashMap<>();

    boolean listening = false;
    Thread listenerThread = null;
    Handler handlerOnHold = null;

    public static final int port = 3000;

    public Server() throws IOException {
        setFocusable(true);
        setPreferredSize(new Dimension(1280, 720));
        System.out.println(serverSocket.getLocalSocketAddress());
    }
    
    public void run() {
        try {

            if (!serverSocket.isClosed()) {
                if (!listening) {
                    listening = true;
                    listenerThread = new Thread(new Runnable() { 
                        @Override
                        public void run() {
                            Socket socket;
                            try {
                                socket = serverSocket.accept();
                                Handler newHandler = new Handler(socket);
                                handlerOnHold = newHandler;
                                new Thread(newHandler).start();
                                listening = false;
                            } catch (IOException e) {e.printStackTrace();}
                            listenerThread = null;
                        }
                    });
                    listenerThread.start();
                }
            }

        } catch (Exception e) {e.printStackTrace();}

        if (handlerOnHold != null) {
            try {
                String name = handlerOnHold.reader.readLine();
                while (handlers.containsKey(name)) {name += ""+((int)(Math.random()*10));}
                handlers.put(name, handlerOnHold);
                handlerOnHold = null;
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        for (String key : handlers.keySet()) {
            Handler handler = handlers.get(key);
            if (handler.messagesOnHold.size() != 0) {
                String message = handler.messagesOnHold.pop();
                if (message == null) {handlers.remove(key);}
                else {System.out.println(key+": "+message);}
            }

            
        }
        
    }

    public void setup() {

        

    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }










    @Override
    public void windowClosing(WindowEvent e) {
        try {
            serverSocket.close();
            System.exit(0);
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

    @Override
    public void windowOpened(WindowEvent e) {}

}
