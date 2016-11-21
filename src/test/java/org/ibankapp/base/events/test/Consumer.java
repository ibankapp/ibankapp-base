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

public class Consumer implements EventConsumer {

    @Override
    public void onEvent(Event event) {

        if (event instanceof DemoEvent) {
            System.out.println(((DemoEvent) event).getMessage());
            System.out.println(event.getTimestamp());
        }
    }
}
