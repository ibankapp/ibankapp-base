/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.util;

import org.ibankapp.base.persistence.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

public class QueryUtils {

    public static List<Order> toOrders(Sort sort, Root<?> root, CriteriaBuilder cb) {

        List<javax.persistence.criteria.Order> orders = new ArrayList<>();

        if (sort == null) {
            return orders;
        }


        for (Sort.Order order : sort) {
            orders.add(toJpaOrder(order, root, cb));
        }

        return orders;
    }

    private static Order toJpaOrder(org.ibankapp.base.persistence.domain.Sort.Order order, Root<?> root,
                                    CriteriaBuilder cb) {

        if (order.isIgnoreCase()) {
            String lower = order.getProperty().toLowerCase();
            return order.isAscending() ? cb.asc(root.get(lower)) : cb.desc(root.get(lower));
        } else {
            return order.isAscending() ? cb.asc(root.get(order.getProperty())) : cb.desc(root.get(order.getProperty()));
        }
    }
}
