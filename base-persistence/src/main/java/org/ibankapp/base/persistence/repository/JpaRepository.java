/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.repository;

import java.io.Serializable;
import java.util.List;
import javax.persistence.LockModeType;
import org.ibankapp.base.persistence.domain.Page;
import org.ibankapp.base.persistence.domain.Pageable;
import org.ibankapp.base.persistence.domain.Sort;
import org.ibankapp.base.persistence.domain.Specification;

/**
 * Jpa操作接口
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public interface JpaRepository {

  /**
   * 持久化操作，执行此函数后entity将变为持久态.
   *
   * @param entity 需要持久化的jpa实体对象
   * @param <T> jpa实体对象类型
   * @return 持久化后的实体对象
   */
  <T> T persist(T entity);

  /**
   * 批量持久化操作.
   *
   * @param entities Jpa实体对象集合
   * @param <T> Jpa实体对象类型
   * @return 持久化后的Jpa实体对象集合
   */
  <T> List<T> persist(Iterable<T> entities);

  /**
   * 用游离态的Jpa实体对象更新数据库中相同ID的数据记录.
   *
   * @param entity 游离态的Jpa实体对象
   * @param <T> Jpa实体对象类型
   * @return 传入的游离态Jpa实体对象
   */
  <T> T merge(T entity);

  /**
   * 批量更新操作.
   *
   * @param entities 游离态的Jpa实体对象集合
   * @param <T> Jpa实体对象类型
   * @return 传入的游离态Jpa实体对象集合
   */
  <T> List<T> merge(Iterable<T> entities);

  /**
   * 根据ID和Jpa实体类型查询记录.
   *
   * @param entityClass Jpa实体类型
   * @param id 实体对象ID
   * @param <T> Jpa实体类型
   * @param <D> 实体对象ID类型
   * @return 符合条件的实体对象
   */
  <T, D extends Serializable> T findOne(Class<T> entityClass, D id);

  /**
   * 根据ID和JPA实体类型查询记录，并按照lockMode锁定记录.
   *
   * @param entityClass Jpa实体类型
   * @param id 实体对象ID
   * @param lockMode 锁模式
   * @param <T> Jpa实体类型
   * @param <D> 实体对象ID类型
   * @return 符合条件的实体对象
   */
  <T, D extends Serializable> T findOne(Class<T> entityClass, D id, LockModeType lockMode);

  /**
   * 根据ID和Jpa实体类型检查记录是否存在.
   *
   * @param entityClass Jpa实体类型
   * @param id 实体对象ID
   * @param <T> Jpa实体类型
   * @param <D> 实体对象ID类型
   * @return 是否存在记录
   */
  <T, D extends Serializable> boolean exist(Class<T> entityClass, D id);

  /**
   * 返回所有查询结果.
   *
   * @param entityClass Jpa实体类型
   * @param <T> Jpa实体类型
   * @return 查询结果List
   */
  <T> List<T> findAll(Class<T> entityClass);

  /**
   * 分页返回所有查询结果.
   *
   * @param entityClass Jpa实体类型
   * @param pageable 分页规则
   * @param <T> Jpa实体类型
   * @return 分页查询结果
   */
  <T> Page<T> findAll(Class<T> entityClass, Pageable pageable);

  /**
   * 按照排序规则返回查询结果.
   *
   * @param entityClass Jpa实体类型
   * @param sort 排序请求规则
   * @param <T> Jpa实体类型
   * @return 查询结果List
   */
  <T> List<T> findAll(Class<T> entityClass, Sort sort);

  /**
   * 按照查询条件返回查询结果.
   *
   * @param entityClass Jpa实体类型
   * @param spec 指定的条件对象
   * @param <T> Jpa实体类型
   * @return 查询结果List
   */
  <T> List<T> findAll(Class<T> entityClass, Specification<T> spec);

  /**
   * 按照查询条件分页返回查询结果.
   *
   * @param entityClass Jpa实体类型
   * @param spec 指定的查询条件
   * @param pageable 分页规则
   * @param <T> Jpa实体类型
   * @return 分页查询结果
   */
  <T> Page<T> findAll(Class<T> entityClass, Specification<T> spec, Pageable pageable);

  /**
   * 按照排序规则、查询条件返回查询结果.
   *
   * @param entityClass Jpa实体类型
   * @param spec 查询条件
   * @param sort 排序规则
   * @param <T> Jpa实体类型
   * @return 查询结果List
   */
  <T> List<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort);

  /**
   * 按照查询条件、排序规则、分页规则返回查询结果.
   *
   * @param entityClass Jpa实体类型
   * @param spec 查询条件
   * @param sort 排序规则
   * @param pageable 分页规则
   * @param <T> Jpa实体类型
   * @return 分页查询结果
   */
  <T> Page<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort, Pageable pageable);

  /**
   * 按照查询条件，排序规则返回查询结果，并按规则锁定查询结果.
   *
   * @param entityClass Jpa实体类型
   * @param spec 查询条件
   * @param sort 排序规则
   * @param lockMode 锁模式
   * @param <T> Jpa实体类型
   * @return 查询结果List
   */
  <T> List<T> findAll(Class<T> entityClass, Specification<T> spec, Sort sort,
      LockModeType lockMode);

  /**
   * 查询满足指定ID集合的数据记录.
   *
   * @param entityClass Jpa实体类型
   * @param ids ID集合
   * @param isBatch 是否批量查询
   * @param <T> Jpa实体类型
   * @param <D> ID类型
   * @return 查询结果List
   */
  <T, D extends Serializable> List<T> findAll(Class<T> entityClass, Iterable<D> ids,
      boolean isBatch);

  /**
   * 返回总记录条数.
   *
   * @param entityClass Jpa实体类型
   * @param <T> Jpa实体类型
   * @return 记录总条数
   */
  <T> long count(Class<T> entityClass);

  /**
   * 返回满足查询条件的记录条数.
   *
   * @param entityClass Jpa实体类型
   * @param spec 查询条件
   * @param <T> Jpa实体类型
   * @return 满足条件的记录条数
   */
  <T> long count(Class<T> entityClass, Specification<T> spec);

  /**
   * 删除指定ID的数据库记录.
   *
   * @param entityClass Jpa实体类型
   * @param id 实体对象ID
   * @param <T> Jpa实体类型
   * @param <D> 实体对象ID类型
   */
  <T, D extends Serializable> void delete(Class<T> entityClass, D id);

  /**
   * 从数据库中删除指定实体对象映射的记录.
   *
   * @param entity Jpa实体类型
   * @param <T> Jpa实体类型
   */
  <T> void delete(T entity);

  /**
   * 从数据库删除指定实体对象集合映射的所有记录.
   *
   * @param entities 实体对象集合
   * @param <T> Jpa实体类型
   */
  <T> void delete(Iterable<T> entities);

  /**
   * 删除所有记录.
   *
   * @param entityClass Jpa实体类型
   * @param <T> Jpa实体类型
   */
  <T> void deleteAll(Class<T> entityClass);

  /**
   * 校验bean如果保存到数据库是否满足唯一性要求，不满足抛出相应异常.
   *
   * @param bean 要检查的游离态实体对象
   * @param <T> Jpa实体类型
   */
  <T> void uniqueValidate(T bean);
}
