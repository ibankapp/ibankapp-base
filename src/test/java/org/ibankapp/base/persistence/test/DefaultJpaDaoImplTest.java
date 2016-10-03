package org.ibankapp.base.persistence.test;


import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.persistence.DefaultJpaDaoImpl;
import org.ibankapp.base.persistence.Model;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultJpaDaoImplTest {


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final DefaultJpaDaoImpl jpaDao = new DefaultJpaDaoImpl();

    public DefaultJpaDaoImplTest() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ibankapp");
        EntityManager entityManager = factory.createEntityManager();

        jpaDao.setEntityManager(entityManager);

    }

    @After
    public void removeAll(){
        jpaDao.beginTrans();
        jpaDao.getEntityManager().clear();
        jpaDao.createQuery("delete TestModel").executeUpdate();
        jpaDao.commitTrans();
    }

    @Test
    public void testPersist() {
        TestModel model = new TestModel();

        jpaDao.beginTrans();
        jpaDao.persist(model);

        Model testModel=jpaDao.get(TestModel.class,model.getId());
        assertEquals(testModel.getId(),model.getId());

        jpaDao.commitTrans();

        @SuppressWarnings("unchecked")
        List<TestModel> models = (List<TestModel>) jpaDao.createQuery("from TestModel").getResultList();
        assertEquals(1,models.size());

    }

    @Test
    public void testRollBack() {

        TestModel model = new TestModel();

        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.rollbackTrans();

        @SuppressWarnings("unchecked")
        List<TestModel> models = (List<TestModel>) jpaDao.createQuery("from TestModel").getResultList();
        assertEquals(0,models.size());

    }


    @Test
    public void testCreateQueryWithNullJpql() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("jpql语句不能为空");

        jpaDao.createQuery(null);
    }

    @Test
    public void testCreateQueryWithEmptyJpql() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("jpql语句不能为空");
        jpaDao.createQuery(" ");
    }

    @Test
    public void testGetEntityManager() {
        EntityManager manager = jpaDao.getEntityManager();
        assertNotNull(manager);
    }

    @Test
    public void testSetEntityManager() {
        jpaDao.setEntityManager(jpaDao.getEntityManager());
    }

}
