/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class SerialUtils {

  public static String getTimeStampSerialNo(LocalDate date) {

    long seq = LocalTime.now().toNanoOfDay() / 1000000;

    return date.format(DateTimeFormatter.ofPattern("yyyyMMdd")) + String.format("%08d", seq);
  }
}
