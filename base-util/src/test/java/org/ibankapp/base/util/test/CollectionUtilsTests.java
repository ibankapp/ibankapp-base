/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util.test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.ibankapp.base.util.CollectionUtils;
import org.junit.Test;

public class CollectionUtilsTests {

  @Test
  public void testIsEmpty() {
    assertTrue(CollectionUtils.isEmpty((Set<Object>) null));
    assertTrue(CollectionUtils.isEmpty((Map<String, String>) null));
    assertTrue(CollectionUtils.isEmpty(new HashMap<String, String>()));
    assertTrue(CollectionUtils.isEmpty(new HashSet<Object>()));

    List<Object> list = new LinkedList<Object>();
    list.add(new Object());
    assertFalse(CollectionUtils.isEmpty(list));

    Map<String, String> map = new HashMap<String, String>();
    map.put("foo", "bar");
    assertFalse(CollectionUtils.isEmpty(map));
  }

}
