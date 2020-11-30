package ee.jannait.common.databaseservice;

import com.lambdaworks.redis.KeyValue;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;
import com.lambdaworks.redis.RedisURI;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RedisService {
    private final RedisClient client;
    private static final Logger LOGGER = LogManager.getLogger(RedisService.class);
    private final RedisConnection<String, String> connection;
    private static final RedisService INSTANCE = new RedisService();
    private static final Integer TIMEOUT = 1;
    private final String redisPassword = System.getenv("REDIS_PASSWORD");
    private final String redisHost = System.getenv("REDIS_HOST");
    private static final int EXPIRE_SECONDS = 21600; // 6 hours

    private RedisService(){
        RedisClient redisClient = new RedisClient(
                RedisURI.create("redis://"+redisHost));
        RedisConnection<String, String> connection = redisClient.connect();
        LOGGER.info("RedisService: Connected");
        this.client = redisClient;
        this.connection = connection;
    }

    public static RedisService getInstance(){
        return INSTANCE;
    }

    public void pushToList(String key, String... values){
        connection.lpush(key, values);
        connection.expire(key, EXPIRE_SECONDS);
    }

    public String getFromList(String key){
        return connection.blpop(TIMEOUT, key).value;
    }

    public String get(String key){
        var response = connection.get(key);
        return response == null ? "" : response;
    }

    public void closeRedis(){
        this.connection.close();
        this.client.shutdown();
        LOGGER.info("RedisService: Closed");
    }

    public boolean exists(String key){
        return connection.exists(key);
    }

}
