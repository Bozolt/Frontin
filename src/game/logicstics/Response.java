package game.logicstics;

public class Response {
    public final int requestKey;
    public final boolean success;

    public Response(int requestKey, boolean success) {
        this.requestKey = requestKey;
        this.success = success;
    }
}
