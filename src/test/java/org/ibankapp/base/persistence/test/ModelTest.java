/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.persistence.test;

import org.ibankapp.base.persistence.Model;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void testSetId(){
        Model model = new Model();
        model.setId("1");
        assertEquals("1",model.getId());
    }
}
