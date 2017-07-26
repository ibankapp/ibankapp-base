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

/**
 * 事件注册器
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class EventRegister {

  private Map<Class, Vector<EventConsumer>> listeners = new HashMap<Class, Vector<EventConsumer>>();

  /**
   * 添加事件监听器.
   *
   * @param clazz 监听的事件类
   * @param consumer 事件消费者
   * @param <T> 继承自Event
   */
  public synchronized <T extends Event> void addListener(Class<T> clazz, EventConsumer consumer) {
    Vector<EventConsumer> consumers = listeners.get(clazz);
    if (consumers == null) {
      consumers = new Vector<EventConsumer>();
    }
    consumers.add(consumer);
    listeners.put(clazz, consumers);
  }

  /**
   * 移除指定事件、指定事件消费者的监听.
   *
   * @param clazz 监听的事件类
   * @param consumer 事件消费者
   * @param <T> 继承自Event
   */
  public synchronized <T extends Event> void removeListener(Class<T> clazz,
      EventConsumer consumer) {
    List<EventConsumer> consumers = listeners.get(clazz);

    if (consumers != null) {
      consumers.remove(consumer);
    }
  }

  /**
   * 移除所有的事件监听.
   */
  public synchronized void removeAllListeners() {
    listeners = new HashMap<Class, Vector<EventConsumer>>();
  }

  /**
   * 移除指定事件所有事件消费者的监听.
   *
   * @param clazz 监听的事件类
   * @param <T> 继承自Event
   */
  public synchronized <T extends Event> void removeAllListeners(Class<T> clazz) {
    listeners.remove(clazz);
  }

  /**
   * 触发指定事件.
   *
   * @param event 触发的事件
   */
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

  /**
   * 获取所有监听器.
   *
   * @return 所有监听器
   */
  public Map<Class, Vector<EventConsumer>> getListeners() {
    return listeners;
  }
}
