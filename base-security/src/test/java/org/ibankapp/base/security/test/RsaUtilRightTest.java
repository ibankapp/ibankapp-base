package org.ibankapp.base.security.test;

import java.io.UnsupportedEncodingException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Arrays;
import net.iharder.Base64;
import org.ibankapp.base.security.KeyType;
import org.ibankapp.base.security.RsaUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RsaUtilRightTest {

  private String privateStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIetOUUKP3bBQoaqhYb"
      + "X2IUVpST8iQ9FpV9HAOHWvY6c7tt+ttKtuWACGTF6D3FwoU731TifVPjHZyNWQ115UTtoqQJi89JAZWJ1/pfMaSWN"
      + "mLQ4wef0M6XTY8A8Kbls6T4ryD1R0aZ03OV4RTgyOIPTWU1t5ulG/+NC7xE+GExxAgMBAAECgYBoMl2IKx2gOz23J"
      + "GBVtZDAbGYe6J7uDqO5b1M7HesICnfaNA997xMtq47jk4UmrsQDXIvw51Sflqwb1FT6BYCe/sLyg/0H3yglkGID8A"
      + "6mvCCHf9MSbyZt7Q36Mwcy7fc6UjMYq6YyIPFKn3JfRsXyKea1mngPGPLBXs76o2wEUQJBAPzzX8ik8h9lkYM6qn2"
      + "KoZk4YcUEO7dNFCzlFdYb+k6OoMIA8Sa30XkJs49ZGnupq+9EXofql9NhByMZC3pLen0CQQCJT+7AIeHvACXyxcba"
      + "5d3Ci02vNmKkhm6OrQ6F4xKNyh7663t6Q+RWr10uOonaUZ6YH4keuvyKWiMI+ztY5wgFAkAG8ohG8oDT6+47NHlKS"
      + "Wx20N2ek6cwOaW8Ne6LmukdDz3LFkuJTLMsJ+AOp9vaWaanQ7F0+jSBUcDobd+q1DfhAkB3LJjayI1/EXHeMylT8w"
      + "11O9JAr8MNaF+sFSb1rQ79YN9ih96zTxlu4uTMqqHaidxLy5MGyONGcNTXhrULg/jBAkEAgyFkw9l3+NbdW05ApnJ"
      + "9S/UZ0Qwoi1wAhDDkjgACZwEkYEUy1I8oJMYyv/+Me8jqXo4fg9RJGNR9KNRRgBWbFg==";

  private String publicStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCHrTlFCj92wUKGqoWG19iFFaUk/IkP"
      + "RaVfRwDh1r2OnO7bfrbSrblgAhkxeg9xcKFO99U4n1T4x2cjVkNdeVE7aKkCYvPSQGVidf6XzGkljZi0OMHn9DOl0"
      + "2PAPCm5bOk+K8g9UdGmdNzleEU4MjiD01lNbebpRv/jQu8RPhhMcQIDAQAB";

  private String clearText = "1234567890杏花村abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
      + "~！@#￥%……&*";

  private PrivateKey privateKey;

  private PublicKey publicKey;

  /**
   * 初始化公钥和私钥.
   */
  @Before
  public void init() throws Exception {
    publicKey = RsaUtil.getPublicKey(Base64.decode(publicStr));
    privateKey = RsaUtil.getPrivateKey(Base64.decode(privateStr));
  }


  @Test
  public void testRsaString() {

    String cipherText = RsaUtil.encrypt(KeyType.PUBLICKEY, publicStr, clearText);

    Assert.assertEquals(clearText,
        RsaUtil.decrypt(KeyType.PRIVATEKEY, privateStr, cipherText));

  }

  @Test
  public void testRsaBytes() {
    byte[] clearBytes = clearText.getBytes();

    byte[] cipherBytes = RsaUtil.encrypt(KeyType.PRIVATEKEY, privateStr, clearBytes);

    Assert.assertTrue(
        Arrays.equals(clearBytes, RsaUtil.decrypt(KeyType.PUBLICKEY, publicStr, cipherBytes)));
  }

  @Test
  public void testCharset() throws UnsupportedEncodingException {

    String isoClearText = new String(clearText.getBytes(), "iso8859-1");

    String isocipherText =
        RsaUtil.encrypt(KeyType.PUBLICKEY, publicStr, isoClearText, "iso8859-1");

    String isoresultClearText =
        RsaUtil.decrypt(KeyType.PRIVATEKEY, privateStr, isocipherText, "iso8859-1");

    isoresultClearText = new String(isoresultClearText.getBytes("iso8859-1"));

    Assert.assertEquals(clearText, isoresultClearText);

  }

  @Test
  public void testByRsaKey() {
    byte[] cipherBytes = RsaUtil.encrypt(publicKey, clearText.getBytes());
    byte[] clearBytes = RsaUtil.decrypt(privateKey, cipherBytes);

    Assert.assertTrue(Arrays.equals(clearText.getBytes(), clearBytes));

    cipherBytes = RsaUtil.encrypt(privateKey, clearText.getBytes());
    clearBytes = RsaUtil.decrypt(publicKey, cipherBytes);

    Assert.assertTrue(Arrays.equals(clearText.getBytes(), clearBytes));
  }
}
