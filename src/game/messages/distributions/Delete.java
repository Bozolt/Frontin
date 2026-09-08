package game.messages.distributions;

import game.utilities.Element;

public class Delete extends Distribution {
    String user;
    Element subject;
    public Delete(String user, Element subject) {
        this.user = user;
        this.subject = subject;
    }

    public String toString() {
        return "Delete;"+user+";"+this.subject.KEY;
    }
}
