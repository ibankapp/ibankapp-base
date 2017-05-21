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
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestContextConfig.class})
public class FireEventTest {

    @Resource
    private Producer producer;

    @Resource
    private EventRegister register;

    @Resource
    private Consumer consumer;

    @Test
    public void testEvent() {
        producer.doSomething();
        Assert.assertEquals(2,consumer.getMessages().size());
        Set<String> messages = new HashSet<>();
        messages.add("send a message");
        messages.add("send second message");

        Assert.assertEquals(messages,consumer.getMessages());
    }

    @Test
    public void FireWhenNoConsumer() {
        register.removeAllListeners();
        producer.doSomething();
    }

}
