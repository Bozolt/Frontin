import javax.swing.JFrame;

import game.Server;

public class ServerApp {
    public static void main(String[] args) throws Exception {
        
        JFrame frame = new JFrame("Frontin Server");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setResizable(true);

        Server server = new Server();
        frame.add(server);
        frame.addWindowListener(server);

        frame.setResizable(false);

        frame.pack();
        frame.setVisible(true);

        server.setup();

        while (true) {server.run();}
    }
}
