/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.security;

/**
 * 加解密类型
 *
 * @author <a href="http://www.ibankapp.org">ibankapp</a>
 * @author <a href="mailto:liulj@ibankapp.org">esailor</a>
 * @since 1.0.0
 */
public enum EncryptType {
    /**
     * 使用公钥加密／私钥解密
     */
    PUBLICKEYFLAG,
    /**
     * 使用私钥加解／公钥解密
     */
    PRIVATEKEYFLAG
}

