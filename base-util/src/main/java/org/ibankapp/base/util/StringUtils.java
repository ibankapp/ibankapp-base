/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 *
 * reference from springframework
 */

package org.ibankapp.base.util;

import java.util.Collection;
import java.util.Iterator;
import java.util.UUID;

/**
 * 字符串应用类
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class StringUtils {

  public static boolean isEmpty(String str) {

    return str == null || str.trim().length() == 0;
  }

  /**
   * 将一个{@link Collection}类型转换为分隔符分割的字符串,例如CSV.
   *
   * <p>可以用于{@code toString()} 实现.
   *
   * @param coll 需要被转换的集合类型
   * @param delim 使用的分隔符(一般为",")
   * @param prefix 每个被分隔字符串的前缀
   * @param suffix 每个被分隔字符串的后缀
   * @return 分隔后的字符串
   */
  public static String collectionToDelimitedString(Collection<?> coll, String delim, String prefix,
      String suffix) {
    if (coll == null || coll.isEmpty()) {
      return "";
    }
    StringBuilder sb = new StringBuilder();
    Iterator<?> it = coll.iterator();
    while (it.hasNext()) {
      sb.append(prefix).append(it.next()).append(suffix);
      if (it.hasNext()) {
        sb.append(delim);
      }
    }
    return sb.toString();
  }

  /**
   * 将一个{@link Collection}类型转换为分隔符分割的字符串,例如CSV.
   *
   * <p>可以用于{@code toString()} 实现.
   *
   * @param coll 需要被转换的{@code Collection}
   * @param delim 使用的分隔符(一般为",")
   * @return 分隔后的字符串
   */
  public static String collectionToDelimitedString(Collection<?> coll, String delim) {
    return collectionToDelimitedString(coll, delim, "", "");
  }

  /**
   * 将一个{@link Collection}类型转换为分隔符分割的字符串,使用","作为分隔符.
   *
   * <p>可以用于{@code toString()} 实现.
   *
   * @param coll 需要被转换的{@code Collection}
   * @return 分隔后的字符串
   */
  public static String collectionToCommaDelimitedString(Collection<?> coll) {
    return collectionToDelimitedString(coll, ",");
  }

  /**
   * 检查传入的{@code CharSequence}是否为null或长度为0.
   *
   * <p>注意: 此方法在检查空格{@code CharSequence}时返回 {@code true}
   *
   * <p><pre class="code">
   * StringUtils.hasLength(null) = false
   * StringUtils.hasLength("") = false
   * StringUtils.hasLength(" ") = true
   * StringUtils.hasLength("Hello") = true
   * </pre>
   *
   * @param str 被检查的字符串
   * @return {@code true} if the {@code CharSequence} is not {@code null} and has length
   * @see #hasText(String)
   */
  public static boolean hasLength(CharSequence str) {
    return (str != null && str.length() > 0);
  }

  /**
   * 检查传入的 {@code CharSequence} 是否包含确定的非空字符.
   *
   * <p><pre class="code">
   * StringUtils.hasText(null) = false
   * StringUtils.hasText("") = false
   * StringUtils.hasText(" ") = false
   * StringUtils.hasText("12345") = true
   * StringUtils.hasText(" 12345 ") = true
   * </pre>
   *
   * @param str 需要被检查的CharSequence
   * @return 当str不为null，长度不为0且至少包含一个非白空格字符时返回true，否则返回false
   * @see Character#isWhitespace
   */
  public static boolean hasText(CharSequence str) {
    if (!hasLength(str)) {
      return false;
    }
    int strLen = str.length();
    for (int i = 0; i < strLen; i++) {
      if (!Character.isWhitespace(str.charAt(i))) {
        return true;
      }
    }
    return false;
  }

  /**
   * 检查传入的 {@code String} 是否包含确定的非空字符.
   *
   * @param str 需要被检查的CharSequence
   * @return 当str不为null，长度不为0且至少包含一个非白空格字符时返回true，否则返回false
   * @see #hasText(CharSequence)
   */
  public static boolean hasText(String str) {
    return hasText((CharSequence) str);
  }

  /**
   * 获取随机的UUID字符串.
   *
   * @return 返回随机的不包含分隔符的UUID字符串
   */
  public static String getRandomUuid() {
    String str = UUID.randomUUID().toString();
    return str.substring(0, 8) + str.substring(9, 13) + str.substring(14, 18) + str
        .substring(19, 23) + str
        .substring(24);
  }
}
