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

import java.util.HashSet;
import java.util.Set;

/**
 * 测试用事件消费者
 *
 * @author codelder@ibankapp.org
 * @since 1.0.0.0
 */
class TestConsumer implements EventConsumer {

    private Set<String> messages = new HashSet<String>();

    /**
     * 接收DemoEvent及DemoEvent1并进行相应处理
     *
     * @param event 发生的事件
     */
    @Override
    public void onEvent(Event event) {

        if (event instanceof TestEvent) {
            messages.add(((TestEvent) event).getMessage());
        } else if (event instanceof TestEvent1) {
            messages.add(((TestEvent1) event).getMessage());
        }
    }

    /**
     * 获取 事件信息集合
     *
     * @return 事件信息集合
     */
    Set<String> getMessages() {
        return messages;
    }

}
