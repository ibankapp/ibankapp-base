/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util.test;

import org.ibankapp.base.util.SerialUtils;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;

public class SerialUtilsTest {

  @Test
  public void testGetTimeStampSerialNo() {

    Calendar cal = Calendar.getInstance();

    cal.set(2017, 9, 1);

    String serialNo = SerialUtils.getTimeStampSerialNo(new Date(cal.getTimeInMillis()));

    Assert.assertEquals(16, serialNo.length());
    Assert.assertTrue(serialNo.startsWith("20171001"));

  }
}
