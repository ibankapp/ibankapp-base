package org.ibankapp.base.persistence.test;

import org.ibankapp.base.persistence.Model;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void testSetId(){
        Model model = new Model();
        assertEquals(36,model.getId().length());
        model.setId("1");
    }
}
