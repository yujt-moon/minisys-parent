import com.moon.minisys.jedis.JedisClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yujiangtao
 * @date 2018/7/16 15:59
 */
public class JedisClientSingleTest {

    ApplicationContext applicationContext = null;

    @Before
    public void setUp() {
        applicationContext =
                new ClassPathXmlApplicationContext("spring/applicationContext.xml", "spring/applicationContext-dao.xml", "spring/applicationContext-jedis.xml", "spring/applicationContext-service.xml");
    }

    @Test
    public void xmlLoadTest() {
        Object redisClient = applicationContext.getBean("redisClient");
        Assert.assertNotNull(redisClient);
    }

    @Test
    public void testJedisClient() {
        JedisClient jedisClient = (JedisClient)applicationContext.getBean("jedisClient");
        String result = jedisClient.get("hehe");
        Assert.assertEquals("haha", result);
        String ret = jedisClient.set("xxx", "123");
        Assert.assertEquals("OK", ret);
        long delRet = jedisClient.del("xxx");
        Assert.assertEquals(1, delRet);
    }

    @After
    public void settleDown() {
    }
}
