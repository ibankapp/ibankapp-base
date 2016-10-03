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
     * 执行jpql语句并返回查询结果
     *
     * @param jpql jpql查询语句
     * @return 查询对象
     */
    Query createQuery(String jpql);

    /**
     * 保存持久化数据
     *
     * @param model 实体类
     */
    void persist(Model model);

    /**
     * 回滚当前事务
     */
    void rollbackTrans();

    /**
     * 获取实体管理器
     *
     * @return 实体管理器
     */
    EntityManager getEntityManager();

    /**
     * 设置实体管理器
     *
     * @param entityManager 实体管理器
     */
    void setEntityManager(EntityManager entityManager);

    /**
     * 按实体类主键获取指定实体类的持久化对象
     *
     * @param clazz 实体类类型
     * @param id    实体类Id
     * @return 实体类持久化对象
     */
    <T> T get(Class<T> clazz, Object id);
}
