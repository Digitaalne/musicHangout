package ee.jannait.sessionmanager;


public class Session {
    public enum Action{
        JOIN,
        LEAVE,
        CREATE
    }

    private Action action;
    private String code;
    private String user;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
