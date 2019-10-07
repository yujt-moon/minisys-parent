package service;

import com.moon.minisys.busi.pojo.User;
import com.moon.minisys.busi.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author yujiangtao
 * @date 2019/7/2 9:11
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext*.xml")
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void addUser() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("123456");
        user.setLocked(Boolean.FALSE);
        User user1 = userService.createUser(user);
        Assert.assertNotNull(user1.getId());
    }
}
