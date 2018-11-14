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
import org.junit.Rule;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PowerMockIgnore;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.rule.PowerMockRule;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

@PowerMockIgnore({"javax.xml.*", "org.xml.sax.*", "org.w3c.dom.*", "org.springframework.context.*", "org.apache.log4j.*"})
public class DateUtilTest {

  @Rule
  public PowerMockRule rule = new PowerMockRule();

  @Test
  @PrepareForTest(DateUtil.class)
  public void testFmtCurrentDate() throws Exception {

    Calendar cal = Calendar.getInstance();
    cal.set(2017, Calendar.MAY, 24);

    PowerMockito.whenNew(Date.class).withNoArguments().thenReturn(cal.getTime());

    assertEquals("20170524", DateUtil.getFmtCurrentDateString("yyyyMMdd"));
    assertEquals("2017/05/24", DateUtil.getFmtCurrentDateString("yyyy/MM/dd"));
  }

  @Test
  public void testGetFmtFromDate() {

    Calendar cal = Calendar.getInstance();
    cal.set(2017, Calendar.MAY, 25);

    assertEquals("20170525", DateUtil.getFmtFromDate(cal.getTime(), "yyyyMMdd"));
    assertEquals("2017-05-25", DateUtil.getFmtFromDate(cal.getTime(), "yyyy-MM-dd"));
  }

  @Test
  public void testGetXMLGregorianCalendarFromString() throws ParseException, DatatypeConfigurationException {

    XMLGregorianCalendar cal = DateUtil.getXMLGregorianCalendarFromString("2017-09-10 17:09:45", "yyyy-MM-dd " +
        "HH:mm:ss");

    assertEquals(10, cal.getDay());
    assertEquals(2017, cal.getYear());
    assertEquals(9, cal.getMonth());
    assertEquals(17, cal.getHour());
    assertEquals(9, cal.getMinute());
    assertEquals(45, cal.getSecond());
  }

  @Test
  public void testGetStringFromXMLGregorianCalendar() throws ParseException, DatatypeConfigurationException {

    XMLGregorianCalendar cal = DateUtil.getXMLGregorianCalendarFromString("2017-10-10 17:09:45",
        "yyyy-MM-dd HH:mm:ss");

    String date = DateUtil.getStringFromXMLGregorianCalendar(cal, "yyyy-MM-dd HH:mm:ss");

    assertEquals("2017-10-10 17:09:45", date);

  }

  @Test
  public void testUtilDateAdd() throws ParseException {

    Date date = DateUtil.getDateFromString("2017-12-20", "yyyy-MM-dd");

    assertEquals("2027-12-20",
        DateUtil.getFmtFromDate(DateUtil.dateAdd(date, Calendar.YEAR, 10), "yyyy-MM-dd"));

    date = DateUtil.getDateFromString("2016-02-29", "yyyy-MM-dd");

    assertEquals("2017-02-28",
        DateUtil.getFmtFromDate(DateUtil.dateAdd(date, Calendar.YEAR, 1), "yyyy-MM-dd"));

    date = DateUtil.getDateFromString("2016-02-29", "yyyy-MM-dd");

    assertEquals("2016-03-29",
        DateUtil.getFmtFromDate(DateUtil.dateAdd(date, Calendar.MONTH, 1), "yyyy-MM-dd"));

    date = DateUtil.getDateFromString("2016-02-29", "yyyy-MM-dd");

    assertEquals("2016-03-01",
        DateUtil.getFmtFromDate(DateUtil.dateAdd(date, Calendar.DATE, 1), "yyyy-MM-dd"));
  }

  @Test
  public void testGetXMLGregorianCalendarFromLocalTime() {

    LocalDateTime dateTime = LocalDateTime.of(2018, 11, 13, 16, 3, 42);
    XMLGregorianCalendar cal = DateUtil.getXMLGregorianCalendarFromLocalDateTime(dateTime);

    assertEquals("2018-11-13T16:03:42", cal.toXMLFormat());
  }

  @Test
  public void testGetXMLGregorianCalendarFromLocalDate() {

    LocalDate date = LocalDate.of(2018, 11, 13);
    XMLGregorianCalendar cal = DateUtil.getXMLGregorianCalendarFromLocalDate(date);

    assertEquals("2018-11-13", cal.toXMLFormat());
  }

  @Test
  public void testGetLocalDateFromXMLGregorianCalendar() throws ParseException, DatatypeConfigurationException {

    DateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Date date = format.parse("2014-04-24 11:15:00");

    GregorianCalendar cal = new GregorianCalendar();
    cal.setTime(date);

    XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);

    LocalDate localDate = DateUtil.getLocalDateFromXMLGregorianCalendar(xmlGregCal);

    assertEquals(2014, localDate.getYear());
    assertEquals(4, localDate.getMonthValue());
    assertEquals(24, localDate.getDayOfMonth());
  }
}
