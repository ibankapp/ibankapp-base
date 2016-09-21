package org.ibankapp.base.persistence;

import javax.annotation.Resource;

import javax.persistence.EntityManager;

/**
 * jpa持久化数据操作实现类
 *
 *
 * @version        1.0.0, 16/09/21
 * @author         codelder
 */
public class JpaDaoImpl implements IJpaDao {

    /** jpa 实体管理器 */
    @Resource
    private EntityManager entityManager;

    @Override
    public void persist(Model model) {
        entityManager.persist(model);
    }

    @Override
    public Object get(Class clazz, Object id) {
        return entityManager.find(clazz, id);
    }
}
