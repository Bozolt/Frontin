package game.messages.distributions;

import game.utilities.Element;

public class Post extends Distribution {
    String user;
    Element subject;
    public Post(String user, Element subject) {
        this.user = user;
        this.subject = subject;
    }

    public String toString() {
        return "Post;"+user+";"+this.subject.toString();
    }
}
