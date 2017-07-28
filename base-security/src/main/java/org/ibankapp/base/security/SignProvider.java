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
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import net.iharder.Base64;

/**
 * 验证签名相关工具类
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:liulj@ibankapp.org">esailor</a>
 * @since 1.0.0
 */
public class SignProvider {

  private SignProvider() {

  }

  /**
   * 用私钥对指定信息进行签名，并返回电子签名.
   *
   * @param priKeyStr 私钥
   * @param plainText 待签名的字符串
   * @return 电子签名
   */
  public static String sign(String priKeyStr, String plainText) {

    try {
      PrivateKey prikey = RsaUtil.getPrivateKey(Base64.decode(priKeyStr));

      // 用私钥对信息生成数字签名
      Signature signet = Signature.getInstance("MD5withRSA");
      signet.initSign(prikey);
      signet.update(plainText.getBytes());

      return Base64.encodeBytes(signet.sign());

    } catch (Exception e) {
      throw new BaseSecurityException("E-BASE-SECURITY-000001").initCause(e);
    }

  }

  /**
   * 校验传入的plaintextbyte数组，与原加签的字符串验证运算是否验证通过.
   *
   * @param pubKeyBytes 公钥
   * @param plainText 待验证的字符串
   * @param signText 通过私钥数字签名后的密文(Base64编码)
   * @return true:验证通过 false:验证失败
   */
  private static boolean verify(byte[] pubKeyBytes, String signText, String plainText) {

    try {
      // 取公钥匙对象
      PublicKey pubKey = RsaUtil.getPublicKey(pubKeyBytes);

      // 解密由base64编码的数字签名
      byte[] signed = Base64.decode(signText);
      Signature signatureChecker = Signature.getInstance("MD5withRSA");
      signatureChecker.initVerify(pubKey);
      signatureChecker.update(plainText.getBytes());

      // 验证签名是否正常
      return signatureChecker.verify(signed);
    } catch (Exception e) {
      throw new BaseSecurityException("E-BASE-SECURITY-000002").initCause(e);
    }
  }

  /**
   * 校验传入的plaintext字符串，与原加签的字符串运算,验证电子签名是否正确.
   *
   * @param pubKeyText 公钥字符串
   * @param plainText 需校验的字符串
   * @param signedText 电子签名
   * @return true:验证通过 false:验证失败
   */
  public static boolean verify(String pubKeyText, String signedText, String plainText) {
    try {
      byte[] pubKeyBytes = Base64.decode(pubKeyText);
      return SignProvider.verify(pubKeyBytes, signedText, plainText);
    } catch (IOException e) {
      throw new BaseSecurityException("E-BASE-SECURITY-000002").initCause(e);
    }
  }

}