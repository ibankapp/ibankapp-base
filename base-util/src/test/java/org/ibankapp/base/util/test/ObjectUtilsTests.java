/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util.test;

import static org.ibankapp.base.util.ObjectUtils.isEmpty;
import static org.ibankapp.base.util.ObjectUtils.nullSafeToString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Unit tests for {@link org.ibankapp.base.util.ObjectUtils}.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @author Rick Evans
 * @author Sam Brannen
 */
public class ObjectUtilsTests {

  @Rule
  public final ExpectedException exception = ExpectedException.none();

  @Test
  public void isEmptyNull() {
    assertTrue(isEmpty(null));
  }

  @Test
  public void isEmptyArray() {
    assertTrue(isEmpty(new char[0]));
    assertTrue(isEmpty(new Object[0]));
    assertTrue(isEmpty(new Integer[0]));

    assertFalse(isEmpty(new int[]{42}));
    assertFalse(isEmpty(new Integer[]{42}));
  }

  @Test
  public void isEmptyCollection() {
    assertTrue(isEmpty(Collections.emptyList()));
    assertTrue(isEmpty(Collections.emptySet()));

    Set<String> set = new HashSet<String>();
    set.add("foo");
    assertFalse(isEmpty(set));
    assertFalse(isEmpty(Collections.singletonList("foo")));
  }

  @Test
  public void isEmptyMap() {
    assertTrue(isEmpty(Collections.emptyMap()));

    HashMap<String, Object> map = new HashMap<String, Object>();
    map.put("foo", 42L);
    assertFalse(isEmpty(map));
  }

  @Test
  public void isEmptyCharSequence() {
    assertTrue(isEmpty(new StringBuilder()));
    assertTrue(isEmpty(""));

    assertFalse(isEmpty(new StringBuilder("foo")));
    assertFalse(isEmpty("   "));
    assertFalse(isEmpty("\t"));
    assertFalse(isEmpty("foo"));
  }

  @Test
  public void isEmptyUnsupportedObjectType() {
    assertFalse(isEmpty(42L));
    assertFalse(isEmpty(new Object()));
  }

  @Test
  public void nullSafeToStringWithObjectArrayBeingEmpty() {
    Object[] array = {};
    assertEquals("{}", nullSafeToString(array));
  }

  @Test
  public void nullSafeToStringWithObjectArrayEqualToNull() {
    assertEquals("null", nullSafeToString(null));
  }
}
