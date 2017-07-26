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

/**
 * Created by llj on 17/6/9.
 */
public class SignaturerTest {
    @Test
    public void testSign()
    {
        Signaturer st = new Signaturer();
        String priKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJF4Wp4cxBNmY4FXgNT7pe6eExZc03yDHKMTw+n0oR5ByTHPla6GXu6wIiwlChxripICBzrIVF+OSioB7wJWlzadb/TuaInyH4z2BRZIkqF66pUuRcNzRjBv8slcc5blY0TDxIIWZrxQC3GpsEBAIp8SvPPdOJF5v2OXpr8tlKp5AgMBAAECgYATqEfWhdyZIoTfvYtTPI43qHS72N9bO/xqrQkUXFJJXGprqLl5U/8QEg0MGhU7seyPvmZrsxZzsy6ibpB1qG3WZj7/pkuVWnJteyBe4yI1fBZKzP5o+aqB8CBFQcrBofyk9L0OzVsnuaz5jCgXkWvUHkpvD4xW2hilLZIT6YLI4QJBAOzdm37uPFqwImFZqsGnzWFBNdNsj5zu/0I++2qgWN8Lyx7MHdlJoidswKScykK/CTPbex9XpOSOdBlF5qDn4scCQQCdOK7Y1jzETh4bF1uzSWTqYFPtqSj3TzmoqqFArtYNyqvJc1E0u12LsVR8IBKBka0qiw8AJpQOnWpXjIKmYci/AkAS3cPY5FjiUE0su9uh+R+es3b6FBeMRG0IdcPIxmTNvz66gT+PYxILWdouQl7J2jV9b+nqoSx9F+ufZ7Xq8w1ZAkBETlEgogtqminYSVCs1xFCBrHQCnNi5we88Mz4Jj2XZls2PaSlTVX6EAQNnX2Mz3a1ig7tWNVweBGwkbpdgRRPAkEAg4oCnncCL1AkF3B2QEktQjSKps3HlR7JxVIUhzNnzAqju3opmVL+zQvTGycAJ0I2+my1LNE6I3OazWWjRJcdYw==";
        byte[] priKeyText = priKeyStr.getBytes();
        String plainText = "12345678";
        byte[] signed = Signaturer.sign(priKeyText,plainText);
        String signedStr = new String(signed);
        String expectedStr = "XOX7CZ+ytpoE81bxPoBjcjNHZsaDqkfwmynFhnFOFQP1zC89Dchusbo2yLaeKmyHCBjHH03jWo5FFashlURzUQabKJDSvEXwv8R3nT4Z2rMMOFkeZ7HyISbth9+QogtOv4h3QW06+ShXBvxVw02UWoiU0dm2sEjakeqxUTQcRTA=";
        Assert.assertEquals(expectedStr,signedStr);
    }
}
