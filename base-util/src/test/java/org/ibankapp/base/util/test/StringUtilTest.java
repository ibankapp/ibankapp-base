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

public class StringUtilTest {

    @Test
    public void testIsEmpty(){

        Assert.assertTrue(StringUtils.isEmpty(null));

        String s = "";
        Assert.assertTrue(StringUtils.isEmpty(s));

        s=" ";
        Assert.assertTrue(StringUtils.isEmpty(s));

        s="0";
        Assert.assertFalse(StringUtils.isEmpty(s));

    }

    @Test
    public void testNewStringUtil(){
        new StringUtils();
    }
}
