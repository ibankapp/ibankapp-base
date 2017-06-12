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

package org.ibankapp.base.persistence.domain.test;

import org.ibankapp.base.persistence.domain.Sort;
import org.ibankapp.base.persistence.repository.JpaRepository;
import org.ibankapp.base.persistence.validation.test.TestContextConfig;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfig.class})
public class FindSortTest {

    @Resource
    private JpaRepository repository;

    @Before
    public void init(){

        TestSortModel model = new TestSortModel();

        model.setId("0");
        model.setName("tom");
        model.setAge(10);

        repository.persist(model);

        model = new TestSortModel();

        model.setId("1");
        model.setName("tom");
        model.setAge(11);

        repository.persist(model);

        model = new TestSortModel();

        model.setId("2");
        model.setName("jack");
        model.setAge(10);

        repository.persist(model);

        model = new TestSortModel();

        model.setId("3");
        model.setName("jack");
        model.setAge(11);

        repository.persist(model);
    }

    @Test
    @Transactional
    public void testAscNameSort(){

        Sort sort = new Sort("name");

        List<TestSortModel> models = repository.findAll(TestSortModel.class,sort);

        Assert.assertEquals("jack",models.get(0).getName());
        Assert.assertEquals("jack",models.get(1).getName());
        Assert.assertEquals("tom",models.get(2).getName());
        Assert.assertEquals("tom",models.get(3).getName());

    }

    @Test
    @Transactional
    public void testAscAgeSort(){

        Sort sort = new Sort("age");

        List<TestSortModel> models = repository.findAll(TestSortModel.class,sort);

        Assert.assertEquals(10,models.get(0).getAge());
        Assert.assertEquals(10,models.get(1).getAge());
        Assert.assertEquals(11,models.get(2).getAge());
        Assert.assertEquals(11,models.get(3).getAge());
    }

    @Test
    @Transactional
    public void testDescNameSort(){
        Sort sort = new Sort(Sort.Direction.DESC,"name");

        List<TestSortModel> models = repository.findAll(TestSortModel.class,sort);

        Assert.assertEquals("tom",models.get(0).getName());
        Assert.assertEquals("tom",models.get(1).getName());
        Assert.assertEquals("jack",models.get(2).getName());
        Assert.assertEquals("jack",models.get(3).getName());
    }

    @Test
    @Transactional
    public void testAscAgeNameSort(){
        Sort sort = new Sort("age","name");

        List<TestSortModel> models = repository.findAll(TestSortModel.class,sort);

        Assert.assertEquals("2",models.get(0).getId());
        Assert.assertEquals("0",models.get(1).getId());
        Assert.assertEquals("3",models.get(2).getId());
        Assert.assertEquals("1",models.get(3).getId());
    }

    @Test
    @Transactional
    public void testAscNameAgeSort(){
        Sort sort = new Sort("name","age");

        List<TestSortModel> models = repository.findAll(TestSortModel.class,sort);

        Assert.assertEquals("2",models.get(0).getId());
        Assert.assertEquals("3",models.get(1).getId());
        Assert.assertEquals("0",models.get(2).getId());
        Assert.assertEquals("1",models.get(3).getId());
    }

    @Test
    @Transactional
    public void testAscNameDescAgeSort(){

        Sort.Order order1 = new Sort.Order("name");
        Sort.Order order2 = new Sort.Order(Sort.Direction.DESC,"age");

        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        orders.add(order1);
        orders.add(order2);

        Sort sort = new Sort(orders);

        List<TestSortModel> models = repository.findAll(TestSortModel.class,sort);

        Assert.assertEquals("3",models.get(0).getId());
        Assert.assertEquals("2",models.get(1).getId());
        Assert.assertEquals("1",models.get(2).getId());
        Assert.assertEquals("0",models.get(3).getId());
    }
}
