package game.messages.distributions;

import game.exceptions.IllegalFormattingException;
import game.exceptions.UndefinedMessageTypeException;
import game.utilities.Element;

public class Distribution {
    public static Distribution fromString(String message) throws Exception {
        String parts[] = message.split(";");
        if (parts.length <= 1) throw new IllegalFormattingException("Illegally formatted String");
        switch (parts[0]) {
            case "Post":
                return new Post(message, Element.fromString(parts[1]));
        
            default:
                break;
        }
        
        throw new UndefinedMessageTypeException("Undefined message type or misformatted message");
    }

    public String toString() {
        return "Undefined Message";
    }

}
