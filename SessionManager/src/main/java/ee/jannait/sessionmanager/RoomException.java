package ee.jannait.sessionmanager;

public class RoomException extends RuntimeException {
    private final String message;
    private static final Integer CODE = 400;
    public RoomException(String message){
        this.message = message;
    }

    @Override
    public String toString() {
        return "{" +
                "\"message\":\"" + message + "\"," +
                "\"code\":"+CODE+
                '}';
    }
}
