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

import javax.annotation.Resource;


class Producer{

    @Resource
    private EventRegister register;

    void doSomething(){

        DemoEvent event = new DemoEvent(this);
        DemoEvent1 event1 = new DemoEvent1(this);

        event.setMessage("send a message");
        event1.setMessage("send second message");

//        register.fireEvent(event);
        register.fireEvent(event1);
    }
}
