package game.messages.distributions;

public class Disconnect extends Distribution {
    String user;
    public Disconnect(String user) {
        this.user = user;
    }

    public String toString() {
        return "Disconnect;"+user;
    }
}
