/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.exception;


import org.ibankapp.base.util.PropertyUtil;

public class BaseException extends RuntimeException {


    private static final long serialVersionUID = 8639994227897113279L;

    private String messageId;


    private String message;

    private final static String MESSAGE_FILE = "base_message.properties";

    static {
        PropertyUtil.load(MESSAGE_FILE);
    }


    public BaseException() {
    }

    public BaseException(String messageId) {
        this.messageId = messageId;
        this.message = PropertyUtil.getProperty(messageId);
    }


    public BaseException(String messageId, String param) {
        this.messageId = messageId;
        this.message = PropertyUtil.getProperty(messageId, param);
    }


    public BaseException(String messageId, String param1, String param2) {
        this.messageId = messageId;
        this.message = PropertyUtil.getProperty(messageId, param1, param2);
    }


    public BaseException(String messageId, String[] params) {
        this.messageId = messageId;
        this.message = PropertyUtil.getProperty(messageId, params);
    }

    @Override
    public synchronized BaseException initCause(Throwable cause) {

        return (BaseException) super.initCause(cause);
    }


    public String getMessage() {
        return this.message;
    }


    public String getMessageId() {
        return this.messageId;
    }
}
