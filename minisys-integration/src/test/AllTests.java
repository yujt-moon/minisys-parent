import lucene.IndexManagerTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author yujiangtao
 * @date 2018/7/17 10:01
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        JedisClientSingleTest.class,
        IndexManagerTest.class
})
public class AllTests {
}
