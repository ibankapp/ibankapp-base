/*
 * iBankApp
 *
 * License : Apache License,Version 2.0, January 2004
 *
 * See the LICENSE file in English or LICENSE.zh_CN in chinese
 * in the root directory or <http://www.apache.org/licenses/>.
 */

package org.ibankapp.base.validations.test;


import org.hibernate.validator.constraints.NotEmpty;
import org.ibankapp.base.exceptions.BaseException;
import org.ibankapp.base.validations.validators.BeanValidator;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import javax.validation.constraints.NotNull;

public class BeanValidatorTest {

    /**
     * 异常测试RULE
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testValidateFail() {

        thrown.expect(BaseException.class);
        thrown.expectMessage("名称不能为空;");
        thrown.expectMessage("ID不能为NULL;");

        ValidationTestBean bean = new ValidationTestBean();
        BeanValidator.validate(bean);
    }

    @Test
    public void testValidatePass() {

        ValidationTestBean bean = new ValidationTestBean();
        bean.setId("123");
        bean.setName("name");
        BeanValidator.validate(bean);
    }

    @Test
    public void testNewValidator() {

        BeanValidator validator = new BeanValidator();
        assertNotNull(validator);
    }

    private static class ValidationTestBean {

        private String id;
        private String name;

        @NotNull(message = "ID不能为NULL")
        @SuppressWarnings("unused")
        public String getId() {
            return id;
        }

        void setId(String id) {
            this.id = id;
        }

        @NotEmpty(message = "名称不能为空")
        @SuppressWarnings("unused")
        public String getName() {
            return name;
        }

        void setName(String name) {
            this.name = name;
        }
    }
}
