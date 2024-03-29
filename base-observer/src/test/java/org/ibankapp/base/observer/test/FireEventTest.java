/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.observer.test;

import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import org.ibankapp.base.observer.EventRegister;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试触发事件
 *
 * @author codelder@ibankapp.org
 * @since 1.0.0.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfig.class})
public class FireEventTest {

  /**
   * 事件触发者.
   */
  @Resource
  private TestProducer producer;

  /**
   * 事件监听器.
   */
  @Resource
  private EventRegister register;

  /**
   * 事件消费者.
   */
  @Resource
  private TestConsumer consumer;

  /**
   * 测试事件触发.
   */
  @Test
  public void testEvent() {
    producer.doSomething();
    Assert.assertEquals(2, consumer.getMessages().size());
    Set<String> messages = new HashSet<String>();
    messages.add("send a message");
    messages.add("send second message");

    Assert.assertEquals(messages, consumer.getMessages());
  }

  /**
   * 测试没有监听者的事件触发.
   */
  @Test
  public void testFireWhenNoConsumer() {
    register.removeAllListeners();
    producer.doSomething();
  }

}
