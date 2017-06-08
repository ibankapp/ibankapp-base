/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validation.exception;

import org.ibankapp.base.exception.BaseException;
import org.ibankapp.base.exception.PropertyUtil;

public class BaseValidationException extends BaseException {

    private final static String MESSAGE_FILE = "base_validation_message.properties";

    static {
        PropertyUtil.load(MESSAGE_FILE);
    }

    public BaseValidationException(String messageId, String param) {
        super(messageId, param);
    }
}
