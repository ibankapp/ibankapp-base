package org.ibankapp.base.persistence;

/**
 * jpa持久化数据操作接口
 *
 *
 * @version        1.0.0, 16/09/21
 * @author         codelder
 */
public interface IJpaDao {

    /**
     * 保存持久化数据
     *
     *
     * @param model 实体类
     */
    void persist(Model model);

    /**
     * 按实体类主键获取指定实体类的持久化对象
     *
     *
     * @param clazz 实体类类型
     * @param id 实体类Id
     *
     * @return 实体类持久化对象
     */
    Object get(Class clazz, Object id);
}
