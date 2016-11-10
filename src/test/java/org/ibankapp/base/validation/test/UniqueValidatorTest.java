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
import org.ibankapp.base.persistence.test.*;
import org.ibankapp.base.persistence.test.model.TestModel;
import org.ibankapp.base.validation.test.model.*;
import org.ibankapp.base.validation.validators.UniqueValidator;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.InheritanceType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfig.class})
public class UniqueValidatorTest {

    @Resource
    private TestModelRepository repository;

    @Resource
    private TestModelWithEumAndUniqueRepository testModelWithEumAndUniqueRepository;

    @Resource
    private TestModelWithNoExistUniqueColumnRepository testModelWithNoExistUniqueColumnRepository;

    @Resource
    private TestModelWithTwoColumnUniqueRepository testModelWithTwoColumnUniqueRepository;

    @Resource
    private TestModelWithUniqueRepository testModelWithUniqueRepository;

    @Resource
    private TestModelWithUniquesRepository testModelWithUniquesRepository;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @After
    public void removeAll(){
        repository.deleteAll();
        testModelWithEumAndUniqueRepository.deleteAll();
        testModelWithTwoColumnUniqueRepository.deleteAll();
        testModelWithNoExistUniqueColumnRepository.deleteAll();
        testModelWithUniquesRepository.deleteAll();
        testModelWithUniqueRepository.deleteAll();
    }

    @Test
    public void testUnique(){

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,姓名重复");

        TestModel model = new TestModel();
        model.setId("aaaaa");
        model.setName("test1");
        repository.save(model);

        model = new TestModel();
        model.setId("bbbbb");
        model.setName("test1");
        repository.save(model);
    }

    @Test
    public void testUniqueWithNoExistColumn(){

        thrown.expect(BaseException.class);
        thrown.expectMessage("Unable to locate");

        TestModelWithNoExistUniqueColumn model = new TestModelWithNoExistUniqueColumn();
        model.setId("aaa");
        model.setName("test1");
        testModelWithNoExistUniqueColumnRepository.save(model);

        model = new TestModelWithNoExistUniqueColumn();
        model.setId("bbb");
        model.setName("test1");
        testModelWithNoExistUniqueColumnRepository.save(model);
    }

    @Test
    public void testUniqueInherited(){

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,姓名重复");

        TestModelWithInheritedUnique model = new TestModelWithInheritedUnique();
        model.setId("aaa");
        model.setName("test1");
        testModelWithUniqueRepository.save(model);

        model = new TestModelWithInheritedUnique();
        model.setId("bbb");
        model.setName("test1");
        testModelWithUniqueRepository.save(model);
    }

    @Test
    public void testUniqueTwoColumn(){

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,姓名及类型重复");

        TestModelWithTwoColumnUnique model = new TestModelWithTwoColumnUnique();
        model.setId("aaa");
        model.setType(1);
        model.setName("test2");
        testModelWithTwoColumnUniqueRepository.save(model);

        model = new TestModelWithTwoColumnUnique();
        model.setId("bbb");
        model.setType(1);
        model.setName("test2");
        testModelWithTwoColumnUniqueRepository.save(model);
    }

    @Test
    public void testUniqueTwoColumnNoError(){

        TestModelWithTwoColumnUnique model = new TestModelWithTwoColumnUnique();
        model.setId("aaa");
        model.setType(1);
        model.setName("test2");
        testModelWithTwoColumnUniqueRepository.save(model);

        model = new TestModelWithTwoColumnUnique();
        model.setId("bbb");
        model.setType(2);
        model.setName("test2");
        testModelWithTwoColumnUniqueRepository.save(model);
    }

    @Test
    public void testUniquesInherited(){

        thrown.expect(BaseException.class);
        thrown.expectMessage("唯一约束校验失败,类型重复");

        TestModelWithInheritedUniques model = new TestModelWithInheritedUniques();
        model.setId("aaa");
        model.setType("test1");
        testModelWithUniquesRepository.save(model);

        model = new TestModelWithInheritedUniques();
        model.setId("bbb");
        model.setType("test1");
        testModelWithUniquesRepository.save(model);
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
        model.setId("aaa");
        testModelWithEumAndUniqueRepository.save(model);

        model = new TestModelWithEumAndUnique();
        model.setId("bbb");
        model.setStatus(InheritanceType.SINGLE_TABLE);
        testModelWithEumAndUniqueRepository.save(model);
    }
}
