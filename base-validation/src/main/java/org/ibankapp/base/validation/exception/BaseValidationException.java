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

/**
 * bean校验异常
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:codelder@ibankapp.org">codelder</a>
 * @since 1.0.0
 */
public class BaseValidationException extends BaseException {

  private static final String MESSAGE_FILE = "base_validation_message.properties";

  static {
    PropertyUtil.load(MESSAGE_FILE);
  }

  public BaseValidationException(String messageId) {
    super(messageId);
  }

  public BaseValidationException(String messageId, String param) {
    super(messageId, param);
  }

  public BaseValidationException(String messageId, String param1, String param2) {
    super(messageId, param1, param2);
  }
}
