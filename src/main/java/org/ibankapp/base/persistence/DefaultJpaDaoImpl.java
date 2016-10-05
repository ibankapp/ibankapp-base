package org.ibankapp.base.persistence;

import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.validation.BeanValidator;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * jpa持久化数据操作实现类
 *
 * @author codelder
 * @version 1.0.0, 16/09/21
 */
public class DefaultJpaDaoImpl implements IJpaDao {

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
    public void merge(Model model) {
        BeanValidator.validate(model);
        entityManager.merge(model);
    }

    @Override
    public void persist(Model model) {
        BeanValidator.validate(model);
        entityManager.persist(model);
    }

    @Override
    public Query query(String jpql) {
        if (jpql == null || jpql.trim().length() == 0) {
            throw new BaseException("E-BASE-000004");
        }
        return entityManager.createQuery(jpql);
    }

    @Override
    public void remove(Model model) {
        entityManager.remove(model);
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
    public <T> T get(Class<T> clazz, Object id) {
        return entityManager.find(clazz, id);
    }
}
