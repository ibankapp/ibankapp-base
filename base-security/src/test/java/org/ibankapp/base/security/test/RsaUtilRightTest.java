package org.ibankapp.base.security.test;

import net.iharder.Base64;
import org.ibankapp.base.security.EncryptType;
import org.ibankapp.base.security.RsaUtil;
import org.junit.Assert;
import org.junit.Test;

public class RsaUtilRightTest {

  @Test
  public void testEncrypt() {
    new RsaUtil();

    //公钥加密
    String publicStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCHrTlFCj92wUKGqoWG19iFFaUk/IkPRaVfRw"
        + "Dh1r2OnO7bfrbSrblgAhkxeg9xcKFO99U4n1T4x2cjVkNdeVE7aKkCYvPSQGVidf6XzGkljZi0OMHn9DOl02PAP"
        + "Cm5bOk+K8g9UdGmdNzleEU4MjiD01lNbebpRv/jQu8RPhhMcQIDAQAB";
    String plainText = "12345678";
    byte[] encryptBytes = RsaUtil
        .encrypt(EncryptType.PUBLICKEYFLAG.ordinal(), publicStr, plainText.getBytes());
    String encryptStr = Base64.encodeBytes(encryptBytes);
    System.out.println("1 encryptStr=[" + encryptStr + "]");

    //私钥解密
    String privateStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAIetOUUKP3bBQoaqhYbX2IUVp"
        + "ST8iQ9FpV9HAOHWvY6c7tt+ttKtuWACGTF6D3FwoU731TifVPjHZyNWQ115UTtoqQJi89JAZWJ1/pfMaSWNmLQ4"
        + "wef0M6XTY8A8Kbls6T4ryD1R0aZ03OV4RTgyOIPTWU1t5ulG/+NC7xE+GExxAgMBAAECgYBoMl2IKx2gOz23JGB"
        + "VtZDAbGYe6J7uDqO5b1M7HesICnfaNA997xMtq47jk4UmrsQDXIvw51Sflqwb1FT6BYCe/sLyg/0H3yglkGID8A"
        + "6mvCCHf9MSbyZt7Q36Mwcy7fc6UjMYq6YyIPFKn3JfRsXyKea1mngPGPLBXs76o2wEUQJBAPzzX8ik8h9lkYM6q"
        + "n2KoZk4YcUEO7dNFCzlFdYb+k6OoMIA8Sa30XkJs49ZGnupq+9EXofql9NhByMZC3pLen0CQQCJT+7AIeHvACXy"
        + "xcba5d3Ci02vNmKkhm6OrQ6F4xKNyh7663t6Q+RWr10uOonaUZ6YH4keuvyKWiMI+ztY5wgFAkAG8ohG8oDT6+4"
        + "7NHlKSWx20N2ek6cwOaW8Ne6LmukdDz3LFkuJTLMsJ+AOp9vaWaanQ7F0+jSBUcDobd+q1DfhAkB3LJjayI1/EX"
        + "HeMylT8w11O9JAr8MNaF+sFSb1rQ79YN9ih96zTxlu4uTMqqHaidxLy5MGyONGcNTXhrULg/jBAkEAgyFkw9l3+"
        + "NbdW05ApnJ9S/UZ0Qwoi1wAhDDkjgACZwEkYEUy1I8oJMYyv/+Me8jqXo4fg9RJGNR9KNRRgBWbFg==";

    String unencryptStr = RsaUtil
        .unencrypt(EncryptType.PUBLICKEYFLAG.ordinal(), privateStr, encryptBytes);
    System.out.println("1 unencryptStr=[" + unencryptStr + "]");
    Assert.assertEquals(unencryptStr, plainText);

    //私钥加密
    encryptBytes = RsaUtil
        .encrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), privateStr, plainText.getBytes());
    encryptStr = Base64.encodeBytes(encryptBytes);
    System.out.println("2 encryptStr=[" + encryptStr + "]");

    //公钥解密
    unencryptStr = RsaUtil.unencrypt(EncryptType.PRIVATEKEYFLAG.ordinal(), publicStr, encryptBytes);
    System.out.println("2 unencryptStr=[" + unencryptStr + "]");
    Assert.assertEquals(unencryptStr, plainText);
  }


}
