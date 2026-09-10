package game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;

import game.messages.calls.Call;
import game.messages.distributions.Distribution;

public class Handler implements Runnable {

    Socket socket;
    BufferedReader reader;
    BufferedWriter writer;

    public LinkedList<String> callsOnHold = new LinkedList<>();

    public Handler(Socket socket) throws IOException {
        try {

            this.socket = socket;
            this.reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

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
                message = reader.readLine();
                callsOnHold.add(message);
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
    
        try {

            writer.write(message.toString());
            writer.newLine();
            writer.flush();

        } catch (Exception e) {e.printStackTrace();}

    }
    
}
