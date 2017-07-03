/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.exception;

/**
 * 异常处理基类
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class BaseException extends RuntimeException {


    private static final long serialVersionUID = 8639994227897113279L;
    private final static String MESSAGE_FILE = "base_message.properties";

    static {
        PropertyUtil.load(MESSAGE_FILE);
    }

    /**
     * 错误信息ID.
     */
    private String messageId;

    /**
     * 完整错误信息
     */
    private String message;


    /**
     * 默认构造函数
     */
    public BaseException() {
    }

    /**
     * 构造函数
     *
     * @param messageId 错误信息ID
     */
    public BaseException(String messageId) {
        this.messageId = messageId;
        this.message = PropertyUtil.getProperty(messageId);
    }


    /**
     * 构造函数
     *
     * @param messageId 错误信息ID
     * @param param     错误信息插值
     */
    public BaseException(String messageId, String param) {
        this.messageId = messageId;
        this.message = PropertyUtil.getProperty(messageId, param);
    }


    /**
     * 构造函数
     *
     * @param messageId 错误信息ID
     * @param param1    错误信息插值1
     * @param param2    错误信息插值2
     */
    public BaseException(String messageId, String param1, String param2) {
        this.messageId = messageId;
        this.message = PropertyUtil.getProperty(messageId, param1, param2);
    }


    /**
     * 构造函数
     *
     * @param messageId 错误信息ID
     * @param params    错误信息插值数组
     */
    public BaseException(String messageId, String[] params) {
        this.messageId = messageId;
        this.message = PropertyUtil.getProperty(messageId, params);
    }

    /**
     * 设置 错误原始原因
     *
     * @param cause 原始异常
     * @return 当前异常
     */
    @Override
    public synchronized BaseException initCause(Throwable cause) {

        return (BaseException) super.initCause(cause);
    }


    /**
     * 获取 完整错误信息
     *
     * @return 完整错误信息
     */
    @Override
    public String getMessage() {
        return this.message;
    }


    /**
     * 获取 错误信息ID
     *
     * @return 错误信息ID
     */
    public String getMessageId() {
        return this.messageId;
    }
}
