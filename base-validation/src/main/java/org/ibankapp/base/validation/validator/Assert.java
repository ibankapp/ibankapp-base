/*
 * Copyright 2002-2017 the original author or authors.
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

package org.ibankapp.base.validation.validator;

import org.ibankapp.base.util.CollectionUtils;
import org.ibankapp.base.util.ObjectUtils;
import org.ibankapp.base.util.StringUtils;
import org.ibankapp.base.exception.BaseValidationException;

import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Assertion utility class that assists in validating arguments.
 * <p>
 * <p>Useful for identifying programmer errors early and clearly at runtime.
 * <p>
 * <p>For example, if the contract of a public method states it does not
 * allow {@code null} arguments, {@code Assert} can be used to validate that
 * contract. Doing this clearly indicates a contract violation when it
 * occurs and protects the class's invariants.
 * <p>
 * <p>Typically used to validate method arguments rather than configuration
 * properties, to check for cases that are usually programmer errors rather
 * than configuration errors. In contrast to configuration initialization
 * code, there is usually no point in falling back to defaults in such methods.
 * <p>
 * <p>This class is similar to JUnit's assertion library. If an argument value is
 * deemed invalid, an {@link BaseValidationException} is thrown (typically).
 * For example:
 * <p>
 * <pre class="code">
 * Assert.notNull(clazz, "The class must not be null");
 * Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
 * <p>
 * <p>Mainly for internal use within the framework; consider
 * <a href="http://commons.apache.org/proper/commons-lang/">Apache's Commons Lang</a>
 * for a more comprehensive suite of {@code String} utilities.
 *
 * @author Keith Donald
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Colin Sampaleanu
 * @author Rob Harrop
 * @since 1.1.2
 */

public abstract class Assert {

  /**
   * Assert a boolean expression, throwing an {@code BaseValidationException}
   * if the expression evaluates to {@code false}.
   * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
   *
   * @param expression a boolean expression
   * @param message    the exception message to use if the assertion fails
   * @throws BaseValidationException if {@code expression} is {@code false}
   */
  public static void isTrue(boolean expression, String message) {
    if (!expression) {
      throw new BaseValidationException("E-BASE-VALIDATION-000003", message);
    }
  }

  /**
   * Assert that an object is {@code null}.
   * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
   *
   * @param object  the object to check
   * @param message the exception message to use if the assertion fails
   * @throws BaseValidationException if the object is not {@code null}
   */
  public static void isNull(Object object, String message) {
    if (object != null) {
      throw new BaseValidationException("E-BASE-VALIDATION-000003", message);
    }
  }

  /**
   * Assert that an object is not {@code null}.
   * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
   *
   * @param object  the object to check
   * @param message the exception message to use if the assertion fails
   * @throws BaseValidationException if the object is {@code null}
   */
  public static void notNull(Object object, String message) {
    if (object == null) {
      throw new BaseValidationException("E-BASE-VALIDATION-000003", message);
    }
  }

  /**
   * Assert that the given String is not empty; that is,
   * it must not be {@code null} and not the empty String.
   * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
   *
   * @param text    the String to check
   * @param message the exception message to use if the assertion fails
   * @throws BaseValidationException if the text is empty
   * @see StringUtils#hasLength
   */
  public static void hasLength(String text, String message) {
    if (!StringUtils.hasLength(text)) {
      throw new BaseValidationException("E-BASE-VALIDATION-000003", message);
    }
  }

  /**
   * Assert that the given String contains valid text content; that is, it must not
   * be {@code null} and must contain at least one non-whitespace character.
   * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
   *
   * @param text    the String to check
   * @param message the exception message to use if the assertion fails
   * @throws BaseValidationException if the text does not contain valid text content
   * @see StringUtils#hasText
   */
  public static void hasText(String text, String message) {
    if (!StringUtils.hasText(text)) {
      throw new BaseValidationException("E-BASE-VALIDATION-000003", message);
    }
  }

  /**
   * Assert that the given text does not contain the given substring.
   * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
   *
   * @param textToSearch the text to search
   * @param substring    the substring to find within the text
   * @param message      the exception message to use if the assertion fails
   * @throws BaseValidationException if the text contains the substring
   */
  public static void doesNotContain(String textToSearch, String substring, String message) {
    if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring)
            && textToSearch.contains(substring)) {
      throw new BaseValidationException("E-BASE-VALIDATION-000003", message);
    }
  }

  /**
   * Assert that an array contains elements; that is, it must not be
   * {@code null} and must contain at least one element.
   * <pre class="code">Assert.notEmpty(array, "The array must contain elements");</pre>
   *
   * @param array   the array to check
   * @param message the exception message to use if the assertion fails
   * @throws BaseValidationException if the object array is {@code null} or contains no elements
   */
  public static void notEmpty(Object[] array, String message) {
    if (ObjectUtils.isEmpty(array)) {
      throw new BaseValidationException("E-BASE-VALIDATION-000003", message);
    }
  }

  /**
   * Assert that a collection contains elements; that is, it must not be
   * {@code null} and must contain at least one element.
   * <pre class="code">Assert.notEmpty(collection, "Collection must contain elements");</pre>
   *
   * @param collection the collection to check
   * @param message    the exception message to use if the assertion fails
   * @throws BaseValidationException if the collection is {@code null} or contains no elements
   */
  public static void notEmpty(Collection<?> collection, String message) {
    if (CollectionUtils.isEmpty(collection)) {
      throw new BaseValidationException("E-BASE-VALIDATION-000003", message);
    }
  }

  /**
   * Assert that a Map contains entries; that is, it must not be {@code null}
   * and must contain at least one entry.
   * <pre class="code">Assert.notEmpty(map, "Map must contain entries");</pre>
   *
   * @param map     the map to check
   * @param message the exception message to use if the assertion fails
   * @throws BaseValidationException if the map is {@code null} or contains no entries
   */
  public static void notEmpty(Map<?, ?> map, String message) {
    if (CollectionUtils.isEmpty(map)) {
      throw new BaseValidationException("E-BASE-VALIDATION-000003", message);
    }
  }

  /**
   * Assert that an array contains no {@code null} elements.
   * <p>
   * <p>Note: Does not complain if the array
   * is empty! <pre class="code">Assert.noNullElements(array, "The array must contain non-null
   * elements");</pre>
   *
   * @param array   the array to check
   * @param message the exception message to use if the assertion fails
   * @throws BaseValidationException if the object array contains a {@code null} element
   */
  public static void noNullElements(Object[] array, String message) {
    if (array != null) {
      for (Object element : array) {
        if (element == null) {
          throw new BaseValidationException("E-BASE-VALIDATION-000003", message);
        }
      }
    }
  }


  /**
   * Assert that the provided object is an instance of the provided class.
   * <pre class="code">Assert.instanceOf(Foo.class, foo, "Foo expected");</pre>
   *
   * @param type    the type to check against
   * @param obj     the object to check
   * @param message a message which will be prepended to provide further context. If it is empty or
   *                ends in ":" or ";" or "," or ".", a full exception message will be appended. If it ends in a
   *                space, the name of the offending object's type will be appended. In any other case, a ":" with
   *                a space and the name of the offending object's type will be appended.
   * @throws BaseValidationException if the object is not an instance of type
   */
  public static void isInstanceOf(Class<?> type, Object obj, String message) {
    notNull(type, "Type to check against must not be null");
    if (!type.isInstance(obj)) {
      instanceCheckFailed(type, obj, message);
    }
  }

  /**
   * Assert that the provided object is an instance of the provided class.
   * <pre class="code">Assert.instanceOf(Foo.class, foo);</pre>
   *
   * @param type the type to check against
   * @param obj  the object to check
   * @throws BaseValidationException if the object is not an instance of type
   */
  public static void isInstanceOf(Class<?> type, Object obj) {
    isInstanceOf(type, obj, "");
  }

  /**
   * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
   * <pre class="code">Assert.isAssignable(Number.class, myClass, "Number expected");</pre>
   *
   * @param superType the super type to check against
   * @param subType   the sub type to check
   * @param message   a message which will be prepended to provide further context. If it is empty or
   *                  ends in ":" or ";" or "," or ".", a full exception message will be appended. If it ends in a
   *                  space, the name of the offending sub type will be appended. In any other case, a ":" with a
   *                  space and the name of the offending sub type will be appended.
   * @throws BaseValidationException if the classes are not assignable
   */
  public static void isAssignable(Class<?> superType, Class<?> subType, String message) {
    notNull(superType, "Super type to check against must not be null");
    if (subType == null || !superType.isAssignableFrom(subType)) {
      assignableCheckFailed(superType, subType, message);
    }
  }

  /**
   * Assert that {@code superType.isAssignableFrom(subType)} is {@code true}.
   * <pre class="code">Assert.isAssignable(Number.class, myClass);</pre>
   *
   * @param superType the super type to check
   * @param subType   the sub type to check
   * @throws BaseValidationException if the classes are not assignable
   */
  public static void isAssignable(Class<?> superType, Class<?> subType) {
    isAssignable(superType, subType, "");
  }

  public static void match(String regex, String text, String message) {
    hasText(regex, "正则表达式不能为空");
    hasText(text, "待验证字符串不能为空");

    if (!StringUtils.hasText(message)) {
      message = "待验证字符串" + text + "不满足正则表达式\"" + regex + "\"";
    }

    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    if (!matcher.matches()) {
      throw new BaseValidationException("E-BASE-VALIDATION-000003", message);
    }
  }

  private static void instanceCheckFailed(Class<?> type, Object obj, String msg) {
    String className = (obj != null ? obj.getClass().getName() : "null");
    String result = "";
    boolean defaultMessage = true;
    if (StringUtils.hasLength(msg)) {
      if (endsWithSeparator(msg)) {
        result = msg + " ";
      } else {
        result = messageWithTypeName(msg, className);
        defaultMessage = false;
      }
    }
    if (defaultMessage) {
      result = result + ("Object of class [" + className + "] must be an instance of " + type);
    }
    throw new BaseValidationException("E-BASE-VALIDATION-000003", result);
  }

  private static void assignableCheckFailed(Class<?> superType, Class<?> subType, String msg) {
    String result = "";
    boolean defaultMessage = true;
    if (StringUtils.hasLength(msg)) {
      if (endsWithSeparator(msg)) {
        result = msg + " ";
      } else {
        result = messageWithTypeName(msg, subType);
        defaultMessage = false;
      }
    }
    if (defaultMessage) {
      result = result + (subType + " is not assignable to " + superType);
    }
    throw new BaseValidationException("E-BASE-VALIDATION-000003", result);
  }

  private static boolean endsWithSeparator(String msg) {
    return (msg.endsWith(":") || msg.endsWith(";") || msg.endsWith(",") || msg.endsWith("."));
  }

  private static String messageWithTypeName(String msg, Object typeName) {
    return msg + (msg.endsWith(" ") ? "" : ": ") + typeName;
  }
}
