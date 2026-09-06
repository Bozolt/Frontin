import javax.swing.JFrame;

import game.Client;
import game.Server;

public class ClientApp {
    public static void main(String[] args) throws Exception {
        
        JFrame frame = new JFrame("Frontin");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(true);

        Client client = new Client();
        frame.add(client);
        frame.addWindowListener(client);

        client.run(frame);

        frame.pack();
        frame.setVisible(true);


        long frameStart = System.currentTimeMillis();
        long frameLength = (long)(1000/60);
        while (true) {if (frameStart+frameLength < System.currentTimeMillis()) {client.run(frame); frameStart+=frameLength;}}
    }
}
