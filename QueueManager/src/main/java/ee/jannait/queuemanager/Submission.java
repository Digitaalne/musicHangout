package ee.jannait.queuemanager;

import ee.jannait.common.SearchResponse;

public class Submission {
    public enum Action{
        SUBMIT,
        GET
    }

    private Action action;
    private SearchResponse input;
    private String room;
    private String user;

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public SearchResponse getInput() {
        return input;
    }

    public void setInput(SearchResponse input) {
        this.input = input;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getUser() {
        return user;
    }

}
