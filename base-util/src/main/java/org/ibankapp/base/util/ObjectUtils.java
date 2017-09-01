/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.ibankapp.base.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Miscellaneous object utility methods.
 *
 * <p>Mainly for internal use within the framework.
 *
 * <p>Thanks to Alex Ruiz for contributing several enhancements to this class!
 *
 * @author Juergen Hoeller
 * @author Keith Donald
 * @author Rod Johnson
 * @author Rob Harrop
 * @author Chris Beams
 * @author Sam Brannen
 * @see CollectionUtils
 * @see StringUtils
 * @since 19.03.2004
 */
public abstract class ObjectUtils {

  private static final String EMPTY_STRING = "";
  private static final String NULL_STRING = "null";
  private static final String ARRAY_START = "{";
  private static final String ARRAY_END = "}";
  private static final String EMPTY_ARRAY = ARRAY_START + ARRAY_END;
  private static final String ARRAY_ELEMENT_SEPARATOR = ", ";


  /**
   * Determine whether the given array is empty:
   * i.e. {@code null} or of zero length.
   *
   * @param array the array to check
   * @see #isEmpty(Object)
   */
  public static boolean isEmpty(Object[] array) {
    return (array == null || array.length == 0);
  }

  /**
   * Determine whether the given object is empty.
   * <p>This method supports the following object types.
   * <ul>
   * <li>{@code Array}: considered empty if its length is zero</li>
   * <li>{@link CharSequence}: considered empty if its length is zero</li>
   * <li>{@link Collection}: delegates to {@link Collection#isEmpty()}</li>
   * <li>{@link Map}: delegates to {@link Map#isEmpty()}</li>
   * </ul>
   * <p>If the given object is non-null and not one of the aforementioned
   * supported types, this method returns {@code false}.
   *
   * @param obj the object to check
   * @return {@code true} if the object is {@code null} or <em>empty</em>
   * @see ObjectUtils#isEmpty(Object[])
   * @see StringUtils#hasLength(CharSequence)
   * @see StringUtils#isEmpty(Object)
   * @see CollectionUtils#isEmpty(java.util.Collection)
   * @see CollectionUtils#isEmpty(java.util.Map)
   * @since 4.2
   */
  @SuppressWarnings("rawtypes")
  public static boolean isEmpty(Object obj) {
    if (obj == null) {
      return true;
    }

    if (obj.getClass().isArray()) {
      return Array.getLength(obj) == 0;
    }
    if (obj instanceof CharSequence) {
      return ((CharSequence) obj).length() == 0;
    }
    if (obj instanceof Collection) {
      return ((Collection) obj).isEmpty();
    }
    if (obj instanceof Map) {
      return ((Map) obj).isEmpty();
    }

    // else
    return false;
  }

  /**
   * Return a String representation of the contents of the specified array.
   * <p>The String representation consists of a list of the array's elements,
   * enclosed in curly braces ({@code "{}"}). Adjacent elements are separated
   * by the characters {@code ", "} (a comma followed by a space). Returns
   * {@code "null"} if {@code array} is {@code null}.
   *
   * @param array the array to build a String representation for
   * @return a String representation of {@code array}
   */
  public static String nullSafeToString(Object[] array) {
    if (array == null) {
      return NULL_STRING;
    }
    int length = array.length;
    if (length == 0) {
      return EMPTY_ARRAY;
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < length; i++) {
      if (i == 0) {
        sb.append(ARRAY_START);
      } else {
        sb.append(ARRAY_ELEMENT_SEPARATOR);
      }
      sb.append(String.valueOf(array[i]));
    }
    sb.append(ARRAY_END);
    return sb.toString();
  }

  /**
   * Return a String representation of the specified Object.
   * <p>Builds a String representation of the contents in case of an array.
   * Returns {@code "null"} if {@code obj} is {@code null}.
   *
   * @param obj the object to build a String representation for
   * @return a String representation of {@code obj}
   */
  public static String nullSafeToString(Object obj) {
    if (obj == null) {
      return NULL_STRING;
    }
    if (obj instanceof String) {
      return (String) obj;
    }
    if (obj instanceof Object[]) {
      return nullSafeToString((Object[]) obj);
    }
    if (obj instanceof boolean[]) {
      return nullSafeToString((boolean[]) obj);
    }
    if (obj instanceof byte[]) {
      return nullSafeToString((byte[]) obj);
    }
    if (obj instanceof char[]) {
      return nullSafeToString((char[]) obj);
    }
    if (obj instanceof double[]) {
      return nullSafeToString((double[]) obj);
    }
    if (obj instanceof float[]) {
      return nullSafeToString((float[]) obj);
    }
    if (obj instanceof int[]) {
      return nullSafeToString((int[]) obj);
    }
    if (obj instanceof long[]) {
      return nullSafeToString((long[]) obj);
    }
    if (obj instanceof short[]) {
      return nullSafeToString((short[]) obj);
    }
    String str = obj.toString();
    return (str != null ? str : EMPTY_STRING);
  }
}
