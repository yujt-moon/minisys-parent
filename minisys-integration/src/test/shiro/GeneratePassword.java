package shiro;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author yujiangtao
 * @date 2019/6/3 13:43
 */
public class GeneratePassword {
    public static void main(String[] args) {
        String hashAlgorithmName = "md5";
        String credentials = "123456";
        int hashIterations = 2;
        Object obj = new SimpleHash(hashAlgorithmName, credentials, ByteSource.Util.bytes("admina38ec51c909be1c321a4907427ad5b00"), hashIterations).toHex();
        System.out.println(obj);
    }
}
