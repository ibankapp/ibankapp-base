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

public class EventRegisterTest {

    private EventRegister register;


    private Consumer consumer = new Consumer();

    private SecondConsumer secondConsumer = new SecondConsumer();

    @Before
    public void initRegister() {
        register = new EventRegister();
        register.addListener(DemoEvent.class, consumer);
        register.addListener(DemoEvent.class, secondConsumer);
        register.addListener(DemoEvent1.class, consumer);
    }

    @Test
    public void testAddListener() {

        Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

        Vector<EventConsumer> consumers = new Vector<>();
        consumers.add(consumer);
        consumers.add(secondConsumer);

        Assert.assertEquals(consumers, listeners.get(DemoEvent.class));
    }

    @Test
    public void testRemoveListener() {

        register.removeListener(DemoEvent.class, consumer);

        Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

        Vector<EventConsumer> consumers = new Vector<>();
        consumers.add(secondConsumer);

        Assert.assertEquals(consumers, listeners.get(DemoEvent.class));

    }

    @Test
    public void testRemoveFromEmptyListeners(){
        register.removeAllListeners(DemoEvent.class);
        register.removeAllListeners(DemoEvent.class);
        register.removeListener(DemoEvent.class,consumer);
    }

    @Test
    public void testRemoveAllDemoEventListener() {

        register.removeAllListeners(DemoEvent.class);

        Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

        Vector<EventConsumer> consumers = new Vector<>();
        consumers.add(consumer);

        Assert.assertNull(listeners.get(DemoEvent.class));
        Assert.assertEquals(consumers, listeners.get(DemoEvent1.class));

    }

    @Test
    public void testRemoveAllListener() {

        register.removeAllListeners();

        Map<Class, Vector<EventConsumer>> listeners = register.getListeners();

        Assert.assertEquals(0,listeners.size());
    }
}

