/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence;

import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.exception.PropertyUtil;

/**
 * 持久化异常
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class BasePersistenceException extends BaseException {

    private final static String MESSAGE_FILE = "base_persistence_message.properties";

    static {
        PropertyUtil.load(MESSAGE_FILE);
    }

    public BasePersistenceException(String messageId) {
        super(messageId);
    }

    public BasePersistenceException(String messageId, String param) {
        super(messageId, param);
    }
}
