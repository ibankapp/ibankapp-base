/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.observer.test;

import org.ibankapp.base.observer.EventConsumer;
import org.ibankapp.base.observer.EventRegister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.Vector;

/**
 * 测试事件注册器
 */
public class EventRegisterTest {

    /**
     * 事件注册器
     */
    private EventRegister register;

    /**
     * 事件消费者
     */
    private Consumer consumer = new Consumer();

    /**
     * 另一个事件消费者
     */
    private SecondConsumer secondConsumer = new SecondConsumer();

    /**
     * 将Demo事件注册到事件注册器中并让事件消费者进行监听
     */
    @Before
    public void initRegister() {
        register = new EventRegister();
        register.addListener(DemoEvent.class, consumer);
        register.addListener(DemoEvent.class, secondConsumer);
        register.addListener(DemoEvent1.class, consumer);
    }

    /**
     * 测试添加事件监听
     */
    @Test
    public void testAddListener() {

        Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

        Vector<EventConsumer> consumers = new Vector<EventConsumer>();
        consumers.add(consumer);
        consumers.add(secondConsumer);

        Assert.assertEquals(consumers, listeners.get(DemoEvent.class));
    }

    /**
     * 测试移除事件监听
     */
    @Test
    public void testRemoveListener() {

        register.removeListener(DemoEvent.class, consumer);

        Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

        Vector<EventConsumer> consumers = new Vector<EventConsumer>();
        consumers.add(secondConsumer);

        Assert.assertEquals(consumers, listeners.get(DemoEvent.class));

    }

    /**
     * 测试从空的事件监听列表里移除事件监听器
     */
    @Test
    public void testRemoveFromEmptyListeners() {
        register.removeAllListeners(DemoEvent.class);
        register.removeAllListeners(DemoEvent.class);
        register.removeListener(DemoEvent.class, consumer);
    }

    /**
     * 测试从事件监听列表里移除所有DemoEvent的事件监听
     */
    @Test
    public void testRemoveAllDemoEventListener() {

        register.removeAllListeners(DemoEvent.class);

        Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

        Vector<EventConsumer> consumers = new Vector<EventConsumer>();
        consumers.add(consumer);

        Assert.assertNull(listeners.get(DemoEvent.class));
        Assert.assertEquals(consumers, listeners.get(DemoEvent1.class));

    }

    /**
     * 测试移出所有的事件监听器
     */
    @Test
    public void testRemoveAllListener() {

        register.removeAllListeners();

        Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

        Assert.assertEquals(0, listeners.size());
    }
}

