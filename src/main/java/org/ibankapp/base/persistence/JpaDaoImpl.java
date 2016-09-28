package org.ibankapp.base.persistence;

import javax.annotation.Resource;

import javax.persistence.EntityManager;
import java.util.List;

/**
 * jpa持久化数据操作实现类
 *
 * @author codelder
 * @version 1.0.0, 16/09/21
 */
@SuppressWarnings("unused")
public class JpaDaoImpl implements IJpaDao {

    /**
     * jpa 实体管理器
     */
    @Resource
    private EntityManager entityManager;

    @Override
    public void beginTrans() {
        entityManager.getTransaction().begin();
    }

    @Override
    public void commitTrans() {
        entityManager.getTransaction().commit();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List createQuery(String jpql) {
        return entityManager.createQuery(jpql).getResultList();
    }

    @Override
    public void persist(Model model) {
        entityManager.persist(model);
    }

    @Override
    public void rollbackTrans() {
        entityManager.getTransaction().rollback();
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @SuppressWarnings("unchecked")
    public Object get(Class clazz, Object id) {
        return entityManager.find(clazz, id);
    }
}
