/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.repository;

import org.ibankapp.base.persistence.domain.Page;
import org.ibankapp.base.persistence.domain.Pageable;
import org.ibankapp.base.persistence.domain.Sort;
import org.ibankapp.base.persistence.domain.Specification;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;

public interface JpaRepository {

    <T> T persist(T entity);

    <T> T merge(T entity);

    <T> List<T> persist(Iterable<T> entities);

    <T> List<T> merge(Iterable<T> entities);

    <T, ID extends Serializable> T findOne(Class<T> entityClass, ID id);

    <T, ID extends Serializable> boolean exist(Class<T> entityClass, ID id);

    <T> List<T> findAll(Class<T> entityClass);

    <T> Page<T> findAll(Class<T> entityClass,Pageable pageable);

    <T> List<T> findAll(Class<T> entityClass, Sort sort);

    <T> List<T> findAll(Class<T> entityClass, Specification<T> spec);

    <T> Page<T> findAll(Class<T> entityClass, Specification<T> spec, Pageable pageable);

    <T> List<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort);

    <T> Page<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort,Pageable pageable);

    <T> List<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort, LockModeType lockMode,
                        Map<String, Object> queryHints);

    <T, ID extends Serializable> List<T> findAll(Class<T> entityClass, Iterable<ID> ids, boolean isBatch);

    <T> long count(Class<T> entityClass);

    <T> long count(Class<T> entityClass, Specification<T> spec);

    <T, ID extends Serializable> void delete(Class<T> entityClass, ID id);

    <T> void delete(T entity);

    <T> void delete(Iterable<T> entities);

    <T> void deleteAll(Class<T> entityClass);

    <T> void uniqueValidate(T bean);
}
