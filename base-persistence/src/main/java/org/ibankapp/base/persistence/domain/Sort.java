/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.ibankapp.base.persistence.BasePersistenceException;
import org.ibankapp.base.util.StringUtils;

/**
 * 排序请求配置类.
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class Sort implements Iterable<Sort.Order>, Serializable {

  /**
   * 默认排序方向设置为正序.
   */
  private static final Direction DEFAULT_DIRECTION = Direction.ASC;
  private static final long serialVersionUID = 5737186511678863905L;
  /**
   * 排序配置列表.
   */
  private final List<Order> orders;

  /**
   * 构造函数.
   *
   * @param orders 排序配置列表
   */
  public Sort(Order... orders) {
    this(Arrays.asList(orders));
  }

  /**
   * 构造函数.
   *
   * @param orders 排序配置列表
   * @throws BasePersistenceException 当排序配置列表为空或null时抛出此异常，messageId为E-BASE-PERSISTANCE-000002
   */
  public Sort(List<Order> orders) {

    if (null == orders || orders.isEmpty()) {
      throw new BasePersistenceException("E-BASE-PERSISTENCE-000002");
    }

    this.orders = orders;
  }

  /**
   * 构造函数，按默认正序方向进行排序.
   *
   * @param properties 排序属性列表
   */
  public Sort(String... properties) {
    this(DEFAULT_DIRECTION, properties);
  }

  /**
   * 构造函数.
   *
   * @param direction 排序方向
   * @param properties 排序属性列表
   */
  public Sort(Direction direction, String... properties) {

    this(direction, Arrays.asList(properties));
    //this(direction, properties == null ? new ArrayList<Object>() : Arrays.asList(properties));
  }

  /**
   * 构造函数.
   *
   * @param direction 排序方向
   * @param properties 排序属性列表
   * @throws BasePersistenceException 当排序属性列表为空或null时抛出此异常，messageId为E-BASE-PERSISTANCE-000002
   */
  public Sort(Direction direction, List<String> properties) {

    if (properties == null || properties.isEmpty()) {
      throw new BasePersistenceException("E-BASE-PERSISTENCE-000002");
    }

    this.orders = new ArrayList<Order>(properties.size());

    for (String property : properties) {
      this.orders.add(new Order(direction, property));
    }
  }

  /**
   * 将当前排序配置与传入的配置进行and操作.
   *
   * @param sort 排序请求配置对象
   * @return 新的排序请求配置对象
   */
  public Sort and(Sort sort) {

    if (sort == null) {
      return this;
    }

    ArrayList<Order> these = new ArrayList<Order>(this.orders);

    for (Order order : sort) {
      these.add(order);
    }

    return new Sort(these);
  }

  /**
   * 获取 指定属性的排序对象.
   *
   * @param property 参与排序的属性
   * @return 指定属性的排序对象
   */
  public Order getOrderFor(String property) {

    for (Order order : this) {
      if (order.getProperty().equals(property)) {
        return order;
      }
    }

    return null;
  }

  /**
   * 获取 排序对象迭代器.
   *
   * @return 排序对象迭代器
   */
  public Iterator<Order> iterator() {
    return this.orders.iterator();
  }

  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }

    if (!(obj instanceof Sort)) {
      return false;
    }

    Sort that = (Sort) obj;

    return this.orders.equals(that.orders);
  }

  @Override
  public int hashCode() {

    int result = 17;
    result = 31 * result + orders.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return StringUtils.collectionToCommaDelimitedString(orders);
  }

  /**
   * 排序方向枚举.
   */
  public enum Direction {
    /**
     * 正序.
     */
    ASC,
    /**
     * 逆序.
     */
    DESC;

    /**
     * 根据枚举字符串获取枚举对象.
     *
     * @param value 枚举字符串
     * @return 排序方向枚举对象
     * @throws BasePersistenceException 当传入值为范围外字符串是抛出此异常，messageId为E-BASE-PERSISTANCE-000003
     */
    public static Direction fromString(String value) {

      try {
        return Direction.valueOf(value.toUpperCase(Locale.US));
      } catch (Exception e) {
        throw new BasePersistenceException("E-BASE-PERSISTENCE-000003", value).initCause(e);
      }
    }

    /**
     * 根据枚举字符串获取枚举对象,当传入value为非法字符串时，返回null.
     *
     * @param value 枚举字符串
     * @return 排序方向枚举对象
     */
    public static Direction fromStringOrNull(String value) {

      try {
        return fromString(value);
      } catch (BasePersistenceException e) {
        return null;
      }
    }
  }

  /**
   * 排序类
   *
   * @author <a href="http://www.ibankapp.org">ibankapp</a>
   * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
   * @since 1.0.0
   */
  public static class Order implements Serializable {

    private static final long serialVersionUID = 1522511010900108987L;
    //private static final boolean DEFAULT_IGNORE_CASE = false;

    /**
     * 排序方向.
     */
    private final Direction direction;

    /**
     * 排序属性.
     */
    private final String property;
    //private final boolean ignoreCase;
    //private final NullHandling nullHandling;

    /**
     * 构造函数.
     *
     * @param direction 排序方向
     * @param property 排序属性
     * @throws BasePersistenceException 当传入的property属性为空时抛出此异常,messageId为E-BASE-PERSISTENCE-000002
     */
    public Order(Direction direction, String property) {
      if (!StringUtils.hasText(property)) {
        throw new BasePersistenceException("E-BASE-PERSISTENCE-000002");
      }

      this.direction = direction == null ? DEFAULT_DIRECTION : direction;
      this.property = property;
    }


    /**
     * 构造函数.
     *
     * @param property 排序属性
     */
    public Order(String property) {
      this(DEFAULT_DIRECTION, property);
    }

    /**
     * 获取 排序方向.
     *
     * @return 排序方向
     */
    public Direction getDirection() {
      return direction;
    }

    /**
     * 获取 排序属性.
     *
     * @return 排序属性
     */
    public String getProperty() {
      return property;
    }

    /**
     * 获取 是否正序排序.
     *
     * @return 是否正序排序
     */
    public boolean isAscending() {
      return this.direction.equals(Direction.ASC);
    }


    /**
     * 根据当前排序对象属性配置排序方向.
     *
     * @param direction 排序方向
     * @return 新排序对象
     */
    public Order with(Direction direction) {
      return new Order(direction, this.property);
    }

    /**
     * 根据当前排序对象方向配置排序属性.
     *
     * @param properties 排序属性列表
     * @return 排序配置对象
     */
    public Sort withProperties(String... properties) {
      return new Sort(this.direction, properties);
    }


    @Override
    public int hashCode() {

      int result = 17;

      result = 31 * result + direction.hashCode();
      result = 31 * result + property.hashCode();

      return result;
    }

    @Override
    public boolean equals(Object obj) {

      if (this == obj) {
        return true;
      }

      if (!(obj instanceof Order)) {
        return false;
      }

      Order that = (Order) obj;

      return this.direction.equals(that.direction) && this.property.equals(that.property);
    }

    @Override
    public String toString() {
      return String.format("%s: %s", property, direction);
    }
  }
}
