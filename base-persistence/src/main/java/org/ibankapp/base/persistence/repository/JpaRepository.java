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

/**
 * Jpa操作接口
 *
 * @author codelder@ibankapp.org
 * @since 1.0.0.0
 */
public interface JpaRepository {

    /**
     * 持久化操作，执行此函数后entity将变为持久态
     *
     * @param entity 需要持久化的jpa实体对象
     * @param <T>    jpa实体对象类型
     * @return 持久化后的实体对象
     */
    <T> T persist(T entity);

    /**
     * 用游离态的Jpa实体对象更新数据库中相同ID的数据记录
     *
     * @param entity 游离态的Jpa实体对象
     * @param <T>    Jpa实体对象类型
     * @return 传入的游离态Jpa实体对象
     */
    <T> T merge(T entity);

    /**
     * 批量持久化操作
     *
     * @param entities Jpa实体对象集合
     * @param <T>      Jpa实体对象类型
     * @return 持久化后的Jpa实体对象集合
     */
    <T> List<T> persist(Iterable<T> entities);

    /**
     * 批量更新操作
     *
     * @param entities 游离态的Jpa实体对象集合
     * @param <T>      Jpa实体对象类型
     * @return 传入的游离态Jpa实体对象集合
     */
    <T> List<T> merge(Iterable<T> entities);

    /**
     * 根据ID和Jpa实体类型查询记录
     *
     * @param entityClass Jpa实体类型
     * @param id          实体对象ID
     * @param <T>         Jpa实体类型
     * @param <ID>        实体对象ID类型
     * @return 符合条件的实体对象
     */
    <T, ID extends Serializable> T findOne(Class<T> entityClass, ID id);

    /**
     * 根据ID和Jpa实体类型检查记录是否存在
     *
     * @param entityClass Jpa实体类型
     * @param id          实体对象ID
     * @param <T>         Jpa实体类型
     * @param <ID>        实体对象ID类型
     * @return 是否存在记录
     */
    <T, ID extends Serializable> boolean exist(Class<T> entityClass, ID id);

    /**
     * 查找传入的实体对象类型的所有实体对象
     *
     * @param entityClass Jpa实体类型
     * @param <T>         Jpa实体类型
     * @return 所有该实体类型的实体对象List
     */
    <T> List<T> findAll(Class<T> entityClass);

    <T> Page<T> findAll(Class<T> entityClass, Pageable pageable);

    <T> List<T> findAll(Class<T> entityClass, Sort sort);

    <T> List<T> findAll(Class<T> entityClass, Specification<T> spec);

    <T> Page<T> findAll(Class<T> entityClass, Specification<T> spec, Pageable pageable);

    <T> List<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort);

    <T> Page<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort, Pageable pageable);

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
