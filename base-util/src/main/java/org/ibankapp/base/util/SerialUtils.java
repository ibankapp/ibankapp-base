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

public class SerialUtils {

  public static String getTimeStampSerialNo(Date date) {

    java.util.Date now = new java.util.Date();
    Calendar cal = Calendar.getInstance();
    cal.setTime(now);

    long seq = cal.get(Calendar.HOUR_OF_DAY) * 3600000 + cal.get(Calendar.MINUTE) * 60000
            + cal.get(Calendar.SECOND) * 1000 + cal.get(Calendar.MILLISECOND);

    return DateUtil.getFmtFromDate(date, "yyyyMMdd") + String
            .format("%08d", seq);

  }
}
