/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.repository;

import static org.ibankapp.base.persistence.util.QueryUtils.toOrders;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.ibankapp.base.persistence.BasePersistenceException;
import org.ibankapp.base.persistence.domain.Page;
import org.ibankapp.base.persistence.domain.Pageable;
import org.ibankapp.base.persistence.domain.Sort;
import org.ibankapp.base.persistence.domain.Specification;
import org.ibankapp.base.persistence.specification.ByIdsSpecification;
import org.ibankapp.base.persistence.validation.validator.UniqueValidator;
import org.ibankapp.base.validation.validator.BeanValidator;

/**
 * JpaRepository默认实现类.
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class JpaRepositoryImpl implements JpaRepository {

  /**
   * Jpa EntityManager.
   */
  @PersistenceContext
  private EntityManager em;


  /**
   * 对entity进行合法性校验，并检查数据库中是否有重复记录.
   *
   * @param entity 需要交验的实体bean
   */
  private void validateEntity(Object entity) {
    if (entity == null) {
      throw new NullPointerException();
    }

    BeanValidator.validate(entity);
    UniqueValidator.validate(entity, em);
  }

  @Override
  public <T> T persist(T entity) {

    validateEntity(entity);
    em.persist(entity);
    return entity;
  }

  @Override
  public <T> List<T> persist(Iterable<T> entities) {

    List<T> result = new ArrayList<T>();

    if (entities == null) {
      return result;
    }

    for (T entity : entities) {
      result.add(persist(entity));
    }

    return result;
  }

  @Override
  public <T> T merge(T entity) {
    validateEntity(entity);
    return em.merge(entity);
  }

  @Override
  public <T> List<T> merge(Iterable<T> entities) {

    List<T> result = new ArrayList<T>();

    if (entities == null) {
      return result;
    }

    for (T entity : entities) {
      result.add(merge(entity));
    }

    return result;
  }

  @Override
  public <T, D extends Serializable> T findOne(Class<T> entityClass, D id) {

    return em.find(entityClass, id);
  }

  @Override
  public <T, D extends Serializable> T findOne(Class<T> entityClass, D id, LockModeType lockMode) {
    return em.find(entityClass, id, lockMode);
  }

  @Override
  public <T> T findOne(Specification<T> spec, Class<T> entityClass, LockModeType lockMode) {

    TypedQuery<T> query = getQuery(spec, entityClass, null);

    return getSingleResult(query, lockMode);
  }

  @Override
  public <T> T findOne(Specification<T> spec, Class<T> entityClass) {
    return findOne(spec, entityClass, null);
  }

  @Override
  public <T> T findOne(String jpql, Class<T> entityClass, LockModeType lockMode) {
    TypedQuery<T> query = em.createQuery(jpql, entityClass);

    return getSingleResult(query, lockMode);
  }

  @Override
  public <T> T findOne(String jpql, Class<T> entityClass) {
    return findOne(jpql, entityClass, null);
  }

  private <T> T getSingleResult(TypedQuery<T> query, LockModeType lockMode) {
    if (lockMode != null) {
      query.setLockMode(lockMode);
    }
    return query.getSingleResult();
  }

  @Override
  public <T, D extends Serializable> boolean exist(Class<T> entityClass, D id) {

    return findOne(entityClass, id) != null;
  }

  @Override
  public <T> List<T> findAll(Class<T> entityClass) {

    return findAll(entityClass, null, null, (LockModeType) null);
  }

  @Override
  public <T> Page<T> findAll(Class<T> entityClass, Pageable pageable) {
    return findAll(entityClass, (Specification<T>) null, pageable);
  }

  @Override
  public <T> List<T> findAll(Class<T> entityClass, Sort sort) {
    return findAll(entityClass, null, sort, (LockModeType) null);
  }

  @Override
  public <T> List<T> findAll(Class<T> entityClass, Specification<T> spec) {

    return findAll(entityClass, spec, null, (LockModeType) null);
  }

  @Override
  public <T> Page<T> findAll(Class<T> entityClass, Specification<T> spec, Pageable pageable) {
    return findAll(entityClass, spec, null, pageable);
  }

  @Override
  public <T> List<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort) {

    return findAll(entityClass, spec, sort, (LockModeType) null);
  }

  @Override
  public <T> Page<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort,
      Pageable pageable) {

    TypedQuery<T> query = getQuery(spec, entityClass, sort);
    long totalCount = count(entityClass, spec);

    return getPage(query, pageable, totalCount);
  }

  @Override
  public <T> List<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort,
      LockModeType lockMode) {

    TypedQuery<T> query = getQuery(spec, entityClass, sort);

    if (lockMode != null) {
      query = query.setLockMode(lockMode);
    }

    return query.getResultList();
  }

  @Override
  public <T, D extends Serializable> List<T> findAll(Class<T> entityClass, Iterable<D> ids,
      boolean isBatch) {

    if (ids == null || !ids.iterator().hasNext()) {
      return Collections.emptyList();
    }

    if (!isBatch) {
      List<T> results = new ArrayList<T>();

      for (Serializable id : ids) {
        T entity = findOne(entityClass, id);
        if (entity != null) {
          results.add(findOne(entityClass, id));
        }
      }

      return results;
    }

    ByIdsSpecification<T> specification = new ByIdsSpecification<T>(em, entityClass);
    TypedQuery<T> query = getQuery(specification, entityClass, null);

    return query.setParameter(specification.parameter, ids).getResultList();
  }

  @Override
  public <T> List<T> findAll(Class<T> entityClass, String jpql) {

    TypedQuery<T> query = em.createQuery(jpql, entityClass);

    return query.getResultList();
  }

  @Override
  public <T> Page<T> findAll(Class<T> entityClass, String jpql, Pageable pageable) {

    TypedQuery<T> query = em.createQuery(jpql, entityClass);

    long totalCount = count(jpql);

    return getPage(query, pageable, totalCount);
  }

  private <T> Page<T> getPage(TypedQuery<T> query, Pageable pageable, long totalCount) {

    if (pageable == null) {
      return new Page<T>(query.getResultList(), totalCount);
    }

    query.setFirstResult(pageable.getOffset());
    query.setMaxResults(pageable.getSize());

    if (totalCount > pageable.getOffset()) {
      return new Page<T>(query.getResultList(), totalCount, pageable.getSize(), pageable.getPage());
    } else {
      return new Page<T>(new ArrayList<T>(), totalCount, pageable.getSize(), pageable.getPage());
    }
  }

  /**
   * 根据查询条件、实体类型、排序规则获取TypedQuery.
   *
   * @param spec 查询条件
   * @param domainClass 实体类型
   * @param sort 排序规则
   * @param <T> 实体类型
   * @return TypeQuery
   */
  private <T> TypedQuery<T> getQuery(Specification<T> spec, Class<T> domainClass, Sort sort) {

    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<T> query = builder.createQuery(domainClass);

    Root<T> root = applySpecificationToCriteria(spec, domainClass, query);
    query.select(root);

    if (sort != null) {
      query.orderBy(toOrders(sort, root, builder));
    }

    return em.createQuery(query);

  }

  private <S, T, U extends T> Root<U> applySpecificationToCriteria(Specification<U> spec,
      Class<U> domainClass,
      CriteriaQuery<S> query) {
    Root<U> root = query.from(domainClass);

    if (spec == null) {
      return root;
    }

    CriteriaBuilder builder = em.getCriteriaBuilder();
    Predicate predicate = spec.toPredicate(root, query, builder);

    if (predicate != null) {
      query.where(predicate);
    }

    return root;
  }

  @Override
  public <T> long count(Class<T> entityClass) {
    String queryString = String.format("Select count(*) from %s", entityClass.getSimpleName());
    return em.createQuery(queryString, Long.class).getSingleResult();
  }

  @Override
  public <T> long count(Class<T> entityClass, Specification<T> spec) {

    CriteriaBuilder builder = em.getCriteriaBuilder();
    CriteriaQuery<Long> query = builder.createQuery(Long.class);

    Root<T> root = applySpecificationToCriteria(spec, entityClass, query);

    if (query.isDistinct()) {
      query.select(builder.countDistinct(root));
    } else {
      query.select(builder.count(root));
    }

    return em.createQuery(query).getSingleResult();
  }

  @Override
  public long count(String jpql) {

    Query query = em.createQuery(" select count (*) " + removeSelect(removeOrders(jpql)));

    return (Long) query.getSingleResult();

  }

  private String removeSelect(String jpql) {
    if (!StringUtils.hasText(jpql)) {
      throw new BasePersistenceException("E-BASE-PERSISTENCE-000005");
    }
    int beginPos = jpql.toLowerCase().indexOf("from");
    if (beginPos == -1) {
      throw new BasePersistenceException("E-BASE-PERSISTENCE-000004", jpql);
    }
    return jpql.substring(beginPos);
  }

  private String removeOrders(String jpql) {
    if (!StringUtils.hasText(jpql)) {
      throw new BasePersistenceException("E-BASE-PERSISTENCE-000005");
    }
    Pattern pattern = Pattern.compile("order\\s*by[\\w|\\W|\\s|\\S]*", Pattern.CASE_INSENSITIVE);
    Matcher matcher = pattern.matcher(jpql);
    StringBuffer sb = new StringBuffer();
    while (matcher.find()) {
      matcher.appendReplacement(sb, "");
    }
    matcher.appendTail(sb);
    return sb.toString();
  }

  @Override
  public <T, D extends Serializable> void delete(Class<T> entityClass, D id) {

    T entity = findOne(entityClass, id);

    if (entity == null) {
      throw new NullPointerException();
    }

    delete(entity);
  }

  @Override
  public <T> void delete(T entity) {
    em.remove(em.contains(entity) ? entity : em.merge(entity));
  }

  @Override
  public <T> void delete(Iterable<T> entities) {

    for (T entity : entities) {
      delete(entity);
    }
  }

  @Override
  public <T> void deleteAll(Class<T> entityClass) {

    List<T> entities = findAll(entityClass);

    for (T entity : entities) {
      this.delete(entity);
    }
  }

  @Override
  public <T> void uniqueValidate(T bean) {
    UniqueValidator.validate(bean, em);
  }
}
