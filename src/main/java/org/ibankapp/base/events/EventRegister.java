/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.events;

import java.util.*;

public class EventRegister {

    private Map<Class, Vector<EventConsumer>> listeners = new HashMap<>();

    public synchronized void addListener(Class clazz, EventConsumer consumer) {
        Vector<EventConsumer> consumers = listeners.get(clazz);
        if (consumers == null) {
            consumers = new Vector<>();
        }
        consumers.add(consumer);
        listeners.put(clazz, consumers);
    }

    public synchronized void removeListener(Class clazz, EventConsumer consumer) {
        List<EventConsumer> consumers = listeners.get(clazz);

        if (consumers != null && consumers.size() != 0) {
            consumers.remove(consumer);
        }
    }

    public synchronized void removeAllListeners() {
        listeners = new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    public void fireEvent(Event event) {

        Vector<EventConsumer> currentConsumers;

        synchronized (this) {
            currentConsumers = (Vector<EventConsumer>) listeners.get(event.getClass()).clone();
        }

        for (int i = 0; i < currentConsumers.size(); i++) {
            EventConsumer consumer = currentConsumers.elementAt(i);
            consumer.onEvent(event);
        }
    }
}
