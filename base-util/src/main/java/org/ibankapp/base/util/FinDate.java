/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util;

import java.sql.Date;
import java.util.Calendar;

/**
 * 日期应用类
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class FinDate extends Date {


  public FinDate(long date) {
    super(date);
  }

  public FinDate getFinDateFromUtilDate(java.util.Date udt)
  {
    FinDate fdt = new FinDate(udt.getTime());
    return fdt;
  }


  public FinDate addDays(int amount) {
    Calendar rightNow = Calendar.getInstance();
    rightNow.setTime(this);
    rightNow.add(Calendar.DAY_OF_YEAR, amount);
    java.util.Date udt = rightNow.getTime();
    return getFinDateFromUtilDate(udt);
  }

  public FinDate addMonths(int amount) {
    Calendar rightNow = Calendar.getInstance();
    rightNow.setTime(this);
    rightNow.add(Calendar.MONTH, amount);
    java.util.Date udt = rightNow.getTime();
    return getFinDateFromUtilDate(udt);
  }

  public FinDate addYears(int amount) {
    Calendar rightNow = Calendar.getInstance();
    rightNow.setTime(this);
    rightNow.add(Calendar.YEAR, amount);
    java.util.Date udt = rightNow.getTime();
    return getFinDateFromUtilDate(udt);
  }

}