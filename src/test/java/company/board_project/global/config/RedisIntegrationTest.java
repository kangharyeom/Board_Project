package company.board_project.global.config;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = RedisConfig.class)
public class RedisIntegrationTest {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisOperations() {
        String key = "test";
        String value = "10";

        // Redis에 데이터 저장
        redisTemplate.opsForValue().set(key, value);

        // Redis에서 데이터 조회
        String retrievedValue = (String) redisTemplate.opsForValue().get(key);

        // Redis에서 가져온 값과 예상 값이 일치하는지 확인
        assertEquals(value, retrievedValue);
    }
}