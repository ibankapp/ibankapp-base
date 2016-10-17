/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

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
import static org.junit.Assert.assertNull;

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
        jpaDao.query("delete TestModel").executeUpdate();
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
        List<TestModel> models = (List<TestModel>) jpaDao.query("from TestModel").getResultList();
        assertEquals(1,models.size());


    }

    @Test
    public void testPersistWithId(){
        Model model = new TestModel();

        jpaDao.beginTrans();
        model.setId("123");
        jpaDao.persist(model);

        Model testModel = jpaDao.get(TestModel.class,"123");
        assertNotNull(testModel);

        jpaDao.commitTrans();

    }

    @Test
    public void testPersistWithEmptyId(){
        TestModel model = new TestModel();

        jpaDao.beginTrans();

        model.setId("   ");
        jpaDao.persist(model);

        Model testModel=jpaDao.get(TestModel.class,model.getId());
        assertEquals(testModel.getId(),model.getId());

        jpaDao.commitTrans();

        @SuppressWarnings("unchecked")
        List<TestModel> models = (List<TestModel>) jpaDao.query("from TestModel").getResultList();
        assertEquals(1,models.size());
    }

    @Test
    public void testMerge(){
        TestModel model = new TestModel();
        model.setId("123");
        model.setName("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();

        jpaDao.beginTrans();
        TestModel model1 = new TestModel();
        model1.setId("123");
        model1.setName("test2");
        jpaDao.merge(model1);
        jpaDao.commitTrans();
        assertEquals("test2",jpaDao.get(TestModel.class,"123").getName());
    }

    @Test
    public void testMergeWithNullId(){
        merge(null);
    }

    private void merge(String id){
        TestModel model = new TestModel();
        model.setId(id);
        model.setName("test1");
        jpaDao.beginTrans();
        jpaDao.merge(model);
        jpaDao.commitTrans();
    }

    @Test
    public void testMergeWithEmptyId(){
        merge(" ");
    }

    @Test
    public void testRemove(){
        TestModel model = new TestModel();
        model.setName("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        TestModel testModel=jpaDao.get(TestModel.class,model.getId());
        assertEquals("test1",testModel.getName());
        jpaDao.commitTrans();

        jpaDao.beginTrans();
        testModel=jpaDao.get(TestModel.class,model.getId());
        jpaDao.remove(testModel);
        testModel=jpaDao.get(TestModel.class,model.getId());
        assertNull(testModel);
        jpaDao.commitTrans();

    }

    @Test
    public void testRollBack() {

        TestModel model = new TestModel();

        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.rollbackTrans();

        @SuppressWarnings("unchecked")
        List<TestModel> models = (List<TestModel>) jpaDao.query("from TestModel").getResultList();
        assertEquals(0,models.size());

    }


    @Test
    public void testCreateQueryWithNullJpql() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("jpql语句不能为空");

        jpaDao.query(null);
    }

    @Test
    public void testCreateQueryWithEmptyJpql() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("jpql语句不能为空");
        jpaDao.query(" ");
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
