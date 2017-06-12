/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */
package org.ibankapp.base.security.test;

import net.iharder.Base64;
import org.ibankapp.base.security.SignProvider;
import org.ibankapp.base.security.Signaturer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by llj on 17/6/12.
 */
public class SignProviderTest {
    @Test
    public void testBytesVerify()
    {
        SignProvider sp = new SignProvider();
        Signaturer st = new Signaturer();
        String priKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJF4Wp4cxBNmY4FXgNT7pe6eExZc03yDHKMTw+n0oR5ByTHPla6GXu6wIiwlChxripICBzrIVF+OSioB7wJWlzadb/TuaInyH4z2BRZIkqF66pUuRcNzRjBv8slcc5blY0TDxIIWZrxQC3GpsEBAIp8SvPPdOJF5v2OXpr8tlKp5AgMBAAECgYATqEfWhdyZIoTfvYtTPI43qHS72N9bO/xqrQkUXFJJXGprqLl5U/8QEg0MGhU7seyPvmZrsxZzsy6ibpB1qG3WZj7/pkuVWnJteyBe4yI1fBZKzP5o+aqB8CBFQcrBofyk9L0OzVsnuaz5jCgXkWvUHkpvD4xW2hilLZIT6YLI4QJBAOzdm37uPFqwImFZqsGnzWFBNdNsj5zu/0I++2qgWN8Lyx7MHdlJoidswKScykK/CTPbex9XpOSOdBlF5qDn4scCQQCdOK7Y1jzETh4bF1uzSWTqYFPtqSj3TzmoqqFArtYNyqvJc1E0u12LsVR8IBKBka0qiw8AJpQOnWpXjIKmYci/AkAS3cPY5FjiUE0su9uh+R+es3b6FBeMRG0IdcPIxmTNvz66gT+PYxILWdouQl7J2jV9b+nqoSx9F+ufZ7Xq8w1ZAkBETlEgogtqminYSVCs1xFCBrHQCnNi5we88Mz4Jj2XZls2PaSlTVX6EAQNnX2Mz3a1ig7tWNVweBGwkbpdgRRPAkEAg4oCnncCL1AkF3B2QEktQjSKps3HlR7JxVIUhzNnzAqju3opmVL+zQvTGycAJ0I2+my1LNE6I3OazWWjRJcdYw==";
        String pubKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCReFqeHMQTZmOBV4DU+6XunhMWXNN8gxyjE8Pp9KEeQckxz5Wuhl7usCIsJQoca4qSAgc6yFRfjkoqAe8CVpc2nW/07miJ8h+M9gUWSJKheuqVLkXDc0Ywb/LJXHOW5WNEw8SCFma8UAtxqbBAQCKfErzz3TiReb9jl6a/LZSqeQIDAQAB";
        byte[] priKeyBytes = priKeyStr.getBytes();
        byte[] pubKeyBytes = pubKeyStr.getBytes();

        String plainText = "12345678";
        byte[] signed = Signaturer.sign(priKeyBytes, plainText);

        boolean verifyFlag = SignProvider.verify(pubKeyBytes,signed,plainText);
        Assert.assertTrue(verifyFlag);

        plainText = "12345679";
        verifyFlag = SignProvider.verify(pubKeyBytes,signed,plainText);
        Assert.assertFalse(verifyFlag);

        plainText = "12345678";
        priKeyStr = "1";
        priKeyBytes = priKeyStr.getBytes();
        signed = Signaturer.sign(priKeyBytes, plainText);
        verifyFlag = SignProvider.verify(pubKeyBytes,signed,plainText);
        Assert.assertFalse(verifyFlag);

    }

    @Test
    public void testVerify()
    {
        SignProvider sp = new SignProvider();
        Signaturer st = new Signaturer();
        String priKeyStr = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAJF4Wp4cxBNmY4FXgNT7pe6eExZc03yDHKMTw+n0oR5ByTHPla6GXu6wIiwlChxripICBzrIVF+OSioB7wJWlzadb/TuaInyH4z2BRZIkqF66pUuRcNzRjBv8slcc5blY0TDxIIWZrxQC3GpsEBAIp8SvPPdOJF5v2OXpr8tlKp5AgMBAAECgYATqEfWhdyZIoTfvYtTPI43qHS72N9bO/xqrQkUXFJJXGprqLl5U/8QEg0MGhU7seyPvmZrsxZzsy6ibpB1qG3WZj7/pkuVWnJteyBe4yI1fBZKzP5o+aqB8CBFQcrBofyk9L0OzVsnuaz5jCgXkWvUHkpvD4xW2hilLZIT6YLI4QJBAOzdm37uPFqwImFZqsGnzWFBNdNsj5zu/0I++2qgWN8Lyx7MHdlJoidswKScykK/CTPbex9XpOSOdBlF5qDn4scCQQCdOK7Y1jzETh4bF1uzSWTqYFPtqSj3TzmoqqFArtYNyqvJc1E0u12LsVR8IBKBka0qiw8AJpQOnWpXjIKmYci/AkAS3cPY5FjiUE0su9uh+R+es3b6FBeMRG0IdcPIxmTNvz66gT+PYxILWdouQl7J2jV9b+nqoSx9F+ufZ7Xq8w1ZAkBETlEgogtqminYSVCs1xFCBrHQCnNi5we88Mz4Jj2XZls2PaSlTVX6EAQNnX2Mz3a1ig7tWNVweBGwkbpdgRRPAkEAg4oCnncCL1AkF3B2QEktQjSKps3HlR7JxVIUhzNnzAqju3opmVL+zQvTGycAJ0I2+my1LNE6I3OazWWjRJcdYw==";
        String pubKeyStr = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCReFqeHMQTZmOBV4DU+6XunhMWXNN8gxyjE8Pp9KEeQckxz5Wuhl7usCIsJQoca4qSAgc6yFRfjkoqAe8CVpc2nW/07miJ8h+M9gUWSJKheuqVLkXDc0Ywb/LJXHOW5WNEw8SCFma8UAtxqbBAQCKfErzz3TiReb9jl6a/LZSqeQIDAQAB";
        byte[] priKeyBytes = priKeyStr.getBytes();

        String plainText = "12345678";
        byte[] signed = Signaturer.sign(priKeyBytes, plainText);
        String signedStr = Base64.encodeBytes(signed);

        boolean verifyFlag = false;
        try {
            verifyFlag = SignProvider.verify(pubKeyStr,signedStr,plainText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(verifyFlag);

        plainText = "12345679";
        try {
            verifyFlag = SignProvider.verify(pubKeyStr,signedStr,plainText);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertFalse(verifyFlag);

    }
}
