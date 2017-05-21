/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.observer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class EventRegister {

    private Map<Class, Vector<EventConsumer>> listeners = new HashMap<>();

    public synchronized <T extends Event> void addListener(Class<T> clazz, EventConsumer consumer) {
        Vector<EventConsumer> consumers = listeners.get(clazz);
        if (consumers == null) {
            consumers = new Vector<>();
        }
        consumers.add(consumer);
        listeners.put(clazz, consumers);
    }

    public synchronized <T extends Event> void removeListener(Class<T> clazz, EventConsumer consumer) {
        List<EventConsumer> consumers = listeners.get(clazz);

        if (consumers != null && consumers.size() != 0) {
            consumers.remove(consumer);
        }
    }

    public synchronized void removeAllListeners() {
        listeners = new HashMap<>();
    }

    public synchronized <T extends Event> void removeAllListeners(Class<T> clazz) {
        listeners.remove(clazz);
    }

    @SuppressWarnings("unchecked")
    public void fireEvent(Event event) {

        Vector<EventConsumer> currentConsumers;

        synchronized (this) {
            currentConsumers = listeners.get(event.getClass());
        }

        if (currentConsumers != null) {
            for (int i = 0; i < currentConsumers.size(); i++) {
                EventConsumer consumer = currentConsumers.elementAt(i);
                consumer.onEvent(event);
            }
        }
    }

    public Map<Class, Vector<EventConsumer>> getListeners() {
        return listeners;
    }
}
