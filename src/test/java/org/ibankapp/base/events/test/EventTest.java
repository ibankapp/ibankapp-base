/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.events.test;

import org.ibankapp.base.events.EventRegister;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EventTest {

    private Producer producer = new Producer();

    private Consumer consumer = new Consumer();

    private SecondConsumer secondConsumer = new SecondConsumer();


    @Before
    public void init() {
        EventRegister register = new EventRegister();
        producer.setRegister(register);
    }

    @After
    public void remove(){
        producer.removeAllListeners();
    }

    @Test
    public void testEvent() {
        producer.addListener(DemoEvent.class, consumer);
        producer.fireEvent();
    }

    @Test
    public void testSecondConsumerEvent() {
        producer.addListener(DemoEvent.class, consumer);
        producer.addListener(DemoEvent.class, secondConsumer);
        producer.fireEvent();
    }

    @Test
    public void testRemoveListener(){
        producer.removeListener(DemoEvent.class,consumer);
        producer.addListener(DemoEvent.class,consumer);
        producer.removeListener(DemoEvent.class,consumer);
        producer.removeListener(DemoEvent.class,consumer);
        producer.fireEvent();
    }

}
