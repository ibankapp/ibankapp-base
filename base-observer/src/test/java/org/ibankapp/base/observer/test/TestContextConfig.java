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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TestContextConfig {

    @Bean
    EventRegister register(Consumer consumer) {
        EventRegister register = new EventRegister();
        register.addListener(DemoEvent.class, consumer);
        register.addListener(DemoEvent.class, secondConsumer());
        register.addListener(DemoEvent1.class, consumer());
        return register;
    }

    @Bean
    Producer producer() {
        return new Producer();
    }

    @Bean
    Consumer consumer() {
        return new Consumer();
    }

    @Bean
    SecondConsumer secondConsumer() {
        return new SecondConsumer();
    }

}
