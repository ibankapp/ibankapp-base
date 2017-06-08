/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.domain.test;

import org.ibankapp.base.persistence.domain.EntityInformation;
import org.ibankapp.base.persistence.validation.test.TestContextConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfig.class})
public class EntityInformationTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void testNewEntityInformation() {

        EntityInformation<CompositedModel> entityInformation = new EntityInformation<CompositedModel>(CompositedModel.class, em
                .getMetamodel());

        Assert.assertEquals("CompositedModel", entityInformation.getEntityName());

        for (String name : entityInformation.getIdAttributeNames()) {
            Assert.assertTrue(name.equals("firstName") || name.equals("lastName"));
        }
    }
}
