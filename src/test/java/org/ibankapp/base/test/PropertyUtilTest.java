package org.ibankapp.base.test;

import org.ibankapp.base.exception.PropertyUtil;
import org.junit.Test;

public class PropertyUtilTest {

    @Test
    public void testLoadPropertyFile(){

        PropertyUtil.load("base_message.properties");

    }
}
