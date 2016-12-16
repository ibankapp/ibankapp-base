/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.springdata.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfig.class})
public class BaseJpaRespositoryTest {

    @Resource
    private TestModelRepository repository;

    @Test
    @Transactional
    public void testSave(){

        TestModel testModel = new TestModel();
        testModel.setId("aaa");

        repository.save(testModel);
        List<TestModel> models = repository.findAll();
        Assert.assertEquals(1,models.size());
        Assert.assertEquals("aaa",models.get(0).getId());
    }

    @Test
    @Transactional
    public void testSaveNoId(){

        TestModel testModel = new TestModel();

        repository.save(testModel);
        List<TestModel> models = repository.findAll();
        Assert.assertEquals(1,models.size());
    }
}