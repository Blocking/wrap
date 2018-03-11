package com.example.wrap.javacryptography;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.net.URL;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.TreeSet;

/**
 * @author 12232
 * @date 2018/3/10
 */
@Slf4j
public class CipherTest {
    /**
     * 查看本地数字签名支持的加密算法
     */
    @Test
    public void t(){
        TreeSet<String> algorithms = new TreeSet<>();
        for (Provider provider : Security.getProviders()) {
            for (Provider.Service service : provider.getServices()) {
                if ("Signature".equals(service.getType())) {
                    algorithms.add(service.getAlgorithm());
                }
            }
        }
        System.out.println(algorithms);
    }


    /**
     * 非对称加解密
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    @Test
    public void encryptAndDecrypt() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException, BadPaddingException, IllegalBlockSizeException, CertificateException, UnrecoverableEntryException, KeyStoreException {
        Cipher cipher = Cipher.getInstance("RSA");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
//        KeyPair keyPair = this.loadKeyStoreToKeyPair();
        cipher.init(Cipher.ENCRYPT_MODE, keyPair.getPublic());
        byte[] plainText  = "天下无双战绩功成名就扬名立万".getBytes("UTF-8");
        byte[] cipherText = cipher.doFinal(plainText);
        System.out.println(new String(cipherText));
        cipher.init(Cipher.DECRYPT_MODE, keyPair.getPrivate());
        byte[] st = cipher.doFinal(cipherText);
        System.out.println(new String(st));

    }



    public KeyPair loadKeyStoreToKeyPair() throws KeyStoreException, IOException, CertificateException, NoSuchAlgorithmException, UnrecoverableEntryException {
        URL url = CipherTest.class.getResource("/certificate/vodt.pfx");
        File file = new File(url.getFile());
        final KeyStore keyStore = KeyStore.getInstance("PKCS12");
        char[] keyStorePassword = "12345678".toCharArray();
        try(InputStream keyStoreData = new FileInputStream(file)){
            keyStore.load(keyStoreData, keyStorePassword);
        }
        KeyStore.ProtectionParameter entryPassword =
                new KeyStore.PasswordProtection(keyStorePassword);

        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("1",entryPassword);
        PrivateKey privateKey = privateKeyEntry.getPrivateKey();
        PublicKey publicKey = privateKeyEntry.getCertificate().getPublicKey();

        KeyPair keyPair = new KeyPair(publicKey,privateKey);
        return keyPair;

    }


    /**
     * 数字签名以及验签
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws UnsupportedEncodingException
     * @throws SignatureException
     */
    @Test
    public void verifySignature() throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, SignatureException {
        Signature signature = Signature.getInstance("SHA256WithDSA");
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        signature.initSign(keyPair.getPrivate(),secureRandom);
        byte[] data = "天下无双战绩功成名就扬名立万".getBytes("UTF-8");
        signature.update(data);
        byte[] digitalSignature = signature.sign();
        String  s = new String(digitalSignature);
        System.out.println(s);


        Signature signature1 = Signature.getInstance("SHA256WithDSA");
        signature1.initVerify(keyPair.getPublic());
        signature1.update(data);
        boolean verify = signature1.verify(digitalSignature);
        System.out.println(verify);

    }

    @Test
    public void verifySignature1() throws CertificateException, UnrecoverableEntryException, NoSuchAlgorithmException, KeyStoreException, IOException {
        KeyPair keyPair = loadKeyStoreToKeyPair();
        String content = "天下无双战绩功成名就扬名立万";
        String signStr  = sign(content,keyPair.getPrivate());
        boolean isPass = verify(content,signStr,keyPair.getPublic());
        System.out.println(isPass);
    }

    public static final String SIGN_ALGORITHMS = "SHA1WithRSA";

    public  String sign(final String content, final PrivateKey priKey) {
        try {
            final byte[] md5 = DigestUtils.md5(content);

            final Signature signature = Signature.getInstance(SIGN_ALGORITHMS);
            signature.initSign(priKey);
            signature.update(md5);
            final byte[] signed = signature.sign();
            return Base64.encodeBase64String(signed);
        } catch (final Exception e) {
            log.error("计算签名失败。", e);
        }
        return null;
    }

    public  boolean verify(final String content, final String sign, final PublicKey pubKey) {
        try {
            final byte[] md5 = DigestUtils.md5(content);

            final Signature signature = Signature
                    .getInstance(SIGN_ALGORITHMS);

            signature.initVerify(pubKey);
            signature.update(md5);

            final boolean bverify = signature.verify(Base64.decodeBase64(sign));
            return bverify;
        } catch (final Exception e) {
            log.error("校验签名失败。", e);
        }

        return false;
    }
}
