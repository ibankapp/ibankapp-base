/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.observer.test;

import org.ibankapp.base.observer.Event;
import org.ibankapp.base.observer.EventConsumer;

/**
 * 测试用第二个事件消费者
 *
 * @author codelder@ibankapp.org
 * @since 1.0.0.0
 */
class TestSecondConsumer implements EventConsumer {

    /**
     * 接收DemoEvent事件并进行相应处理
     *
     * @param event 发生的事件
     */
    @Override
    public void onEvent(Event event) {

        if (event instanceof TestEvent) {
            System.out.println(event.getTimestamp());
        }
    }
}
