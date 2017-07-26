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

/**
 * 测试用spring配置.
 *
 * @author codelder@ibankapp.org
 * @since 1.0.0.0
 */
@Configuration
class TestContextConfig {

  /**
   * 初始化事件注册器Bean.
   *
   * @param consumer 事件消费者
   * @return 事件注册器
   */
  @Bean
  EventRegister register(TestConsumer consumer) {
    EventRegister register = new EventRegister();
    register.addListener(TestEvent.class, consumer);
    register.addListener(TestEvent.class, secondConsumer());
    register.addListener(TestEvent1.class, consumer());
    return register;
  }

  /**
   * 初始化事件生产者.
   *
   * @return 事件生产者
   */
  @Bean
  TestProducer producer() {
    return new TestProducer();
  }

  /**
   * 初始化事件消费者.
   *
   * @return 事件消费者
   */
  @Bean
  TestConsumer consumer() {
    return new TestConsumer();
  }

  /**
   * 初始化第二个事件消费者.
   *
   * @return 事件消费者
   */
  @Bean
  TestSecondConsumer secondConsumer() {
    return new TestSecondConsumer();
  }

}
