/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.security;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import net.iharder.Base64;

/**
 * 加解密工具
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:liulj@ibankapp.org">esailor</a>
 * @since 1.0.0.0
 */
public class RsaUtil {

  private static final String KEY_ALGORITHM = "RSA";

  private static final String CIPHER_ALGORITHM = "RSA/ECB/PKCS1Padding";

  /**
   * 还原公钥，X509EncodedKeySpec 用于构建公钥的规范.
   *
   * @param keyBytes byte[]类型的公钥
   * @return PublicKey类型的公钥
   */
  private static PublicKey getPublicKey(byte[] keyBytes) throws BaseSecurityException {
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
    try {
      KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
      return factory.generatePublic(x509EncodedKeySpec);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000007").initCause(e);
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000007").initCause(e);
    }
  }

  /**
   * 还原私钥，PKCS8EncodedKeySpec 用于构建私钥的规范.
   *
   * @param keyBytes byte[]类型的私钥
   * @return PrivateKey类型的私钥
   */
  private static PrivateKey getPrivateKey(byte[] keyBytes) throws BaseSecurityException {
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
    try {
      KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
      return factory.generatePrivate(pkcs8EncodedKeySpec);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000008").initCause(e);
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000008").initCause(e);
    }
  }

  /**
   * 加密.
   *
   * @param key 公钥
   * @param plainText 待加密的byte[]类型数据.
   * @return 加密后的byte[]类型数据
   */
  private static byte[] encrypt(PublicKey key, byte[] plainText) throws BaseSecurityException {

    try {
      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return cipher.doFinal(plainText);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
    } catch (InvalidKeyException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
    } catch (BadPaddingException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
    }
  }

  /**
   * 加密.
   *
   * @param key 私钥
   * @param plainText 待加密的byte[]类型数据
   * @return 加密后的byte[]类型数据
   */
  private static byte[] encrypt(PrivateKey key, byte[] plainText) throws BaseSecurityException {

    try {
      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return cipher.doFinal(plainText);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
    } catch (InvalidKeyException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
    } catch (BadPaddingException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
    }
  }

  /**
   * 加密.
   *
   * @param flag 0.公钥加密 1.私钥加密
   * @param key 密文
   * @param plainText 待加密的明文
   * @return 密文
   */
  public static byte[] encrypt(int flag, String key, byte[] plainText)
      throws BaseSecurityException {
    if (flag == EncryptType.PUBLICKEYFLAG.ordinal()) {
      PublicKey publicKey;
      try {
        publicKey = getPublicKey(Base64.decode(key));
      } catch (IOException e) {
        e.printStackTrace();
        throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
      } catch (BaseSecurityException e) {
        e.printStackTrace();
        throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
      }
      return encrypt(publicKey, plainText);
    } else if (flag == EncryptType.PRIVATEKEYFLAG.ordinal()) {
      PrivateKey privateKey;
      try {
        privateKey = getPrivateKey(Base64.decode(key));
      } catch (IOException e) {
        e.printStackTrace();
        throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
      } catch (BaseSecurityException e) {
        e.printStackTrace();
        throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
      }
      return encrypt(privateKey, plainText);
    } else {
      throw new BaseSecurityException("E-BASE-SECURITY-000004");
    }

  }

  /**
   * 解密.
   *
   * @param key 私钥
   * @param encodedText 密文
   * @return 明文
   */
  private static String unencrypt(PrivateKey key, byte[] encodedText) throws BaseSecurityException {
    try {
      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, key);
      return new String(cipher.doFinal(encodedText));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
    } catch (InvalidKeyException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
    } catch (BadPaddingException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
    }
  }

  /**
   * 通过公钥解密.
   *
   * @param key 公钥
   * @param encodedText 密文
   */
  private static String unencrypt(PublicKey key, byte[] encodedText) throws BaseSecurityException {
    try {
      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, key);
      return new String(cipher.doFinal(encodedText));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
    } catch (InvalidKeyException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
    } catch (BadPaddingException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
    }
  }

  /**
   * 解密
   *
   * @param flag 0.私钥解密 1.公钥解密
   * @param key 密钥
   * @param encodedText 密文
   */
  public static String unencrypt(int flag, String key, byte[] encodedText)
      throws BaseSecurityException {

    if (flag == EncryptType.PUBLICKEYFLAG.ordinal()) {
      PrivateKey privateKey;
      try {
        privateKey = getPrivateKey(Base64.decode(key));
      } catch (IOException e) {
        e.printStackTrace();
        throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
      } catch (BaseSecurityException e) {
        e.printStackTrace();
        throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
      }
      return unencrypt(privateKey, encodedText);
    } else if (flag == EncryptType.PRIVATEKEYFLAG.ordinal()) {
      PublicKey publicKey;
      try {
        publicKey = getPublicKey(Base64.decode(key));
      } catch (IOException e) {
        e.printStackTrace();
        throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
      } catch (BaseSecurityException e) {
        e.printStackTrace();
        throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
      }
      return unencrypt(publicKey, encodedText);
    } else {
      throw new BaseSecurityException("E-BASE-SECURITY-000005");
    }

  }
}