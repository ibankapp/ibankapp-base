/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util.test;

import org.ibankapp.base.util.DateUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

public class DateUtilTest {

    @Test
    public void testNewDateUtil() {
        new DateUtil();
    }

    @Test
    public void testFmtCurrentDate() {

        String format = DateUtil.getFmtCurrentDateString("yyyyMMdd");

        Assert.assertEquals(8, format.length());

        format = DateUtil.getFmtCurrentDateString("yyyy-MM-dd");

        Assert.assertEquals(10, format.length());

    }

    @Test
    public void testGetFmtFromDate() {

        Calendar cal = Calendar.getInstance();
        cal.set(2017, 4, 25);

        Assert.assertEquals("20170525", DateUtil.getFmtFromDate(cal.getTime(), "yyyyMMdd"));
        Assert.assertEquals("2017-05-25", DateUtil.getFmtFromDate(cal.getTime(), "yyyy-MM-dd"));
    }


}
