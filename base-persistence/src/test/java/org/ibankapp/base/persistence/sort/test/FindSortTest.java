/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.sort.test;

import org.ibankapp.base.persistence.domain.Sort;
import org.ibankapp.base.persistence.repository.JpaRepository;
import org.ibankapp.base.persistence.validation.test.TestContextConfig;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfig.class})
public class FindSortTest {

    @Resource
    private JpaRepository repository;

    @Before
    public void init(){

        SortModel model = new SortModel();

        model.setId("0");
        model.setName("tom");
        model.setAge(10);

        repository.persist(model);

        model = new SortModel();

        model.setId("1");
        model.setName("jack");
        model.setAge(11);

        repository.persist(model);
    }

    @Test
    @Transactional
    public void testAscNameSort(){

        Sort sort = new Sort("name");

        List<SortModel> models = repository.findAll(SortModel.class,sort);

        Assert.assertEquals("jack",models.get(0).getName());
        Assert.assertEquals("tom",models.get(1).getName());

    }

    @Test
    @Transactional
    public void testAscAgeSort(){

        Sort sort = new Sort("age");

        List<SortModel> models = repository.findAll(SortModel.class,sort);

        Assert.assertEquals("tom",models.get(0).getName());
        Assert.assertEquals("jack",models.get(1).getName());

    }
}
