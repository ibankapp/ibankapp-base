/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.util;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;
import org.ibankapp.base.persistence.domain.Sort;

/**
 * 查询应用类.
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class QueryUtils {

  /**
   * 根据排序规则获取Jpa Order对象集合.
   *
   * @param sort 排序规则对象
   * @param root 实体类ROOT
   * @param cb 查询构建器
   * @return Jpa Order对象集合
   */
  public static List<Order> toOrders(Sort sort, Root<?> root, CriteriaBuilder cb) {

    List<Order> orders = new ArrayList<Order>();

    if (sort == null) {
      return orders;
    }

    for (Sort.Order order : sort) {
      orders.add(toJpaOrder(order, root, cb));
    }

    return orders;
  }

  /**
   * 根据排序规则获取JPA Order对象.
   *
   * @param order 排序对象中的Order对象
   * @param root 实体类ROOT
   * @param cb 查询构建器
   * @return Jpa Order对象
   */
  private static Order toJpaOrder(org.ibankapp.base.persistence.domain.Sort.Order order,
      Root<?> root,
      CriteriaBuilder cb) {
    return order.isAscending() ? cb.asc(root.get(order.getProperty()))
        : cb.desc(root.get(order.getProperty()));
  }
}
