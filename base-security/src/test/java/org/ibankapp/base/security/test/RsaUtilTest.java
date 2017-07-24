package org.ibankapp.base.security.test;

import mockit.Mocked;
import org.ibankapp.base.security.BaseSecurityException;
import org.ibankapp.base.security.EncryptType;
import org.ibankapp.base.security.RsaUtil;
import org.junit.Test;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Rule;
import org.junit.rules.ExpectedException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

/**
 * Created by llj on 17/6/15.
 */
public class RsaUtilTest {
    String publicStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCHrTlFCj92wUKGqoWG19iFFaUk/IkPRaVfRwDh1r2OnO7bfrbSrblgAhkxeg9xcKFO99U4n1T4x2cjVkNdeVE7aKkCYvPSQGVidf6XzGkljZi0OMHn9DOl02PAPCm5bOk+K8g9UdGmdNzleEU4MjiD01lNbebpRv/jQu8RPhhMcQIDAQAB";
    String privateStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIetOUUKP3bBQoaqhYbX2IUVpST8iQ9FpV9HAOHWvY6c7tt+ttKtuWACGTF6D3FwoU731TifVPjHZyNWQ115UTtoqQJi89JAZWJ1/pfMaSWNmLQ4wef0M6XTY8A8Kbls6T4ryD1R0aZ03OV4RTgyOIPTWU1t5ulG/+NC7xE+GExxAgMBAAECgYBoMl2IKx2gOz23JGBVtZDAbGYe6J7uDqO5b1M7HesICnfaNA997xMtq47jk4UmrsQDXIvw51Sflqwb1FT6BYCe/sLyg/0H3yglkGID8A6mvCCHf9MSbyZt7Q36Mwcy7fc6UjMYq6YyIPFKn3JfRsXyKea1mngPGPLBXs76o2wEUQJBAPzzX8ik8h9lkYM6qn2KoZk4YcUEO7dNFCzlFdYb+k6OoMIA8Sa30XkJs49ZGnupq+9EXofql9NhByMZC3pLen0CQQCJT+7AIeHvACXyxcba5d3Ci02vNmKkhm6OrQ6F4xKNyh7663t6Q+RWr10uOonaUZ6YH4keuvyKWiMI+ztY5wgFAkAG8ohG8oDT6+47NHlKSWx20N2ek6cwOaW8Ne6LmukdDz3LFkuJTLMsJ+AOp9vaWaanQ7F0+jSBUcDobd+q1DfhAkB3LJjayI1/EXHeMylT8w11O9JAr8MNaF+sFSb1rQ79YN9ih96zTxlu4uTMqqHaidxLy5MGyONGcNTXhrULg/jBAkEAgyFkw9l3+NbdW05ApnJ9S/UZ0Qwoi1wAhDDkjgACZwEkYEUy1I8oJMYyv/+Me8jqXo4fg9RJGNR9KNRRgBWbFg==";
    String plainText = "12345678";

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

     
    public void testGetPublicKey_NoSuchAlgorithmException(){
        new MockUp<KeyFactory>() {
            @Mock
            public KeyFactory getInstance(String algorithm) throws NoSuchAlgorithmException {
                throw new NoSuchAlgorithmException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //公钥加密
        RsaUtil.encrypt(EncryptType.PUBLICKEYFLAG.ordinal(), publicStr, plainText.getBytes());

    }

     
    public void testGetPublicKey_InvalidKeySpecException(){
        new MockUp<KeyFactory>() {
            @Mock
            public final PublicKey generatePublic(KeySpec keySpec) throws InvalidKeySpecException{
                throw new InvalidKeySpecException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //公钥加密
        RsaUtil.encrypt(EncryptType.PUBLICKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    }

     
    public void testGetPrivateKey_NoSuchAlgorithmException(){
        new MockUp<KeyFactory>() {
            @Mock
            public KeyFactory getInstance(String algorithm) throws NoSuchAlgorithmException {
                throw new NoSuchAlgorithmException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //私钥加密
        RsaUtil.encrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), privateStr, plainText.getBytes());
    }

     
    public void testGetPrivateKey_InvalidKeySpecException(){
        new MockUp<KeyFactory>() {
            @Mock
            public PrivateKey generatePrivate(KeySpec keySpec) throws InvalidKeySpecException {
                throw new InvalidKeySpecException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //私钥加密
        RsaUtil.encrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), privateStr, plainText.getBytes());

    }

     
    public void testEncryptPublic_NoSuchAlgorithmException(){
        new MockUp<Cipher>() {
            @Mock
            public final Cipher getInstance(String var0) throws NoSuchAlgorithmException, NoSuchPaddingException {
                throw new NoSuchAlgorithmException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //公钥加密
        RsaUtil.encrypt(EncryptType.PUBLICKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    }

     
    public void testEncryptPublic_NoSuchPaddingException(){
        new MockUp<Cipher>() {
            @Mock
            public final Cipher getInstance(String var0) throws NoSuchAlgorithmException, NoSuchPaddingException {
                throw new NoSuchPaddingException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //公钥加密
        RsaUtil.encrypt(EncryptType.PUBLICKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    }

     
    public void testEncryptPublic_InvalidKeyException(){
        new MockUp<Cipher>() {
            @Mock
            public final void init(int var1, Key var2) throws InvalidKeyException {
                throw new InvalidKeyException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //公钥加密
        RsaUtil.encrypt(EncryptType.PUBLICKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    }

     
    public void testEncryptPublic_IllegalBlockSizeException(){
        new MockUp<Cipher>() {
            @Mock
            public final byte[] doFinal(byte[] var1) throws IllegalBlockSizeException, BadPaddingException {
                throw new IllegalBlockSizeException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //公钥加密
        RsaUtil.encrypt(EncryptType.PUBLICKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    }

     
    public void testEncryptPublic_BadPaddingException(){
        new MockUp<Cipher>() {
            @Mock
            public final byte[] doFinal(byte[] var1) throws IllegalBlockSizeException, BadPaddingException {
                throw new BadPaddingException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //公钥加密
        RsaUtil.encrypt(EncryptType.PUBLICKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    }

     
    public void testEncryptPrivate_NoSuchAlgorithmException(){
        new MockUp<Cipher>() {
            @Mock
            public final Cipher getInstance(String var0) throws NoSuchAlgorithmException, NoSuchPaddingException {
                throw new NoSuchAlgorithmException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //私钥加密
        RsaUtil.encrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), privateStr, plainText.getBytes());
    }

     
    public void testEncryptPrivate_NoSuchPaddingException(){
        new MockUp<Cipher>() {
            @Mock
            public final Cipher getInstance(String var0) throws NoSuchAlgorithmException, NoSuchPaddingException {
                throw new NoSuchPaddingException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //私钥加密
        RsaUtil.encrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), privateStr, plainText.getBytes());
    }

     
    public void testEncryptPrivate_InvalidKeyException(){
        new MockUp<Cipher>() {
            @Mock
            public final void init(int var1, Key var2) throws InvalidKeyException {
                throw new InvalidKeyException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //私钥加密
        RsaUtil.encrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), privateStr, plainText.getBytes());
    }

     
    public void testEncryptPrivate_IllegalBlockSizeException(){
        new MockUp<Cipher>() {
            @Mock
            public final byte[] doFinal(byte[] var1) throws IllegalBlockSizeException, BadPaddingException {
                throw new IllegalBlockSizeException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //私钥加密
        RsaUtil.encrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), privateStr, plainText.getBytes());
    }

     
    public void testEncryptPrivate_BadPaddingException(){
        new MockUp<Cipher>() {
            @Mock
            public final byte[] doFinal(byte[] var1) throws IllegalBlockSizeException, BadPaddingException {
                throw new BadPaddingException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("加密失败");
        //私钥加密
        RsaUtil.encrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), privateStr, plainText.getBytes());
    }

     
    public void testUnEncryptPublic_NoSuchAlgorithmException(){
        new MockUp<Cipher>() {
            @Mock
            public final Cipher getInstance(String var0) throws NoSuchAlgorithmException, NoSuchPaddingException {
                throw new NoSuchAlgorithmException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("解密失败");
        //公钥解密
        RsaUtil.unencrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    }

     
    public void testUnEncryptPublic_NoSuchPaddingException(){
        new MockUp<Cipher>() {
            @Mock
            public final Cipher getInstance(String var0) throws NoSuchAlgorithmException, NoSuchPaddingException {
                throw new NoSuchPaddingException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("解密失败");
        //公钥解密
        RsaUtil.unencrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    }

     
    public void testUnEncryptPublic_InvalidKeyException(){
        new MockUp<Cipher>() {
            @Mock
            public final void init(int var1, Key var2) throws InvalidKeyException {
                throw new InvalidKeyException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("解密失败");
        //公钥解密
        RsaUtil.unencrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    }

     
    public void testUnEncryptPublic_IllegalBlockSizeException(){
        new MockUp<Cipher>() {
            @Mock
            public final byte[] doFinal(byte[] var1) throws IllegalBlockSizeException, BadPaddingException {
                throw new IllegalBlockSizeException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("解密失败");
        //公钥解密
        RsaUtil.unencrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    }

     
    public void testUnEncryptPublic_BadPaddingException(){
        new MockUp<Cipher>() {
            @Mock
            public final byte[] doFinal(byte[] var1) throws IllegalBlockSizeException, BadPaddingException {
                throw new BadPaddingException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("解密失败");
        //公钥解密
        RsaUtil.unencrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    }

     
    public void testUnEncryptPrivate_NoSuchAlgorithmException(){
        new MockUp<Cipher>() {
            @Mock
            public final Cipher getInstance(String var0) throws NoSuchAlgorithmException, NoSuchPaddingException {
                throw new NoSuchAlgorithmException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("解密失败");
        //私钥解密
        RsaUtil.unencrypt(EncryptType.PUBLICKEYFLAG.ordinal(), privateStr, plainText.getBytes());
    }

     
    public void testUnEncryptPrivate_NoSuchPaddingException(){
        new MockUp<Cipher>() {
            @Mock
            public final Cipher getInstance(String var0) throws NoSuchAlgorithmException, NoSuchPaddingException {
                throw new NoSuchPaddingException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("解密失败");
        //私钥解密
        RsaUtil.unencrypt(EncryptType.PUBLICKEYFLAG.ordinal(), privateStr, plainText.getBytes());
    }

     
    public void testUnEncryptPrivate_InvalidKeyException(){
        new MockUp<Cipher>() {
            @Mock
            public final void init(int var1, Key var2) throws InvalidKeyException {
                throw new InvalidKeyException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("解密失败");
        //私钥解密
        RsaUtil.unencrypt(EncryptType.PUBLICKEYFLAG.ordinal(), privateStr, plainText.getBytes());
    }

     
    public void testUnEncryptPrivate_IllegalBlockSizeException(){
        new MockUp<Cipher>() {
            @Mock
            public final byte[] doFinal(byte[] var1) throws IllegalBlockSizeException, BadPaddingException {
                throw new IllegalBlockSizeException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("解密失败");
        //私钥解密
        RsaUtil.unencrypt(EncryptType.PUBLICKEYFLAG.ordinal(), privateStr, plainText.getBytes());
    }

     
    public void testUnEncryptPrivate_BadPaddingException(){
        new MockUp<Cipher>() {
            @Mock
            public byte[] doFinal(byte[] var1) throws IllegalBlockSizeException, BadPaddingException {
                throw new BadPaddingException();
            }
        };
        expectedEx.expect(BaseSecurityException.class);
        expectedEx.expectMessage("解密失败");
        //私钥解密
        RsaUtil.unencrypt(EncryptType.PUBLICKEYFLAG.ordinal(), privateStr, plainText.getBytes());

    }
}
