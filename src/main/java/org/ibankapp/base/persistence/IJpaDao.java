/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence;

import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * jpa持久化数据操作接口
 *
 * @author codelder
 * @version 1.0.0, 16/09/21
 */
public interface IJpaDao {

    /**
     * 开始一个事务
     */
    void beginTrans();

    /**
     * 提交当前事务
     */
    void commitTrans();

    /**
     * 将实体类对象持久化,如model同id的数据存在在数据库中,
     * 用model的数据更新它,如不存在,新增model数据到数据库,
     * model对象还保持在游离态
     *
     * @param model 实体类对象
     */
    void merge(Model model);

    /**
     * 将实体类对象持久化,并将model状态变为持久态
     *
     * @param model 实体类对象
     */
    void persist(Model model);

    /**
     * 执行jpql语句并返回查询对象
     *
     * @param jpql jpql查询语句
     * @return 查询对象
     */
    Query query(String jpql);

    /**
     * 将持久态的实体对象model从数据库中删除
     *
     * @param model 持久态实体类对象
     */
    void remove(Model model);

    /**
     * 回滚当前事务
     */
    void rollbackTrans();

    /**
     * 获取实体管理器
     *
     * @return JPA实体管理器
     */
    EntityManager getEntityManager();

    /**
     * 设置实体管理器
     *
     * @param entityManager JPA实体管理器
     */
    void setEntityManager(EntityManager entityManager);

    /**
     * 按实体类主键获取指定实体类的持久化对象
     *
     * @param clazz 实体类类型
     * @param id    实体类Id
     * @return 实体类持久化对象
     */
    <T extends Model> T get(Class<T> clazz, Object id);
}
