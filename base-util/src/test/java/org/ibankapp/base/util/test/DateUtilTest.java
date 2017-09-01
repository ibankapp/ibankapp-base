/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util.test;

import java.util.Calendar;
import java.util.Date;
import org.ibankapp.base.util.DateUtil;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

public class DateUtilTest {

  @Rule
  public PowerMockRule rule = new PowerMockRule();

  @Test
  @PrepareForTest(DateUtil.class)
  public void testFmtCurrentDate() throws Exception {

    Calendar cal = Calendar.getInstance();
    cal.set(2017, Calendar.MAY, 24);

    PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(cal.getTime());

    Assert.assertEquals("20170524", DateUtil.getFmtCurrentDateString("yyyyMMdd"));
    Assert.assertEquals("2017/05/24", DateUtil.getFmtCurrentDateString("yyyy/MM/dd"));
  }

  @Test
  public void testGetFmtFromDate() {

    Calendar cal = Calendar.getInstance();
    cal.set(2017, Calendar.MAY, 25);

    Assert.assertEquals("20170525", DateUtil.getFmtFromDate(cal.getTime(), "yyyyMMdd"));
    Assert.assertEquals("2017-05-25", DateUtil.getFmtFromDate(cal.getTime(), "yyyy-MM-dd"));
  }


}
