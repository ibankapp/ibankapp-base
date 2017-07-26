/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.IdentifiableType;
import javax.persistence.metamodel.ManagedType;
import org.ibankapp.base.persistence.domain.Specification;

/**
 * 通过ID集合进行实体查询的Specification
 *
 * @param <T> 实体类型
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class ByIdsSpecification<T> implements Specification<T> {

  public ParameterExpression<Iterable> parameter;
  private EntityManager em;
  private Class entityClass;

  public ByIdsSpecification(EntityManager em, Class entityClass) {
    this.em = em;
    this.entityClass = entityClass;
  }

  /**
   * 获取按ID集合进行实体查询的Predicate.
   *
   * @param root 实体类ROOT
   * @param query 条件查询
   * @param cb 查询构建器
   */
  @Override
  @SuppressWarnings("unchecked")
  public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

    ManagedType type = em.getMetamodel().managedType(entityClass);

    IdentifiableType identifiableType = (IdentifiableType) type;

    Path<?> path = root.get(identifiableType.getId(identifiableType.getIdType().getJavaType()));

    parameter = cb.parameter(Iterable.class);
    return path.in(parameter);
  }
}
