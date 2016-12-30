/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.validation.test;

import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.persistence.repository.JpaRepository;
import org.ibankapp.base.persistence.validation.validator.UniqueValidator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.InheritanceType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfig.class})
public class UniqueValidatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Resource
    private JpaRepository repository;

    @After
    public void removeAll() {
        repository.deleteAll(TestModel.class);
        repository.deleteAll(TestModelWithNoExistUniqueColumn.class);
        repository.deleteAll(TestModelWithInheritedUnique.class);
        repository.deleteAll(TestModelWithTwoColumnUnique.class);
        repository.deleteAll(TestModelWithInheritedUniques.class);
        repository.deleteAll(TestModelWithEumAndUnique.class);
    }

    @Test
    @Transactional
    public void testUnique() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,姓名重复");

        TestModel model = new TestModel();
        model.setId("aaaa");
        model.setName("test1");
        repository.persist(model);

        model = new TestModel();
        model.setId("bbbb");
        model.setName("test1");
        repository.persist(model);
    }

    @Test
    @Transactional
    public void testUniqueWithNoExistColumn() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("Unable to locate");

        TestModelWithNoExistUniqueColumn model = new TestModelWithNoExistUniqueColumn();
        model.setId("aaa");
        model.setName("test1");
        repository.persist(model);

        model = new TestModelWithNoExistUniqueColumn();
        model.setId("bbb");
        model.setName("test1");
        repository.persist(model);
    }

    @Test
    @Transactional
    public void testUniqueInherited() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,姓名重复");

        TestModelWithInheritedUnique model = new TestModelWithInheritedUnique();
        model.setId("aaa");
        model.setName("test1");
        repository.persist(model);

        model = new TestModelWithInheritedUnique();
        model.setId("bbb");
        model.setName("test1");
        repository.persist(model);
    }

    @Test
    @Transactional
    public void testUniqueTwoColumn() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,姓名及类型重复");

        TestModelWithTwoColumnUnique model = new TestModelWithTwoColumnUnique();
        model.setId("aaa");
        model.setType(1);
        model.setName("test2");
        repository.persist(model);

        model = new TestModelWithTwoColumnUnique();
        model.setId("bbb");
        model.setType(1);
        model.setName("test2");
        repository.persist(model);
    }

    @Test
    @Transactional
    public void testUniqueTwoColumnNoError() {

        TestModelWithTwoColumnUnique model = new TestModelWithTwoColumnUnique();
        model.setId("aaa");
        model.setType(1);
        model.setName("test2");
        repository.persist(model);

        model = new TestModelWithTwoColumnUnique();
        model.setId("bbb");
        model.setType(2);
        model.setName("test2");
        repository.persist(model);

    }

    @Test
    @Transactional
    public void testUniquesInherited() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,类型重复");

        TestModelWithInheritedUniques model = new TestModelWithInheritedUniques();
        model.setId("aaa");
        model.setType("test1");
        repository.persist(model);

        model = new TestModelWithInheritedUniques();
        model.setId("bbb");
        model.setType("test1");
        repository.persist(model);
    }

    @Test
    @Transactional
    public void testNewUniqueValidator() {
        new UniqueValidator();
    }

    @Test
    @Transactional
    public void testUniqueAndNum() {
        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,ENUM重复");

        TestModelWithEumAndUnique model = new TestModelWithEumAndUnique();
        model.setStatus(InheritanceType.SINGLE_TABLE);
        model.setId("aaa");
        repository.persist(model);

        model = new TestModelWithEumAndUnique();
        model.setId("bbb");
        model.setStatus(InheritanceType.SINGLE_TABLE);
        repository.persist(model);
    }

    @Test
    @Transactional
    public void testUpdate() {

        TestModel model = new TestModel();
        model.setId("1");
        model.setName("test1");
        repository.persist(model);

        TestModel model1 = repository.findOne(TestModel.class,"1");
        Assert.assertEquals("test1",model1.getName());

        model.setName("test2");
        repository.persist(model);
        model1 = repository.findOne(TestModel.class,"1");
        Assert.assertEquals("test2",model1.getName());

        model.setName("test3");
        repository.merge(model);
        model1 = repository.findOne(TestModel.class,"1");
        Assert.assertEquals("test3",model1.getName());

        TestModel model2 = new TestModel();
        model2.setId("1");
        model2.setName("test4");
        repository.merge(model2);
        model1 = repository.findOne(TestModel.class,"1");
        Assert.assertEquals("test4",model1.getName());
    }
}
