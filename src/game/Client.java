package game;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import game.messages.calls.Call;
import game.messages.distributions.Distribution;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Client extends JPanel implements WindowListener {

    JTextField addressField = new JTextField("0.0.0.0");
    JTextField nameField = new JTextField("username");
    JButton sendButton = new JButton("Connect");
    Dimension previousResolution = new Dimension(0, 0);

    Socket socket;
    public BufferedWriter writer;
    BufferedReader reader;
    LinkedList<String> distributionsOnHold = new LinkedList<>();
    boolean listening = false;

    private String name;
    final int port = 3000;

    public Client() throws IOException, InterruptedException {
        setFocusable(true);
        setPreferredSize(new Dimension(512, 32));

        BoxLayout box = new BoxLayout(this, BoxLayout.LINE_AXIS);
        this.setLayout(box);
        add(this.addressField);
        add(this.nameField);
        add(this.sendButton);
    }
    
    public void run(JFrame frame) throws IOException {
        if (socket == null || !socket.isConnected()) {
            if (getWidth() != previousResolution.width || getHeight() != previousResolution.height) {
                Font dynaFont = new Font(Font.SANS_SERIF, 0, getWidth()/20);
                
                addressField.setSize((int)(getWidth()*0.6), (int)(getHeight()*0.5));
                addressField.setBounds(0, 0, (int)(getWidth()*0.6), (int)(getHeight()*0.5));
                addressField.setFont(dynaFont);
                nameField.setSize((int)(getWidth()*0.6), (int)(getHeight()*0.5));
                nameField.setBounds(0, (int)(getHeight()*0.5), (int)(getWidth()*0.6), (int)(getHeight()*0.5));
                nameField.setFont(dynaFont);
                sendButton.setSize((int)(getWidth()*0.4), getHeight());
                sendButton.setBounds((int)(getWidth()*0.6), 0, (int)(getWidth()*0.4), getHeight());
                sendButton.setFont(dynaFont);
                sendButton.setPressedIcon(sendButton.getIcon());

                previousResolution = new Dimension(getWidth(), getHeight());
            }

            if (sendButton.getModel().isPressed()) {
                try {
                    socket = new Socket(Inet4Address.getByName(addressField.getText()).getCanonicalHostName(), Server.port);
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    name = nameField.getText();
                    sendMessage(name);

                    this.removeAll();
                    Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
                    frame.setSize((int)(screen.getWidth()/2), (int)(screen.getHeight()/2));
                    frame.setLocation((int)(screen.getWidth()/4), (int)(screen.getHeight()/4));
                } catch (Exception e) {
                    if (this.socket != null) this.socket.close();
                    if (this.reader != null) this.reader.close();
                    if (this.writer != null) this.writer.close();
                }

                addressField.setText("");
                nameField.setText("");
            }
        }
        
        if (socket != null && socket.isConnected()) {
            if (!listening) {listenOnMessage();}


            if (distributionsOnHold.size() != 0) {
                String message = distributionsOnHold.pop();
                System.out.println(": "+message);
            }

            //sendMessage("asda");
        }

        repaint();
        
    }

    public void setup() {        


        
    }

    public void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());


    }

    public void sendMessage(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (Exception e) {e.printStackTrace();}
    }

    public void listenOnMessage() {
        new Thread(
            new Runnable() {
                @Override
                public void run() {
                    String message;

                    listening = true;

                    while (socket.isConnected()) {
                        try {
                            message = reader.readLine();
                            distributionsOnHold.add(message);
                        } catch (Exception e) {
                            try {
                                if (socket != null) socket.close();
                                if (reader != null) reader.close();
                                if (writer != null) writer.close();
                            } catch (Exception f) {f.printStackTrace();}
                        }
                    }

                    listening = false;

                }
            }
        ).start();
    }






    @Override
    public void windowClosing(WindowEvent e) {
        try {
            if (socket != null) socket.close();
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
