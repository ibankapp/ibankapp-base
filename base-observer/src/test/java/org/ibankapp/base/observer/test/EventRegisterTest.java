/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.observer.test;

import java.util.Map;
import java.util.Vector;
import org.ibankapp.base.observer.EventConsumer;
import org.ibankapp.base.observer.EventRegister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 测试事件注册器
 *
 * @author codelder@ibankapp.org
 * @since 1.0.0.0
 */
public class EventRegisterTest {

  /**
   * 事件注册器.
   */
  private EventRegister register;

  /**
   * 事件消费者.
   */
  private final TestConsumer consumer = new TestConsumer();

  /**
   * 另一个事件消费者.
   */
  private final TestSecondConsumer secondConsumer = new TestSecondConsumer();

  /**
   * 将Demo事件注册到事件注册器中并让事件消费者进行监听.
   */
  @Before
  public void initRegister() {
    register = new EventRegister();
    register.addListener(TestEvent.class, consumer);
    register.addListener(TestEvent.class, secondConsumer);
    register.addListener(TestEvent1.class, consumer);
  }

  /**
   * 测试添加事件监听.
   */
  @Test
  public void testAddListener() {

    Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

    Vector<EventConsumer> consumers = new Vector<EventConsumer>();
    consumers.add(consumer);
    consumers.add(secondConsumer);

    Assert.assertEquals(consumers, listeners.get(TestEvent.class));
  }

  /**
   * 测试移除事件监听.
   */
  @Test
  public void testRemoveListener() {

    register.removeListener(TestEvent.class, consumer);

    Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

    Vector<EventConsumer> consumers = new Vector<EventConsumer>();
    consumers.add(secondConsumer);

    Assert.assertEquals(consumers, listeners.get(TestEvent.class));

  }

  /**
   * 测试从空的事件监听列表里移除事件监听器.
   */
  @Test
  public void testRemoveFromEmptyListeners() {
    register.removeAllListeners(TestEvent.class);
    register.removeAllListeners(TestEvent.class);
    register.removeListener(TestEvent.class, consumer);
  }

  /**
   * 测试从事件监听列表里移除所有DemoEvent的事件监听.
   */
  @Test
  public void testRemoveAllDemoEventListener() {

    register.removeAllListeners(TestEvent.class);

    Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

    Vector<EventConsumer> consumers = new Vector<EventConsumer>();
    consumers.add(consumer);

    Assert.assertNull(listeners.get(TestEvent.class));
    Assert.assertEquals(consumers, listeners.get(TestEvent1.class));

  }

  /**
   * 测试移出所有的事件监听器.
   */
  @Test
  public void testRemoveAllListener() {

    register.removeAllListeners();

    Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

    Assert.assertEquals(0, listeners.size());
  }
}

