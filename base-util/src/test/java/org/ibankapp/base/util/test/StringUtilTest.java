/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util.test;

import org.ibankapp.base.util.StringUtils;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StringUtilTest {

    @Test
    public void testIsEmpty() {

        Assert.assertTrue(StringUtils.isEmpty(null));

        String s = "";
        Assert.assertTrue(StringUtils.isEmpty(s));

        s = " ";
        Assert.assertTrue(StringUtils.isEmpty(s));

        s = "0";
        Assert.assertFalse(StringUtils.isEmpty(s));

    }

    @Test
    public void testNewStringUtil() {
        new StringUtils();
    }

    @Test
    public void testCollectionToDelimitedString() {

        Assert.assertEquals("", StringUtils.collectionToCommaDelimitedString(null));
        Assert.assertEquals("", StringUtils.collectionToCommaDelimitedString(new ArrayList<String>()));

        List<String> strings = new ArrayList<>();
        strings.add("abcd");
        strings.add("1234");

        Assert.assertEquals("abcd,1234", StringUtils.collectionToCommaDelimitedString(strings));
    }

    @Test
    public void testHasLength() {
        Assert.assertTrue(StringUtils.hasLength("abcd"));
        Assert.assertFalse(StringUtils.hasLength(""));
        Assert.assertFalse(StringUtils.hasLength(null));
    }

    @Test
    public void testHasText() {
        Assert.assertTrue(StringUtils.hasText("abcd"));
        Assert.assertFalse(StringUtils.hasText(""));
        Assert.assertFalse(StringUtils.hasText(null));
        Assert.assertFalse(StringUtils.hasText("   "));
    }

    @Test
    public void testRandomUUID(){
        String uuid1 = StringUtils.getRandomUUID();
        String uuid2 = StringUtils.getRandomUUID();

        Assert.assertNotEquals(uuid1,uuid2);
        Assert.assertEquals(32,uuid1.length());
        Assert.assertEquals(32,uuid2.length());
    }
}
