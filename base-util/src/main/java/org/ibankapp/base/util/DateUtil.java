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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
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

    return getXMLGregorianCalendarFromDate(getDateFromString(sdate, pattern));

  }

  public static String getStringFromXMLGregorianCalendar(XMLGregorianCalendar cal, String pattern) {
    DateFormat format = new SimpleDateFormat(pattern);
    return format.format(getDateFromXMLGregorianCalendar(cal));
  }

  public static Date getDateFromXMLGregorianCalendar(XMLGregorianCalendar cal) {
    GregorianCalendar gCal = cal.toGregorianCalendar();
    return gCal.getTime();
  }

  public static LocalDate getLocalDateFromXMLGregorianCalendar(XMLGregorianCalendar cal) {
    return cal.toGregorianCalendar().toZonedDateTime().toLocalDate();
  }

  public static LocalDateTime getLocalDateTimeFromXMLGregorianCalendar(XMLGregorianCalendar cal) {
    return cal.toGregorianCalendar().toZonedDateTime().toLocalDateTime();
  }

  public static XMLGregorianCalendar getXMLGregorianCalendarFromLocalDateTime(LocalDateTime dateTime) {

    Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
    try {
      return getXMLGregorianCalendarFromDate(date);
    } catch (DatatypeConfigurationException e) {
      throw new IllegalArgumentException("日期非法");
    }
  }

  public static XMLGregorianCalendar getXMLGregorianCalendarFromLocalDate(LocalDate localDate) {

    Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

    String FORMATER = "yyyy-MM-dd";
    DateFormat format = new SimpleDateFormat(FORMATER);

    try {
      return DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(date));
    } catch (DatatypeConfigurationException e) {
      throw new IllegalArgumentException("日期非法");
    }
  }

  public static XMLGregorianCalendar getXMLGregorianCalendarFromDate(Date date) throws DatatypeConfigurationException {

    String FORMATER = "yyyy-MM-dd'T'HH:mm:ss";
    DateFormat format = new SimpleDateFormat(FORMATER);

    return DatatypeFactory.newInstance().newXMLGregorianCalendar(format.format(date));
  }

  public static Date dateAdd(Date source, int field, int amount) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(source);
    cal.add(field, amount);
    return cal.getTime();
  }

  public static java.sql.Date dateAdd(java.sql.Date source, int field, int amount) {
    return new java.sql.Date(dateAdd(new Date(source.getTime()), field, amount).getTime());
  }
}
