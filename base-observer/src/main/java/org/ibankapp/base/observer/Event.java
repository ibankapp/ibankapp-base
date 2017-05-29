/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.observer;

import java.util.EventObject;

/**
 * ibankapp事件积累
 */
public class Event extends EventObject {

    private final long timestamp;

    /**
     * 构造函数
     *
     * @param source 触发改事件的原始对象
     * @throws IllegalArgumentException 如果source为null则抛出该异常
     */
    public Event(Object source) {
        super(source);
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 获取事件发生的事件
     *
     * @return 事件发生的时间
     */
    public long getTimestamp() {
        return timestamp;
    }
}