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

import javax.annotation.Resource;


public class Producer{

    @Resource
    private EventRegister register;

    void fireEvent(){

        DemoEvent event = new DemoEvent(this);

        event.setMessage("send a message");

        register.fireEvent(event);
    }
}
