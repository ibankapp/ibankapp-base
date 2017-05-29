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

class DemoEvent extends Event {

    private String message;

    DemoEvent(Object source) {
        super(source);
    }

    String getMessage() {
        return message;
    }

    void setMessage(String message) {
        this.message = message;
    }
}
