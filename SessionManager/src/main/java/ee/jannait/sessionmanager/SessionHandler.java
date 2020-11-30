package ee.jannait.sessionmanager;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import ee.jannait.common.databaseservice.RedisService;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.UUID;

public class SessionHandler implements RequestHandler<Session, Session> {
    private final RedisService redisService = RedisService.getInstance();
    private LambdaLogger logger;
    private static final String ROOM_KEY = "room:";
    @Override
    public Session handleRequest(Session input, Context context) {
        this.logger = context.getLogger();
        if(input.getAction().equals(Session.Action.CREATE)){
            return createRoom(input);
        } else if (input.getAction().equals(Session.Action.JOIN)){
            return joinRoom(input);
        } else if (input.getAction().equals(Session.Action.LEAVE)){
            // DO NOTHING YET
        }
        return null;
    }

    private Session createRoom(Session session){
        boolean exists = true;
        String roomCode = null;
        while (exists) {
            roomCode = RandomStringUtils.randomAlphanumeric(6);
            exists = redisService.exists(ROOM_KEY+roomCode);
        }

        UUID uuid = UUID.randomUUID();
        session.setUser(uuid.toString());
        session.setCode(roomCode);

        redisService.pushToList(ROOM_KEY+roomCode, uuid.toString());

        return session;
    }

    private Session joinRoom(Session session) {
        if(!redisService.exists(ROOM_KEY+session.getCode())){
            throw new RoomException("SESSION_INVALID");
        }
        UUID uuid = UUID.randomUUID();
        session.setUser(uuid.toString());

        redisService.pushToList(ROOM_KEY+session.getCode(), uuid.toString());

        return session;
    }



}
