/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.domain.test;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.ibankapp.base.persistence.domain.EntityInformation;
import org.ibankapp.base.persistence.validation.test.TestContextConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * EntityInfomation 测试类.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfig.class})
public class EntityInformationTest {

  /**
   * 注入的Jpa EntityManager.
   */
  @PersistenceContext
  private EntityManager em;

  /**
   * 测试获取实体类的Id属性名列表,该实体类ID由多个字段组合而成.
   */
  @Test
  public void testMultiIdEntityInformation() {

    EntityInformation<TestCompositedModel> entityInformation =
        new EntityInformation<>(
            TestCompositedModel.class, em
            .getMetamodel());

    Assert.assertEquals("TestCompositedModel", entityInformation.getEntityName());

    Set<String> expectAttributNames = new HashSet<>();
    expectAttributNames.add("firstName");
    expectAttributNames.add("lastName");

    Set<String> actualAttributNames = new HashSet<>(
        (Collection<? extends String>) entityInformation
            .getIdAttributeNames());

    Assert.assertEquals(expectAttributNames, actualAttributNames);

  }

  /**
   * 测试获取实体类的ID属性列表，该实体类ID只由一个字段构成.
   */
  @Test
  public void testSingleIdEntityInfomation() {
    EntityInformation<TestSimpleModel> entityInformation = new EntityInformation<>(
        TestSimpleModel.class, em
        .getMetamodel());

    for (String name : entityInformation.getIdAttributeNames()) {
      Assert.assertEquals("name", name);
    }
  }
}
