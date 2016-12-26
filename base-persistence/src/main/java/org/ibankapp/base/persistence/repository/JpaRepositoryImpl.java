/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.repository;

import org.ibankapp.base.persistence.Specification.ByIdsSpecification;
import org.ibankapp.base.persistence.domain.Page;
import org.ibankapp.base.persistence.domain.Pageable;
import org.ibankapp.base.persistence.domain.Sort;
import org.ibankapp.base.persistence.domain.Specification;
import org.ibankapp.base.persistence.validation.validator.UniqueValidator;
import org.ibankapp.base.validation.validator.BeanValidator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import static org.ibankapp.base.persistence.util.QueryUtils.toOrders;

public class JpaRepositoryImpl implements JpaRepository {

    @PersistenceContext
    private EntityManager em;


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
    public <T> T merge(T entity) {
        validateEntity(entity);
        return em.merge(entity);
    }

    @Override
    public <T> List<T> persist(Iterable<T> entities) {

        List<T> result = new ArrayList<>();

        if (entities == null) {
            return result;
        }

        for (T entity : entities) {
            result.add(persist(entity));
        }

        return result;
    }

    @Override
    public <T> List<T> merge(Iterable<T> entities) {

        List<T> result = new ArrayList<>();

        if (entities == null) {
            return result;
        }

        for (T entity : entities) {
            result.add(merge(entity));
        }

        return result;
    }

    @Override
    public <T, ID extends Serializable> T findOne(Class<T> entityClass, ID id) {

        return em.find(entityClass, id);
    }

    @Override
    public <T, ID extends Serializable> boolean exist(Class<T> entityClass, ID id) {

        return findOne(entityClass, id) != null;
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass) {

        return findAll(entityClass, null, null, null, null);
    }

    @Override
    public <T> Page<T> findAll(Class<T> entityClass, Pageable pageable) {
        return findAll(entityClass, null, pageable);
    }


    @Override
    public <T> List<T> findAll(Class<T> entityClass, Sort sort) {
        return findAll(entityClass, null, sort, null, null);
    }


    @Override
    public <T> List<T> findAll(Class<T> entityClass, Specification<T> spec) {

        return findAll(entityClass, spec, null, null, null);
    }

    @Override
    public <T> Page<T> findAll(Class<T> entityClass, Specification<T> spec, Pageable pageable) {
        return findAll(entityClass, spec, null, pageable);
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort) {

        return findAll(entityClass, spec, sort, null, null);
    }

    @Override
    public <T> Page<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort, Pageable pageable) {

        TypedQuery<T> query = getQuery(spec, entityClass, sort);
        long totalCount = count(entityClass, spec);

        if (pageable == null) {
            return new Page<>(query.getResultList(), totalCount);
        }

        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getSize());


        return new Page<>(totalCount > pageable.getOffset() ? query.getResultList() : Collections.emptyList(),
                totalCount, pageable.getSize(), pageable.getPage());
    }

    @Override
    public <T> List<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort, LockModeType lockMode,
                               Map<String, Object> queryHints) {


        TypedQuery<T> query = getQuery(spec, entityClass, sort);

        if (lockMode != null) {
            query = query.setLockMode(lockMode);
        }

        if (queryHints != null && queryHints.size() != 0) {

            for (Map.Entry<String, Object> hint : queryHints.entrySet()) {
                query.setHint(hint.getKey(), hint.getValue());
            }
        }

        return query.getResultList();
    }


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

    private <S, T, U extends T> Root<U> applySpecificationToCriteria(Specification<U> spec, Class<U> domainClass,
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
    public <T, ID extends Serializable> List<T> findAll(Class<T> entityClass, Iterable<ID> ids, boolean isBatch) {

        if (ids == null || !ids.iterator().hasNext()) {
            return Collections.emptyList();
        }

        if (!isBatch) {
            List<T> results = new ArrayList<>();

            for (Serializable id : ids) {
                T entity = findOne(entityClass, id);
                if (entity != null)
                    results.add(findOne(entityClass, id));
            }

            return results;
        }

        ByIdsSpecification<T> specification = new ByIdsSpecification<>(em, entityClass);
        TypedQuery<T> query = getQuery(specification, entityClass, null);

        return query.setParameter(specification.parameter, ids).getResultList();
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
    public <T, ID extends Serializable> void delete(Class<T> entityClass, ID id) {

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

        findAll(entityClass).forEach(this::delete);
    }
}
