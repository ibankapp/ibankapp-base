/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.test;

import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.persistence.DefaultJpaDaoImpl;
import org.ibankapp.base.persistence.test.model.TestModel;
import org.ibankapp.base.validation.test.model.*;
import org.ibankapp.base.validation.validators.UniqueValidator;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.InheritanceType;
import javax.persistence.Persistence;

public class UniqueValidatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private final DefaultJpaDaoImpl jpaDao = new DefaultJpaDaoImpl();

    public UniqueValidatorTest() {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("ibankapp");
        EntityManager entityManager = factory.createEntityManager();

        jpaDao.setEntityManager(entityManager);

    }

    @After
    public void removeAll(){
        if(!jpaDao.getEntityManager().getTransaction().isActive())
            jpaDao.beginTrans();
        jpaDao.getEntityManager().clear();
        jpaDao.query("delete TestModel").executeUpdate();
        jpaDao.commitTrans();
    }

    @Test
    public void testUnique(){

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,姓名重复");

        TestModel model = new TestModel();
        model.setName("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();

        model = new TestModel();
        model.setName("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();
    }

    @Test
    public void testUniqueWithNoExistColumn(){

        thrown.expect(BaseException.class);
        thrown.expectMessage("Unable to locate");

        TestModelWithNoExistUniqueColumn model = new TestModelWithNoExistUniqueColumn();
        model.setName("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();

        model = new TestModelWithNoExistUniqueColumn();
        model.setName("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();
    }

    @Test
    public void testUniqueInherited(){

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,姓名重复");

        TestModelWithInheritedUnique model = new TestModelWithInheritedUnique();
        model.setName("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();

        model = new TestModelWithInheritedUnique();
        model.setName("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();
    }

    @Test
    public void testUniques(){

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,类型重复");

        TestModelWithUniques model = new TestModelWithUniques();
        model.setType("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();

        model = new TestModelWithUniques();
        model.setType("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();
    }

    @Test
    public void testUniqueTwoColumn(){

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,姓名及类型重复");

        TestModelWithTwoColumnUnique model = new TestModelWithTwoColumnUnique();
        model.setType(1);
        model.setName("test2");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();

        model = new TestModelWithTwoColumnUnique();
        model.setType(1);
        model.setName("test2");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();
    }

    @Test
    public void testUniqueTwoColumnNoError(){

        TestModelWithTwoColumnUnique model = new TestModelWithTwoColumnUnique();
        model.setType(1);
        model.setName("test2");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();

        model = new TestModelWithTwoColumnUnique();
        model.setType(2);
        model.setName("test2");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();
    }

    @Test
    public void testUniquesInherited(){

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,类型重复");

        TestModelWithInheritedUniques model = new TestModelWithInheritedUniques();
        model.setType("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();

        model = new TestModelWithInheritedUniques();
        model.setType("test1");
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();
    }

    @Test
    public void testNewUniqueValidator(){
        new UniqueValidator();
    }

    @Test
    public void testUniqueAndNum(){
        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,ENUM重复");

        TestModelWithEumAndUnique model = new TestModelWithEumAndUnique();
        model.setStatus(InheritanceType.SINGLE_TABLE);
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();

        model = new TestModelWithEumAndUnique();
        model.setStatus(InheritanceType.SINGLE_TABLE);
        jpaDao.beginTrans();
        jpaDao.persist(model);
        jpaDao.commitTrans();
    }
}
