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
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import net.iharder.Base64;

/**
 * 验证签名相关工具类
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:liulj@ibankapp.org">esailor</a>
 * @since 1.0.0
 */
public class SignProvider {

  /**
   * 校验传入的plaintextbyte数组，与原加签的字符串验证运算是否验证通过.
   *
   * @param pubKeyText 公钥
   * @param plainText 待验证的字符串
   * @param signText 通过私钥数字签名后的密文(Base64编码)
   * @return true:验证通过 false:验证失败
   */
  private static boolean verify(byte[] pubKeyText, byte[] signText, String plainText) {

    try {
      // 解密由base64编码的公钥,并构造X509EncodedKeySpec对象
      X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(Base64.decode(pubKeyText));

      // RSA对称加密算法
      KeyFactory keyFactory = KeyFactory.getInstance("RSA");

      // 取公钥匙对象
      PublicKey pubKey = keyFactory.generatePublic(bobPubKeySpec);

      // 解密由base64编码的数字签名
      byte[] signed = Base64.decode(signText);
      Signature signatureChecker = Signature.getInstance("MD5withRSA");
      signatureChecker.initVerify(pubKey);
      signatureChecker.update(plainText.getBytes());

      // 验证签名是否正常
      return signatureChecker.verify(signed);
    } catch (IOException e) {
      e.printStackTrace();
      //验签失败
      throw new BaseSecurityException("E-BASE-SECURITY-000002").initCause(e);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000002").initCause(e);
    } catch (InvalidKeySpecException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000002").initCause(e);
    } catch (InvalidKeyException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000002").initCause(e);
    } catch (SignatureException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000002").initCause(e);
    }
  }

  /**
   * 校验传入的plaintext字符串，与原加签的字符串运算,验证是否通过.
   *
   * @param pubKeyText 公钥字符串
   * @param plainText 需校验的字符串（比如mac地址）
   * @param signedText 通过私钥数字签名后的密文(Base64编码)
   * @return true:验证通过 false:验证失败
   */
  public static boolean verify(String pubKeyText, String signedText, String plainText) {
    try {
      byte[] pubKeyBytes = pubKeyText.getBytes();

      //license转码
      byte[] signed;
      signed = Base64.decode(signedText);

      //公钥去验证
      return SignProvider.verify(pubKeyBytes, signed, plainText);
    } catch (IOException e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000002").initCause(e);
    }
  }

}