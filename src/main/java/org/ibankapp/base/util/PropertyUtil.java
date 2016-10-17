/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.util;

import org.ibankapp.base.exception.BaseException;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

/**
 * 配置文件读取类
 *
 * @author codelder
 * @version 1.0.0, 16/09/27
 */
public class PropertyUtil {

    /**
     * 属性文件对象
     */
    private final static Properties props = new Properties();

    /**
     * 将属性文件读取进入属性对象
     *
     * @param name 文件路径及名称
     */
    public static void load(String name) {
        InputStream is = null;

        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
            props.load(is);
        } catch (NullPointerException | IOException e) {
            throw new BaseException().initCause(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据key值从属性文件中获取value
     *
     * @param key 属性key
     * @return 属性value
     * @throws BaseException key为null异常
     */
    public static String getProperty(String key) {

        if (key == null) {
            throw new BaseException("E-BASE-000002");
        }

        return props.getProperty(key);

    }

    /**
     * 根据key值和一个插值从属性文件获取value
     *
     * @param key       属性key
     * @param appendMsg 插值
     * @return 属性value
     */
    public static String getProperty(String key, String appendMsg) {
        String property = getProperty(key);

        if (appendMsg != null) {
            property = MessageFormat.format(property, appendMsg);
        }

        return property;
    }

    /**
     * 根据key值和多个插值从属性文件获取value
     *
     * @param key        属性key
     * @param appendMsgs 插值数组
     * @return 属性value
     */
    public static String getProperty(String key, String[] appendMsgs) {
        String property = getProperty(key);

        if (appendMsgs != null) {
            property = MessageFormat.format(property, (Object[]) appendMsgs);
        }

        return property;
    }

    /**
     * 根据key值和两个插值从属性文件获取value
     *
     * @param key        属性key
     * @param appendMsg  插值1
     * @param appendMsg1 插值2
     * @return 属性value
     */
    public static String getProperty(String key, String appendMsg, String appendMsg1) {
        String property = getProperty(key);

        if (property == null) {
            throw new BaseException("E-BASE-000003", key);
        }
        
        property = MessageFormat.format(property, appendMsg, appendMsg1);

        return property;
    }
}