package game.messages.distributions;

import game.utilities.Element;

public class Patch extends Distribution {
    String user;
    Element subject;
    public Patch(String user, Element subject) {
        this.user = user;
        this.subject = subject;
    }

    public String toString() {
        return "Undefined Patch Distribution";
    }
}
