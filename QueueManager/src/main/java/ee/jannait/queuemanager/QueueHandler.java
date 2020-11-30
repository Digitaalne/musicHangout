package ee.jannait.queuemanager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import ee.jannait.common.SearchResponse;
import ee.jannait.common.Util;
import ee.jannait.common.databaseservice.RedisService;

public class QueueHandler implements RequestHandler<Submission, SearchResponse> {
    private final RedisService redisService = RedisService.getInstance();
    private LambdaLogger logger;
    private static final String ROOM_KEY = "room:";
    private static final String SONG_KEY = "song";

    @Override
    public SearchResponse handleRequest(Submission input, Context context) {
        if (input.getAction().equals(Submission.Action.SUBMIT)) {
            return submitSong(input);
        } else if (input.getAction().equals(Submission.Action.GET)) {
            return getSong(input);
        }
        //TODO:: FIX IT
        throw new RuntimeException();
    }

    private SearchResponse submitSong(Submission input) {
        validateUser(input.getRoom(), input.getUser());
        redisService.pushToList(ROOM_KEY + input.getRoom() + SONG_KEY, Util.getJson(input.getInput()));

        return input.getInput();
    }

    private SearchResponse getSong(Submission input) {
        validateUser(input.getRoom(), input.getUser());
        var songString = redisService.getFromList(ROOM_KEY+input.getRoom()+SONG_KEY);

        return (SearchResponse) Util.getObject(songString, SearchResponse.class);
    }

    private boolean validateUser(String room, String user){
        var users = redisService.get(room);
        if (!users.contains(user)) {
            //TODO:: FIX IT
            throw new RuntimeException();
        }
        return true;
    }
}
