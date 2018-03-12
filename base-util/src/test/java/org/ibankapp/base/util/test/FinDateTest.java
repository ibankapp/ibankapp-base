/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.ibankapp.base.util.FinDate;
import org.junit.Assert;
import org.junit.Test;

public class FinDateTest {

  FinDate getFinDate(String fDate,String format)
  {
    SimpleDateFormat sdf = new SimpleDateFormat(format);
    java.util.Date udt = null;
    try {
      udt = sdf.parse(fDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    FinDate fdt = new FinDate(udt.getTime());
    return fdt;
  }

  @Test
  public void testAddYears() throws Exception {

    FinDate fdt = getFinDate("2017-06-17","yyyy-MM-dd");
    FinDate addFdt = fdt.addYears(1);
    FinDate subFdt = fdt.addYears(-5);
    Assert.assertEquals("2018-06-17",addFdt.toString());
    Assert.assertEquals("2012-06-17",subFdt.toString());
  }

  @Test
  public void testAddMonths() throws Exception {

    FinDate fdt = getFinDate("2017-01-30","yyyy-MM-dd");
    FinDate addFdt = fdt.addMonths(1);
    FinDate subFdt = fdt.addMonths(-5);
    Assert.assertEquals("2017-02-28",addFdt.toString());
    Assert.assertEquals("2016-08-30",subFdt.toString());
  }

  @Test
  public void testAddDays() throws Exception {

    FinDate fdt = getFinDate("2017-1-30","yyyy-MM-dd");
    FinDate addFdt = fdt.addDays(30);
    FinDate subFdt = fdt.addDays(-30);
    Assert.assertEquals("2017-03-01",addFdt.toString());
    Assert.assertEquals("2016-12-31",subFdt.toString());
  }

  @Test
  public void testAddDays1() throws Exception {
    FinDate fdt = new FinDate(2017-1900,1-1,30);
    FinDate addFdt = fdt.addDays(30);
    FinDate subFdt = fdt.addDays(-30);
    Assert.assertEquals("2017-03-01",addFdt.toString());
    Assert.assertEquals("2016-12-31",subFdt.toString());
  }

}

