package com.moon.minisys.util;

import com.moon.minisys.busi.pojo.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * @author yujiangtao
 * @date 2019/7/1 10:38
 */
@Component
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator =
                                    new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private final int hashIterations = 2;
    public void encryPassword(User user) {
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(
                algorithmName,
                user.getPassword(),
                ByteSource.Util.bytes(user.getCredentialsSalt()),
                hashIterations).toHex();
        user.setPassword(newPassword);
    }
}
