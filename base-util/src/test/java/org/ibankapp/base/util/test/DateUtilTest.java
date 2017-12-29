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
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

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
}
