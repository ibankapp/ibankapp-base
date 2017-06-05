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

public class BasePersistenceException extends BaseException {

    private final static String MESSAGE_FILE = "base_persistence_message.properties";

    static {
        PropertyUtil.load(MESSAGE_FILE);
    }

    public BasePersistenceException(String messageId){
        super(messageId);
    }

    public BasePersistenceException(String messageId, String param) {
        super(messageId, param);
    }
}
