/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * 日期应用类
 */
public class DateUtil {

    /**
     * 按照指定的格式返回当前日期字符串
     *
     * @param dateFormat 日期格式
     * @return 当前机器日期字符串
     */
    public static String getFmtCurrentDateString(String dateFormat) {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(cal.getTime());
    }
}
