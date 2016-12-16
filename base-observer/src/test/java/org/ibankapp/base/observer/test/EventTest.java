/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.observer.test;

import org.ibankapp.base.observer.EventRegister;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfig.class})
public class EventTest {

    @Resource
    private Producer producer;

    @Resource
    private Consumer consumer;

    @Resource
    private EventRegister register;

    @Resource
    private SecondConsumer secondConsumer;


    @After
    public void remove() {
        register.removeAllListeners();
    }

    @Test
    public void testEvent() {
        register.addListener(DemoEvent.class, consumer);
        producer.fireEvent();
    }

    @Test
    public void testSecondConsumerEvent() {
        register.addListener(DemoEvent.class, consumer);
        register.addListener(DemoEvent.class, secondConsumer);
        producer.fireEvent();
    }

    @Test
    public void testRemoveListener() {
        register.removeListener(DemoEvent.class, consumer);
        register.addListener(DemoEvent.class, consumer);
        register.removeListener(DemoEvent.class, consumer);
        register.removeListener(DemoEvent.class, consumer);
        producer.fireEvent();
    }
}
