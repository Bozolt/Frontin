import javax.swing.JFrame;

import game.Server;
import game.utilities.Encoder;
import game.utilities.types.Float8;

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

        Float8 f = Float8.getStandard();

        System.out.println(""+f.toString());

        while (true) {server.run();}
    }
}
