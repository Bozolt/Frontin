package game.messages.distributions;

import game.utilities.Element;

public class Put extends Distribution {
    String user;
    Element subject;
    public Put(String user, Element subject) {
        this.user = user;
        this.subject = subject;
    }

    public String toString() {
        return "Put;"+user+";"+this.subject.toString();
    }
}
