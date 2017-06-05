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

/**
 * 测试用Demo事件
 *
 * @author codelder@ibankapp.org
 * @since 1.0.0.0
 */
class DemoEvent1 extends Event {

    /**
     * 事件信息
     */
    private String message;

    /**
     * 构造函数，继承父类构造函数
     *
     * @param source 触发事件的原始对象
     */
    DemoEvent1(Object source) {
        super(source);
    }

    /**
     * 获取 事件信息
     *
     * @return 事件信息
     */
    String getMessage() {
        return message;
    }

    /**
     * 设置 事件信息
     *
     * @param message 事件信息
     */
    void setMessage(String message) {
        this.message = message;
    }
}
