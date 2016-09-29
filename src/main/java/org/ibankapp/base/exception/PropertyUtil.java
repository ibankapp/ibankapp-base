package org.ibankapp.base.exception;

import java.io.IOException;
import java.io.InputStream;

import java.text.MessageFormat;

import java.util.Properties;

/**
 * 配置文件读取类
 *
 *
 * @version        1.0.0, 16/09/27
 * @author         codelder
 */
public class PropertyUtil {

    /** 属性文件对象 */
    private final static Properties props = new Properties();

    /**
     * 将配置文件读取进入属性对象
     *
     *
     * @param name 文件路径及名称
     */
    public static void load(String name) {
        InputStream is = null;

        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
            props.load(is);
        } catch (NullPointerException e) {
            e.printStackTrace();

            throw new BaseException().initCause(e);
        } catch (IOException e) {
            e.printStackTrace();

            throw new BaseException().initCause(e);
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();

                throw new BaseException().initCause(e);
            }
        }
    }

    /**
     * Method description
     *
     *
     * @param key
     *
     * @return 属性描述
     */
    public static String getProperty(String key) {
        if (key != null) {
            return props.getProperty(key);
        } else {
            return null;
        }
    }

    /**
     * Method description
     *
     *
     * @param key
     * @param appendMsg
     *
     * @return 属性描述
     */
    public static String getProperty(String key, String appendMsg) {
        String property = getProperty(key);

        if ((appendMsg != null) && (key != null)) {
            property = MessageFormat.format(property, appendMsg);
        }

        return property;
    }

    /**
     * Method description
     *
     *
     * @param key
     * @param appendMsgs
     *
     * @return 属性描述
     */
    public static String getProperty(String key, String[] appendMsgs) {
        String property = getProperty(key);

        if ((appendMsgs != null) && (key != null)) {
            property = MessageFormat.format(property, (Object[]) appendMsgs);
        }

        return property;
    }

    /**
     * Method description
     *
     *
     * @param key
     * @param appendMsg
     * @param appendMsg1
     *
     * @return 属性描述
     */
    public static String getProperty(String key, String appendMsg, String appendMsg1) {
        String property = getProperty(key);

        if (property == null) {
            throw new BaseException("E-BASE-000002", key);
        }

        if ((appendMsg != null) && (key != null)) {
            property = MessageFormat.format(property, appendMsg, appendMsg1);
        }

        return property;
    }
}