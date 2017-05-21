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

class Consumer implements EventConsumer {

    private Set<String> messages = new HashSet<>();

    @Override
    public void onEvent(Event event) {

        if (event instanceof DemoEvent) {
            messages.add(((DemoEvent) event).getMessage());
        } else if (event instanceof DemoEvent1) {
            messages.add(((DemoEvent1) event).getMessage());
        }
    }

    Set<String> getMessages() {
        return messages;
    }

}
