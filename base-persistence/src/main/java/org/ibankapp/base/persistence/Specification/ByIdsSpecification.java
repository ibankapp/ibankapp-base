/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.Specification;

import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.persistence.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;

public class ByIdsSpecification<T> implements Specification<T> {

    private EntityManager em;

    private Class entityClass;

    public ParameterExpression<Iterable> parameter;

    public ByIdsSpecification(EntityManager em,Class entityClass) {
        this.em = em;
        this.entityClass = entityClass;
    }

    @SuppressWarnings("unchecked")
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

        ManagedType type = em.getMetamodel().managedType(entityClass);

        IdentifiableType identifiableType = (IdentifiableType) type;

        Path<?> path = root.get(identifiableType.getId(identifiableType.getIdType().getJavaType()));

        parameter = cb.parameter(Iterable.class);
        return path.in(parameter);
    }
}
