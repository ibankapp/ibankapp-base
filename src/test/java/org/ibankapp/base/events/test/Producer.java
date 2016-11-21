/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.events.test;

import org.ibankapp.base.events.Event;
import org.ibankapp.base.events.EventConsumer;
import org.ibankapp.base.events.EventRegister;

import java.util.Map;

public class Producer {

    private EventRegister register;

    public void fireEvent(){

        DemoEvent event = new DemoEvent(this);

        event.setMessage("send a message");

        register.fireEvent(event);

    }

    public <E extends Event,C extends EventConsumer> void addListener(Class<E> clazz, C consumer){
        register.addListener(clazz,consumer);
    }

    public <E extends Event,C extends EventConsumer> void removeListener(Class<E> clazz, C consumer){
        register.removeListener(clazz,consumer);
    }

    public void removeAllListeners(){
        register.removeAllListeners();
    }

    public EventRegister getRegister() {
        return register;
    }

    public void setRegister(EventRegister register) {
        this.register = register;
    }
}
