import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
public class TestContainersTest {

    private RedisCache redisCache;

    // container {
    @Container
    public GenericContainer redis = new GenericContainer(DockerImageName.parse("redis:5.0.3-alpine"))
            .withExposedPorts(6379);

    // }

    @BeforeEach
    public void setUp() {
        String address = redis.getHost();
        Integer port = redis.getFirstMappedPort();

        // Now we have an address and port for Redis, no matter where it is running
        redisCache = new RedisCache(address, port);
    }

    @Test
    public void testSimplePutAndGet() {
        redisCache.put("test", "example");

        String retrieved = redisCache.get("test");
        assertThat(retrieved).isEqualTo("example");
    }
}

class RedisCache {

    private final StatefulRedisConnection<String, String> connection;

    public RedisCache(String hostname, Integer port) {
        RedisClient client = RedisClient.create(String.format("redis://%s:%d/0", hostname, port));
        connection = client.connect();
    }

    public String get(String key) {
        return connection.sync().get(key);
    }

    public void put(String key, String value) {
        connection.sync().set(key, value);
    }
}
