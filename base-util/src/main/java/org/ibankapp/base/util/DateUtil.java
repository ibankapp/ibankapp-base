/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * 日期应用类
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class DateUtil {


  /**
   * 按照指定的格式返回当前日期字符串.
   *
   * @param dateFormat 日期格式
   * @return 当前机器日期字符串
   */
  public static String getFmtCurrentDateString(String dateFormat) {
    return getFmtFromDate(new Date(), dateFormat);
  }

  /**
   * 按照制定格式返回指定日期的日期字符串.
   *
   * @param date       日期
   * @param dateFormat 日期格式
   * @return 指定日期的日期字符串
   */
  public static String getFmtFromDate(Date date, String dateFormat) {
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    sdf.setTimeZone(TimeZone.getDefault());
    return sdf.format(date);
  }

  public static Date getDateFromString(String sdate, String pattern) throws ParseException {
    DateFormat format = new SimpleDateFormat(pattern);
    return format.parse(sdate);
  }

  public static XMLGregorianCalendar getXMLGregorianCalendarFromString(String sdate, String pattern)
          throws ParseException, DatatypeConfigurationException {

    return getXMLGregorianCalendarFromDate(getDateFromString(sdate,pattern));

  }

  public static String getStringFromXMLGregorianCalendar(XMLGregorianCalendar cal, String pattern) {
    DateFormat format = new SimpleDateFormat(pattern);
    return format.format(getDateFromXMLGregorianCalendar(cal));
  }

  public static Date getDateFromXMLGregorianCalendar(XMLGregorianCalendar cal) {
    GregorianCalendar gCal = cal.toGregorianCalendar();
    return gCal.getTime();
  }

  public static XMLGregorianCalendar getXMLGregorianCalendarFromDate(Date date) throws DatatypeConfigurationException {

    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(date);

    return DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
  }
}
