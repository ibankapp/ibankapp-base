/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.security.test;

import java.io.IOException;
import org.ibankapp.base.security.BaseSecurityException;
import org.ibankapp.base.security.SignProvider;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class SignProviderTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void testVerify() throws IOException {

    String signedStr = SignProvider.sign(privateStr, clearText);
    Assert.assertTrue(SignProvider.verify(publicStr, signedStr, clearText));
    Assert.assertFalse(SignProvider.verify(publicStr, signedStr, clearText + "1"));

    signedStr = "1" + signedStr.substring(1);
    Assert.assertFalse(SignProvider.verify(publicStr, signedStr, clearText));

  }

  @Test
  public void testSignError() {
    thrown.expect(BaseSecurityException.class);
    thrown.expectMessage("签名失败");

    SignProvider.sign(clearText,clearText);
  }

  @Test
  public void testVerifyError() {
    thrown.expect(BaseSecurityException.class);
    thrown.expectMessage("签名失败");

    SignProvider.verify(clearText,clearText,clearText);
  }
}
