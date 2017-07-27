/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.security;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import net.iharder.Base64;

/**
 * 用私钥对指定信息进行签名，并返回加签后的密文
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:liulj@ibankapp.org">esailor</a>
 * @since 1.0.0
 */
public class Signaturer {

  /**
   * 用私钥对指定信息进行签名，并返回加签后的密文.
   *
   * @param priKeyText 私钥
   * @param plainText 待签名的字符串
   * @return 加签后的密文
   */
  public static byte[] sign(byte[] priKeyText, String plainText) {

    try {
      PKCS8EncodedKeySpec pripkcs8 = new PKCS8EncodedKeySpec(Base64.decode(priKeyText));
      KeyFactory keyf = KeyFactory.getInstance("RSA");
      PrivateKey prikey = keyf.generatePrivate(pripkcs8);

      // 用私钥对信息生成数字签名
      Signature signet = Signature.getInstance("MD5withRSA");
      signet.initSign(prikey);
      signet.update(plainText.getBytes());

      return Base64.encodeBytesToBytes(signet.sign());

    } catch (Exception e) {
      e.printStackTrace();
      throw new BaseSecurityException("E-BASE-SECURITY-000001").initCause(e);
    }

  }

}