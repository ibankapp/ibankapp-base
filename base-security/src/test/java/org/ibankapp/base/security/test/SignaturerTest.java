/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.security.test;

import org.ibankapp.base.security.Signaturer;
import org.junit.Assert;
import org.junit.Test;

public class SignaturerTest {

  @Test
  public void testSign() {
    String priKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJF4Wp4cxBNmY4FXgNT7pe6eEx"
        + "Zc03yDHKMTw+n0oR5ByTHPla6GXu6wIiwlChxripICBzrIVF+OSioB7wJWlzadb/TuaInyH4z2BRZIkqF66pUuR"
        + "cNzRjBv8slcc5blY0TDxIIWZrxQC3GpsEBAIp8SvPPdOJF5v2OXpr8tlKp5AgMBAAECgYATqEfWhdyZIoTfvYtT"
        + "PI43qHS72N9bO/xqrQkUXFJJXGprqLl5U/8QEg0MGhU7seyPvmZrsxZzsy6ibpB1qG3WZj7/pkuVWnJteyBe4yI"
        + "1fBZKzP5o+aqB8CBFQcrBofyk9L0OzVsnuaz5jCgXkWvUHkpvD4xW2hilLZIT6YLI4QJBAOzdm37uPFqwImFZqs"
        + "GnzWFBNdNsj5zu/0I++2qgWN8Lyx7MHdlJoidswKScykK/CTPbex9XpOSOdBlF5qDn4scCQQCdOK7Y1jzETh4bF"
        + "1uzSWTqYFPtqSj3TzmoqqFArtYNyqvJc1E0u12LsVR8IBKBka0qiw8AJpQOnWpXjIKmYci/AkAS3cPY5FjiUE0s"
        + "u9uh+R+es3b6FBeMRG0IdcPIxmTNvz66gT+PYxILWdouQl7J2jV9b+nqoSx9F+ufZ7Xq8w1ZAkBETlEgogtqmin"
        + "YSVCs1xFCBrHQCnNi5we88Mz4Jj2XZls2PaSlTVX6EAQNnX2Mz3a1ig7tWNVweBGwkbpdgRRPAkEAg4oCnncCL1"
        + "AkF3B2QEktQjSKps3HlR7JxVIUhzNnzAqju3opmVL+zQvTGycAJ0I2+my1LNE6I3OazWWjRJcdYw==";
    byte[] priKeyText = priKeyStr.getBytes();
    String plainText = "12345678";
    byte[] signed = Signaturer.sign(priKeyText, plainText);
    String signedStr = new String(signed);
    String expectedStr = "XOX7CZ+ytpoE81bxPoBjcjNHZsaDqkfwmynFhnFOFQP1zC89Dchusbo2yLaeKmyHCBjHH03j"
        + "Wo5FFashlURzUQabKJDSvEXwv8R3nT4Z2rMMOFkeZ7HyISbth9+QogtOv4h3QW06+ShXBvxVw02UWoiU0dm2sEj"
        + "akeqxUTQcRTA=";
    Assert.assertEquals(expectedStr, signedStr);
  }
}
