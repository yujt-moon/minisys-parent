package shiro;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.*;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.junit.Assert;
import org.junit.Test;

import java.security.Key;

/**
 * 编码/解码
 * @author yujiangtao
 * @date 2019/6/28 16:12
 */
public class EncodeAndDecodeTest {

    @Test
    public void testBase64() {
        String str = "hello";
        String base64Encoded = Base64.encodeToString(str.getBytes());
        System.out.println(base64Encoded);
        String str2 = Base64.decodeToString(base64Encoded);
        Assert.assertEquals(str, str2);
    }

    @Test
    public void test16Hex() {
        String str = "hello";
        String hex16Encoded = Hex.encodeToString(str.getBytes());
        System.out.println(hex16Encoded);
        String str2 = new String(Hex.decode(hex16Encoded.getBytes()));
        Assert.assertEquals(str, str2);
    }

    @Test
    public void testMD5Hash() {
        String str = "hello";
        String salt = "123";
        // 还可以转换为 toBase64()/toHex()
        String md5 = new Md5Hash(str, salt).toString();
        System.out.println(md5);
    }

    @Test
    public void testSHA256Hash() {
        String str = "hello";
        String salt = "123";
        String sha1 = new Sha256Hash(str, salt).toString();
        System.out.println(sha1);
    }

    @Test
    public void testSimpleHash() {
        String str = "hello";
        String salt = "123";
        // 内部使用MessageDigest
        String simpleHash = new SimpleHash("SHA-1", str, salt).toString();
        System.out.println(simpleHash);
    }

    @Test
    public void testHashService() {
        // 默认算法SHA-512
        DefaultHashService hashService = new DefaultHashService();
        hashService.setHashAlgorithmName("SHA-512");
        // 私盐，默认无
        hashService.setPrivateSalt(new SimpleByteSource("123"));
        // 是否生成公盐，默认false
        hashService.setGeneratePublicSalt(true);
        // 用于生成公盐
        hashService.setRandomNumberGenerator(new SecureRandomNumberGenerator());
        // 生成hash的迭代次数
        hashService.setHashIterations(1);

        HashRequest request = new HashRequest.Builder()
                .setAlgorithmName("MD5").setSource(ByteSource.Util.bytes("hello"))
                .setSalt(ByteSource.Util.bytes("123")).setIterations(2).build();
        String hex = hashService.computeHash(request).toHex();
        System.out.println(hex);
    }

    /**
     * shiro提供对称式加密/解密算法的支持，如AES、Blowfish等
     */
    @Test
    public void testAES() {
        AesCipherService aesCipherService = new AesCipherService();
        // 设置key的长度
        aesCipherService.setKeySize(128);
        // 生成key
        Key key = aesCipherService.generateNewKey();
        String text = "hello";
        // 加密
        String encrptText = aesCipherService.encrypt(text.getBytes(), key.getEncoded()).toHex();
        // 解密
        String text2 = new String(aesCipherService.decrypt(Hex.decode(encrptText), key.getEncoded()).getBytes());

        Assert.assertEquals(text, text2);
    }
}
