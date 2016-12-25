/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.repository.test;

import org.ibankapp.base.persistence.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NullSpecification implements Specification<SimpleModel> {

    @Override
    public Predicate toPredicate(Root<SimpleModel> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        return null;
    }
}
