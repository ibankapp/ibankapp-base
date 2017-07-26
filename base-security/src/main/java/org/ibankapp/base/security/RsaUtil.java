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
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
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

  private RsaUtil() {

  }

  /**
   * 还原公钥，X509EncodedKeySpec 用于构建公钥的规范.
   *
   * @param keyBytes byte[]类型的公钥
   * @return PublicKey类型的公钥
   */
  private static PublicKey getPublicKey(byte[] keyBytes) {
    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(keyBytes);
    try {
      KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
      return factory.generatePublic(x509EncodedKeySpec);
    } catch (Exception e) {
      throw new BaseSecurityException("E-BASE-SECURITY-000007").initCause(e);
    }
  }

  /**
   * 还原私钥，PKCS8EncodedKeySpec 用于构建私钥的规范.
   *
   * @param keyBytes byte[]类型的私钥
   * @return PrivateKey类型的私钥
   */
  private static PrivateKey getPrivateKey(byte[] keyBytes) {
    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
    try {
      KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
      return factory.generatePrivate(pkcs8EncodedKeySpec);
    } catch (Exception e) {
      throw new BaseSecurityException("E-BASE-SECURITY-000008").initCause(e);
    }
  }

  /**
   * 使用RAS算法进行加密.
   *
   * @param keyType 密钥类型
   * @param skey 密钥
   * @param clearBytes 要加密的明文
   * @return 加密后的密文
   */
  public static byte[] encrypt(KeyType keyType, String skey, byte[] clearBytes) {

    Key key;
    try {
      key = getKey(keyType, skey);
    } catch (IOException e) {
      throw new BaseSecurityException("E-BASE-SECURITY-000009");
    }

    return encrypt(key, clearBytes);
  }

  /**
   * 加密.
   *
   * @param key 密钥
   * @param clearBytes 要加密的明文
   * @return 加密后的密文
   */
  public static byte[] encrypt(Key key, byte[] clearBytes) {
    try {
      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      cipher.init(Cipher.ENCRYPT_MODE, key);
      return cipher.doFinal(clearBytes);
    } catch (Exception e) {
      throw new BaseSecurityException("E-BASE-SECURITY-000004").initCause(e);
    }
  }

  /**
   * 使用默认字符集进行加密.
   *
   * @param keyType 密钥类型
   * @param skey 密钥字符串
   * @param clearText 要加密的明文
   * @return 加密后的密文字符串
   */
  public static String encrypt(KeyType keyType, String skey, String clearText) {
    return encrypt(keyType, skey, clearText, Charset.defaultCharset().name());
  }

  /**
   * 加密.
   *
   * @param keyType 密钥类型
   * @param skey 密钥字符串
   * @param clearText 要加密的明文
   * @param charset 使用的字符集
   * @return 加密后的密文字符串
   */
  public static String encrypt(KeyType keyType, String skey, String clearText, String charset) {
    byte[] clearBytes;
    try {
      clearBytes = clearText.getBytes(charset);
    } catch (UnsupportedEncodingException e) {
      throw new BaseSecurityException("E-BASE-SECURITY-000010");
    }
    return Base64.encodeBytes(encrypt(keyType, skey, clearBytes));
  }

  /**
   * 解密.
   *
   * @param key 密钥
   * @param cipherBytes 要解密的密文
   * @return 解密后的明文
   */
  public static byte[] decrypt(Key key, byte[] cipherBytes) {
    try {
      Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
      cipher.init(Cipher.DECRYPT_MODE, key);
      return cipher.doFinal(cipherBytes);
    } catch (Exception e) {
      throw new BaseSecurityException("E-BASE-SECURITY-000005").initCause(e);
    }
  }

  /**
   * 解密.
   *
   * @param keyType 密钥类型
   * @param skey 密钥字符串
   * @param cipherBytes 要解密的密文
   * @return 解密后的明文
   */
  public static byte[] decrypt(KeyType keyType, String skey, byte[] cipherBytes) {
    Key key;
    try {
      key = getKey(keyType, skey);
    } catch (IOException e) {
      throw new BaseSecurityException("E-BASE-SECURITY-000009");
    }

    return decrypt(key, cipherBytes);
  }

  /**
   * 使用默认的字符集解密.
   *
   * @param keyType 密钥类型
   * @param skey 密钥
   * @param cipherText 要解密的密文
   * @return 解密后的明文
   */
  public static String decrypt(KeyType keyType, String skey, String cipherText) {
    return decrypt(keyType, skey, cipherText, Charset.defaultCharset().name());
  }


  /**
   * 解密.
   *
   * @param keyType 密钥类型
   * @param skey 密钥字符串
   * @param cipherText 要解密的密文
   * @param charset 使用的字符集
   * @return 解密后明文
   */
  public static String decrypt(KeyType keyType, String skey, String cipherText, String charset) {
    try {
      byte[] cipherBytes = Base64.decode(cipherText);
      return new String(decrypt(keyType, skey, cipherBytes), charset);
    } catch (Exception e) {
      throw new BaseSecurityException("E-BASE-SECURITY-000010").initCause(e);
    }
  }

  private static Key getKey(KeyType keyType, String key) throws IOException {
    switch (keyType) {
      case PUBLICKEY:
        return getPublicKey(Base64.decode(key));
      case PRIVATEKEY:
        return getPrivateKey(Base64.decode(key));
      default:
        throw new BaseSecurityException("E-BASE-SECURITY-000006");
    }
  }

}