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
import org.junit.Test;

public class DateUtilTest {

    @Test
    public void testFmtCurrentDate(){

        DateUtil.getFmtCurrentDateString("yyyyMMdd");

    }
}
