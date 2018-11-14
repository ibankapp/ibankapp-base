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

import java.time.LocalDate;

public class SerialUtilsTest {

  @Test
  public void testGetTimeStampSerialNo() {


    LocalDate date = LocalDate.of(2017, 10, 1);

    String serialNo = SerialUtils.getTimeStampSerialNo(date);

    Assert.assertEquals(16, serialNo.length());
    Assert.assertTrue(serialNo.startsWith("20171001"));

  }
}
