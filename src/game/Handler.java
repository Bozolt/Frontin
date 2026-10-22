package game;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;

import game.utilities.streamables.networkables.distributions.Distribution;


public class Handler implements Runnable {

    Socket socket;
    DataInputStream reader;
    DataOutputStream writer;

    public LinkedList<byte[]> callsOnHold = new LinkedList<>();

    public Handler(Socket socket) throws IOException {
        try {

            this.socket = socket;
            this.reader = new DataInputStream(socket.getInputStream());
            this.writer = new DataOutputStream(socket.getOutputStream());

        } catch (Exception e) {
            if (this.socket != null) this.socket.close();
            if (this.reader != null) this.reader.close();
            if (this.writer != null) this.writer.close();
        }
    }

    @Override
    public void run() {
        String message;

        while(socket.isConnected()) {
            try {
                int packetLength = Byte.toUnsignedInt(reader.readByte());

                byte[] packet = new byte[packetLength];

                reader.readFully(packet);

                callsOnHold.add(packet);

            } catch (Exception e) {
                try {
                    if (this.socket != null) this.socket.close();
                    if (this.reader != null) this.reader.close();
                    if (this.writer != null) this.writer.close();
                } catch (Exception f) {f.printStackTrace();}
            }
        }
    }

    public void sendMessage(Distribution message) {
    
        throw new UnsupportedOperationException("Unimplemented method");

    }
    
}
